package com.example.securestocksystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button b1;
    Button b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button)findViewById(R.id.btn_login);
        b2 = (Button)findViewById(R.id.btn_signup);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivityLogin();
            }
        });



        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivitySignup();
            }
        });


    }

    public void openNewActivityLogin(){
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }

    public void openNewActivitySignup(){
        Intent intent = new Intent(this, signup.class);
        startActivity(intent);
    }

}