package com.example.securestocksystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnterKey extends AppCompatActivity {

    EditText keyvalue;
    Button decrypt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_key);
        keyvalue = (EditText)findViewById(R.id.keyvalue);
        decrypt = (Button)findViewById(R.id.decrypt);

        decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                  opennewshowactivity(keyvalue);
            }
        });
    }

    public void opennewshowactivity(EditText keyvalue)
    {
        String keytext = keyvalue.getText().toString();
        Intent intent = new Intent(this,ShowActivity.class);
        intent.putExtra("key",keytext);
        startActivity(intent);
    }
}