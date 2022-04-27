package com.example.oslab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DeadlockActivity1 extends AppCompatActivity {

    EditText pn, rn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deadlock1);
    }

    public void openDeadlock2(View v){
        Toast.makeText(this, "Opening Deadlock check 2", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DeadlockActivity2.class);
        pn = findViewById(R.id.editTxtP2);
        rn = findViewById(R.id.editTxtR2);

//        intent.putExtra("pn", pn.getText().toString());
//        intent.putExtra("rn", rn.getText().toString());

        int p, r;
        if(pn.getText().toString().length() == 0)
        {
            p=1;
        }
        else
        {
            p = Integer.parseInt(pn.getText().toString());
        }
        if(rn.getText().toString().length() == 0)
        {
            r=1;
        }
        else
        {
            r = Integer.parseInt(rn.getText().toString());
        }
        intent.putExtra("pn", p);
        intent.putExtra("rn", r);
        startActivity(intent);

    }
}