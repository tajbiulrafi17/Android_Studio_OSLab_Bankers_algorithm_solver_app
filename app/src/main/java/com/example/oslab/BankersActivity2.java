package com.example.oslab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class BankersActivity2 extends AppCompatActivity {

    TextView pn,rn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bankers2);
        pn = findViewById(R.id.txtPN);
        rn = findViewById(R.id.txtRN);
        Intent intent = getIntent();
        String strP = intent.getStringExtra("pn");
        String strR = intent.getStringExtra("rn");
        pn.setText("Number of Processes:"+strP);
        rn.setText("Number of Resources:"+strR);

    }
}