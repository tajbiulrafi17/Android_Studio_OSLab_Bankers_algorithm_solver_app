package com.example.oslab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DeadlockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deadlock);
    }

    public void openBankers1(View v){
        Toast.makeText(this, "Opening BankersAlgorithm", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, BankersActivity1.class);
        startActivity(intent);
    }

    public void openDeadlock1(View v){
        Toast.makeText(this, "Opening Deadlock check", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DeadlockActivity1.class);
        startActivity(intent);
    }

}