package com.example.android.biddingfarmer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class signup extends AppCompatActivity {
    Button b2;
    EditText e1,e2,e3,e4,e5;
    CheckBox c1,c2;
    DataManager dm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        b2=(Button)findViewById(R.id.button5);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signup.this,firstpage.class));
            }
        });
        Button b3=(Button)findViewById(R.id.button4);
        dm=new DataManager(this);


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c1=(CheckBox)findViewById(R.id.checkBox);
                c2=(CheckBox)findViewById(R.id.checkBox2);
                if((c1.isChecked() && !c2.isChecked()) || (!c1.isChecked() && c2.isChecked()))
                {

                    e1=(EditText)findViewById(R.id.editText);
                    e2=(EditText)findViewById(R.id.editText2);
                    e3=(EditText)findViewById(R.id.editText3);
                    e4=(EditText)findViewById(R.id.editText4);
                    e5=(EditText)findViewById(R.id.editText5);

                    if(e4.getText().toString().equals(e5.getText().toString()))
                    {
                        String val;

                        if (c1.isChecked()) {
                            val = "Farmer";
                        } else if (c2.isChecked()) {
                            val = "Bidder";
                        }
                        else
                        {
                            Toast.makeText(signup.this,"Selected Farmer by default",Toast.LENGTH_LONG).show();
                            val="Farmer";
                        }

                        boolean result = dm.insert_data(e1.getText().toString(), e2.getText().toString(), e3.getText().toString(), e4.getText().toString(), val);
                        if(result==true)
                        {
                            Toast.makeText(signup.this,"Data Inserted", Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(signup.this,"Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(signup.this,"Password Incorrect", Toast.LENGTH_LONG).show();
                        e4.setText(null);
                        e5.setText(null);
                    }
                }
                else if((c1.isChecked() && c2.isChecked()))
                {
                    Toast.makeText(signup.this,"Choose only one option", Toast.LENGTH_LONG).show();
                    c1.toggle();
                    c2.toggle();
                }
                else
                {
                    Toast.makeText(signup.this,"Choose atleast one option", Toast.LENGTH_LONG).show();
                }
                }


        });

    }

}
