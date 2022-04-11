package com.example.oslab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BankersActivity2 extends AppCompatActivity {

    TextView pn,rn, show;
    Button btn;

    private LinearLayout lnrDynamicEditTextHolder;
    private EditText edtNoCreate;
    private Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bankers2);
        pn = findViewById(R.id.txtPN);
        rn = findViewById(R.id.txtRN);
        Intent intent = getIntent();

        //
//        String strP = intent.getStringExtra("pn");
//        String strR = intent.getStringExtra("rn");
//        pn.setText("Number of Processes:"+strP);
//        rn.setText("Number of Resources:"+strR);
        pn.setText("Number of Processes: "+intent.getStringExtra("pn"));
        rn.setText("Number of Resources: "+intent.getStringExtra("rn"));
        //


        //int length = intent.getIntExtra("pn",0);
        int length = Integer.parseInt(intent.getStringExtra("pn"));

        List<EditText> allEds = new ArrayList<EditText>();
        for(int i=0; i<length; i++)
        {
            LinearLayout linearLayout = findViewById(R.id.lnrDynamicEditTextHolder);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams mRparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            EditText myEditTextAlloc = new EditText(this);
            allEds.add(myEditTextAlloc);
            myEditTextAlloc.setId(i+1);
            myEditTextAlloc.setHint("Enter Resources for P"+i+" [Space Separated]");
            myEditTextAlloc.setInputType(InputType.TYPE_CLASS_TEXT);
            myEditTextAlloc.setLayoutParams(mRparams);
            linearLayout.addView(myEditTextAlloc);
        }
        for(int i=0; i<length; i++)
        {
            LinearLayout linearLayout = findViewById(R.id.lnrDynamicEditTextHolder1);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams mRparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            EditText myEditTextMax = new EditText(this);
            myEditTextMax.setId(i+1);
            myEditTextMax.setHint("Enter Resources for P"+i+" [Space Separated]");
            myEditTextMax.setInputType(InputType.TYPE_CLASS_TEXT);
            myEditTextMax.setLayoutParams(mRparams);
            linearLayout.addView(myEditTextMax);
        }



        btn = findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BankersActivity2.this, "Onclick Worked", Toast.LENGTH_SHORT).show();
                String[] strings = new String[(allEds.size())];

                for(int i=0; i < allEds.size(); i++){
                    strings[i] = allEds.get(i).getText().toString();
                }
                show = findViewById(R.id.textView7);
                for(int i=0; i < allEds.size(); i++){
                    show.append(" ");
                    show.append(strings[i]);
                }
                btn.setEnabled(false);
                btn.setVisibility(View.GONE);


            }
        });


    }
}