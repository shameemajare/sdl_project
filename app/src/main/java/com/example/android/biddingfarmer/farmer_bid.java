package com.example.android.biddingfarmer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class farmer_bid extends AppCompatActivity {

    DataManager dM;
    Button button,btn_home2,btn_bck;
    EditText e1,e2,e3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farmer_bid);
        button=(Button)findViewById(R.id.button9);
        btn_home2=(Button)findViewById(R.id.home2);
        btn_bck=(Button)findViewById(R.id.btn_back);

        dM = new DataManager(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e1=(EditText)findViewById(R.id.editText7);
                e2=(EditText)findViewById(R.id.editText10);
                e3=(EditText)findViewById(R.id.editText11);
                boolean temp=dM.insert_bid(farmer_login.str,e1.getText().toString(),e2.getText().toString(),e3.getText().toString());
                if(temp==true)
                {
                    Toast.makeText(farmer_bid.this,"Data Inserted", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(farmer_bid.this,"Data not Inserted", Toast.LENGTH_LONG).show();
            }
        });

        btn_home2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(farmer_bid.this,firstpage.class));
            }
        });

        btn_bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(farmer_bid.this,farmer_mainpage.class));
            }
        });
    }
}
