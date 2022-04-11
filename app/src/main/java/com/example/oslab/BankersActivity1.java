package com.example.oslab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class BankersActivity1 extends AppCompatActivity {

    EditText pn, rn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bankers01);
    }

    public void openBankers2(View v){
        Toast.makeText(this, "Opening BankersActivity 02", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, BankersActivity2.class);
        pn = findViewById(R.id.editTxtP);
        rn = findViewById(R.id.editTxtR);
        intent.putExtra("pn", pn.getText().toString());
        intent.putExtra("rn", rn.getText().toString());
        startActivity(intent);
    }

}