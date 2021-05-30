package com.example.securestocksystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.UUID;

public class AddStocks extends AppCompatActivity {

    EditText stockname;
    EditText customerprice;
    EditText wholesaleprice;
    EditText totalstocks;
    Button addstock;
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stocks);
        stockname = (EditText)findViewById(R.id.stockname);
        customerprice = (EditText)findViewById(R.id.crs);
        wholesaleprice = (EditText)findViewById(R.id.wsrs);
        totalstocks = (EditText)findViewById(R.id.total);
        addstock = (Button)findViewById(R.id.add);

        db = FirebaseFirestore.getInstance();


        addstock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = UUID.randomUUID().toString();
                String pname = stockname.getText().toString();
                String cusprice = customerprice.getText().toString();
                String whprice = wholesaleprice.getText().toString();
                String total = totalstocks.getText().toString();


                storestocks(id,pname,cusprice,whprice,total);
            }
        });

    }

    private void storestocks(String id,String pname,String cusprice,String enwhprice,String entotal)
    {
        if(!pname.isEmpty() && !cusprice.isEmpty() && !enwhprice.isEmpty() && !entotal.isEmpty())
        {
            HashMap<String , Object> map = new HashMap<>();
            map.put("id",id);
            map.put("productname",pname);
            map.put("customerprice",cusprice);
            map.put("wholesaleprice",enwhprice);
            map.put("totalstocks",entotal);

            db.collection("stocks").document(id).set(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(AddStocks.this, "Products Added !!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddStocks.this, "Failed to add products !!", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
        {
            Toast.makeText(AddStocks.this, "Empty fields not allowed !!", Toast.LENGTH_SHORT).show();
        }
    }
}