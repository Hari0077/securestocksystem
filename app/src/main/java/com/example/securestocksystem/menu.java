package com.example.securestocksystem;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class menu extends AppCompatActivity {
    Button add,view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        add = (Button) findViewById(R.id.addstock);
        view = (Button) findViewById(R.id.viewstock);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivityAdd();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openviewActivity();
            }
        });
    }


    public void openNewActivityAdd(){
        Intent intent = new Intent(this, addstock.class);
        startActivity(intent);
    }

    public void openviewActivity()
    {
        Intent intent = new Intent(this,EnterKey.class);
        startActivity(intent);
    }

}