package com.example.oslab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DeadlockActivity2 extends AppCompatActivity {

    TextView pn,rn, show, safe;
    EditText a, e;
    Button btn;
    int p, r;

    private LinearLayout lnrDynamicEditTextHolder;
    private EditText edtNoCreate;
    private Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deadlock2);


        pn = findViewById(R.id.txtPN2);
        rn = findViewById(R.id.txtRN2);
        a = findViewById(R.id.resourceInstances);
        Intent intent = getIntent();

        //
//        String strP = intent.getStringExtra("pn");
//        String strR = intent.getStringExtra("rn");
//        pn.setText("Number of Processes:"+strP);
//        rn.setText("Number of Resources:"+strR);
//        pn.setText("Number of Processes: "+intent.getIntExtra("pn", 1));
//        rn.setText("Number of Resources: "+intent.getIntExtra("rn", 1));
        //
        p = intent.getIntExtra("pn",1);
        r = intent.getIntExtra("rn",1);
        pn.setText("Number of Processes: "+p);
        rn.setText("Number of Resources: "+r);
        a.setHint("Enter Instances of "+r+" Resources [Space Separated]");

        //int length = Integer.parseInt(intent.getStringExtra("pn"));

        List<EditText> allocAL = new ArrayList<EditText>();
        List<EditText> reqAL = new ArrayList<EditText>();

        for(int i=0; i<p; i++)
        {
            LinearLayout linearLayout = findViewById(R.id.lnrDynamicEditTextHolder);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams mRparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            EditText myEditTextAlloc = new EditText(this);
            mRparams.setMargins(10,1,10,0);
            allocAL.add(myEditTextAlloc);
            myEditTextAlloc.setId(i+1);
            myEditTextAlloc.setBackgroundResource(R.drawable.border);
            myEditTextAlloc.setHint("Enter "+r+" Allocation for P"+i+" [Space Separated]");
            myEditTextAlloc.setInputType(InputType.TYPE_CLASS_TEXT);
            myEditTextAlloc.setLayoutParams(mRparams);
            linearLayout.addView(myEditTextAlloc);
        }
        for(int i=0; i<p; i++)
        {
            LinearLayout linearLayout = findViewById(R.id.lnrDynamicEditTextHolder1);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams mRparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            EditText myEditTextMax = new EditText(this);
            mRparams.setMargins(10,1,10,0);
            reqAL.add(myEditTextMax);
            myEditTextMax.setId(i+1);
            myEditTextMax.setBackgroundResource(R.drawable.border);
            myEditTextMax.setHint("Enter "+r+" Request for P"+i+" [Space Separated]");
            myEditTextMax.setInputType(InputType.TYPE_CLASS_TEXT);
            myEditTextMax.setLayoutParams(mRparams);
            linearLayout.addView(myEditTextMax);
        }

        
        safe = findViewById(R.id.textView8);
        safe.setVisibility(View.GONE);

        btn = findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DeadlockActivity2.this, "Calculating...", Toast.LENGTH_SHORT).show();

//                btn.setEnabled(false);
                btn.setVisibility(View.GONE);

                String[] strAlloc = new String[p];
                String[] strReq = new String[p];
                String strInstance = new String();

                for (int i = 0; i < p; i++) {
                    strAlloc[i] = allocAL.get(i).getText().toString();
                }
                for (int i = 0; i < p; i++) {
                    strReq[i] = reqAL.get(i).getText().toString();
                }


                e = findViewById(R.id.resourceInstances);
                strInstance = e.getText().toString();

                int alloc[][] = new int[p][r];
                int req[][] = new int[p][r];
                int instance[] = new int[r];
                int used[] = new int[r];
                int available[] = new int[r];

                int safeSequence[] = new int[p];
//
                //put data on matrix
                String s[] = strInstance.split(" ");
                for (int i = 0; i < s.length; i++) {
                    instance[i] = Integer.parseInt(s[i]);
                }
                for (int i = 0; i < p; i++) {
                    String ss[] = strAlloc[i].split(" ");
                    for (int j = 0; j < ss.length; j++) {
                        alloc[i][j] = Integer.parseInt(ss[j]);
                    }
                }
                for (int i = 0; i < p; i++) {
                    String ss[] = strReq[i].split(" ");
                    for (int j = 0; j < ss.length; j++) {
                        req[i][j] = Integer.parseInt(ss[j]);
                    }
                }

                for(int j=0; j<r; j++)
                {
                    used[j] = 0;
                }
                for(int i=0; i<p; i++)
                {
                    for(int j=0; j<r; j++)
                    {
                        used[j] = used[j] + alloc[i][j];
                    }

                }
                for(int i=0; i<r; i++)
                {
                    available[i] = instance[i] - used[i];
                }


                //Safe state check
                int count = 0;

                boolean visited[] = new boolean[p];
                for (int i = 0; i < p; i++) {
                    visited[i] = false;
                }


                int work[] = new int[r];
                for (int i = 0; i < r; i++) {
                    work[i] = available[i];
                }

                while (count < p) {
                    boolean flag = false;
                    for (int i = 0; i < p; i++) {
                        if (visited[i] == false) {
                            int j;
                            for (j = 0; j < r; j++) {
                                if (req[i][j] > work[j])
                                    break;
                            }
                            if (j == r) {
                                safeSequence[count++] = i;
                                visited[i] = true;
                                flag = true;

                                for (j = 0; j < r; j++) {
                                    work[j] = work[j] + alloc[i][j];
                                }
                            }
                        }
                    }
                    if (flag == false) {
                        break;
                    }
                }
                if (count < p) {
                    safe.setVisibility(View.VISIBLE);
                    safe.setText("Deadlock Occurs!");
                }
                else
                {
                    safe.setVisibility(View.VISIBLE);
                    safe.setText("No Deadlock");
                    safe.append("\nFollowing is the SAFE Sequence:\n  ");
                    for (int i = 0;i < p; i++)
                    {
                        safe.append("P" + safeSequence[i]);
                        if (i != p-1)
                            safe.append(" -> ");
                    }

                }

            }


        });

    }

}