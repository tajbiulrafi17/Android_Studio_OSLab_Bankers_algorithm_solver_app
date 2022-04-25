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

public class BankersActivity2 extends AppCompatActivity {

    TextView pn,rn, show, safe;
    EditText a, editTextAvail;
    Button btn;
    int p, r;

    private LinearLayout lnrDynamicEditTextHolder;
    private EditText edtNoCreate;
    private Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bankers2);
        pn = findViewById(R.id.txtPN);
        rn = findViewById(R.id.txtRN);
        a = findViewById(R.id.availResources);
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
        a.setHint("Enter "+r+" Resources Available Value [Space Separated]");
        
        //int length = Integer.parseInt(intent.getStringExtra("pn"));

        List<EditText> allocAL = new ArrayList<EditText>();
        List<EditText> maxAL = new ArrayList<EditText>();

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
            myEditTextAlloc.setHint("Enter "+r+" Resources for P"+i+" [Space Separated]");
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
            maxAL.add(myEditTextMax);
            myEditTextMax.setId(i+1);
            myEditTextMax.setBackgroundResource(R.drawable.border);
            myEditTextMax.setHint("Enter "+r+" Resources for P"+i+" [Space Separated]");
            myEditTextMax.setInputType(InputType.TYPE_CLASS_TEXT);
            myEditTextMax.setLayoutParams(mRparams);
            linearLayout.addView(myEditTextMax);
        }

        show = findViewById(R.id.textView7);
        safe = findViewById(R.id.textView8);

        show.setVisibility(View.GONE);
        safe.setVisibility(View.GONE);



        btn = findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BankersActivity2.this, "Calculating...", Toast.LENGTH_SHORT).show();

//                btn.setEnabled(false);
                btn.setVisibility(View.GONE);

                String[] strAlloc = new String[p];
                String[] strMax = new String[p];
                String strAvail = new String();

                for (int i = 0; i < p; i++) {
                    strAlloc[i] = allocAL.get(i).getText().toString();
                }
                for (int i = 0; i < p; i++) {
                    strMax[i] = maxAL.get(i).getText().toString();
                }


                editTextAvail = findViewById(R.id.availResources);
                strAvail = editTextAvail.getText().toString();

                int alloc[][] = new int[p][r];
                int max[][] = new int[p][r];
                int need[][] = new int[p][r];
                int avail[] = new int[r];

                int safeSequence[] = new int[p];
//
                //put data on matrix
                String s[] = strAvail.split(" ");
                for (int i = 0; i < s.length; i++) {
                    avail[i] = Integer.parseInt(s[i]);
                }
                for (int i = 0; i < p; i++) {
                    String ss[] = strAlloc[i].split(" ");
                    for (int j = 0; j < ss.length; j++) {
                        alloc[i][j] = Integer.parseInt(ss[j]);
                    }
                }
                for (int i = 0; i < p; i++) {
                    String ss[] = strMax[i].split(" ");
                    for (int j = 0; j < ss.length; j++) {
                        max[i][j] = Integer.parseInt(ss[j]);
                    }
                }
                //Calculating need matrix
                for (int i = 0; i < p; i++) {
                    for (int j = 0; j < r; j++) {
                        need[i][j] = max[i][j] - alloc[i][j];
                    }
                }


                show.setVisibility(View.VISIBLE);
                show.append("--Need Matrix--\n    ");
                for (int i = 0; i < r; i++) {
                    show.append("R" + i + "\t");
                }

                for (int i = 0; i < p; i++) {
                    show.append("\nP" + i + "  ");
                    for (int j = 0; j < r; j++) {
                        show.append(Integer.toString(need[i][j]));
                        show.append("\t");
                    }
                }


                //Safe state check
                int count = 0;

                boolean visited[] = new boolean[p];
                for (int i = 0; i < p; i++) {
                    visited[i] = false;
                }


                int work[] = new int[r];
                for (int i = 0; i < r; i++) {
                    work[i] = avail[i];
                }

                while (count < p) {
                    boolean flag = false;
                    for (int i = 0; i < p; i++) {
                        if (visited[i] == false) {
                            int j;
                            for (j = 0; j < r; j++) {
                                if (need[i][j] > work[j])
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
                    safe.setText("The System is UnSafe!");
                }
                else
                {
                    safe.setVisibility(View.VISIBLE);
                    safe.setText("The given System is Safe.");
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