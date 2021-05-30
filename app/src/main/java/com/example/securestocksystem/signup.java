package com.example.securestocksystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.UUID;

public class signup extends AppCompatActivity {
   Button b2,signup;
   EditText username,password,key;
    private FirebaseFirestore db;
   int i = 0;




    public String encryptusinghash(String input)
    {
        try {
            // getInstance() method is called with algorithm SHA-1
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }


            // return the HashText
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }


    }


   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        key = (EditText)findViewById(R.id.key);
        signup = (Button) findViewById(R.id.signup);
        b2 = (Button) findViewById(R.id.login);
       db = FirebaseFirestore.getInstance();
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String keyt = key.getText().toString();
                String u = encryptusinghash(user);
                String p = encryptusinghash(pass);
                String keytext = encryptusinghash(keyt);
                String id = UUID.randomUUID().toString();
                savetoFirebase(u,p,keytext,id);

            }
        });
    }

    public void openNewActivity(){
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }




    private void savetoFirebase(String u,String p,String keyText,String id)
    {
       // final int i = 0;
        db.collection("usersdetails").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    for(QueryDocumentSnapshot doc: task.getResult())
                    {
                        i = i + 1;
                    }
                }
            }
        });
        if(!u.isEmpty() && !p.isEmpty() && !keyText.isEmpty() && i==0)
        {
            HashMap<String , Object> map = new HashMap<>();

            map.put("username",u);
            map.put("password" , p);
            map.put("keyText" , keyText);

            db.collection("usersdetails").document(id).set(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(signup.this, "Registered Successfully please Login!!", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(signup.this, "Failed to register!!", Toast.LENGTH_SHORT).show();

                }
            });

        }
        else
        {
            Toast.makeText(this, "Empty Fields not Allowed or already user registered", Toast.LENGTH_SHORT).show();
        }
    }

}