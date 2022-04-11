package com.example.oslab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // for hide actionBar from main page
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        //

    }

    public void openBankers1(View v){
        Toast.makeText(this, "Opening BankersActivity 01", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, BankersActivity1.class);
        startActivity(intent);
    }

}