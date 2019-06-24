package com.example.android.biddingfarmer;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class biddermainpage extends AppCompatActivity {

    DataManager dm;
    Button b1,b2,b3;
    EditText edit1,edit2,edit3;

    static String input_user_name;
    static String input_veg_name;
    static String input_qty;
    static String input_grade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biddermainpage);
        b1=(Button)findViewById(R.id.button10);
        b2=(Button)findViewById(R.id.button16);
        b3=(Button)findViewById(R.id.button21);

        edit1=(EditText)findViewById(R.id.editText14);
        edit2=(EditText)findViewById(R.id.editText12);
        edit3=(EditText)findViewById(R.id.editText13);


        dm=new DataManager(this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                input_veg_name=edit1.getText().toString();
                input_qty=edit2.getText().toString();
                input_grade=edit3.getText().toString();
                if(input_veg_name.equals("") || input_grade.equals("") || input_qty.equals(""))
                {
                    Toast.makeText(biddermainpage.this,"Please fill all fields!!",Toast.LENGTH_LONG).show();
                }
                else
                    startActivity(new Intent(biddermainpage.this,ListSearch.class));

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(biddermainpage.this,ListBidBidder.class));
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(biddermainpage.this,FinalList.class));
            }
        });
    }
}
