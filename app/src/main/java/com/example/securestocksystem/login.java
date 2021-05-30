package com.example.securestocksystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class login extends AppCompatActivity {
    EditText username;
    EditText password;
    Button login;
    private FirebaseFirestore db;



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
        setContentView(R.layout.activity_login);
        username = (EditText)findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button)findViewById(R.id.login);
        db = FirebaseFirestore.getInstance();





        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String user = username.getText().toString();
                String pass = password.getText().toString();
                String u = encryptusinghash(user);
                String p = encryptusinghash(pass);

                loginusingfirestore(u,p);

            }
        });
    }

    private void loginusingfirestore(String u,String p)
    {
         System.out.println(u);
         System.out.println(p);
          if(!u.isEmpty() && !p.isEmpty())
          {
              db.collection("usersdetails").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                  @Override
                  public void onComplete(@NonNull  Task<QuerySnapshot> task) {

                      if(task.isSuccessful())
                      {
                          for(QueryDocumentSnapshot doc: task.getResult())
                          {
                              String us = doc.getString("username");
                              String ps = doc.getString("password");

                              if(u.equalsIgnoreCase(us) & p.equalsIgnoreCase(ps))
                              {

                                  Intent home = new Intent(login.this, menu.class);
                                  startActivity(home);
                                  Toast.makeText(login.this, "Logged In", Toast.LENGTH_SHORT).show();
                                  break;

                              }
                              else
                              {
                                  Toast.makeText(login.this, "Cannot login,incorrect Email and Password", Toast.LENGTH_SHORT).show();

                              }
                          }
                      }

                  }
              });

          }
          else
          {
              Toast.makeText(this, "Empty Fields not Allowed", Toast.LENGTH_SHORT).show();
          }
    }
}