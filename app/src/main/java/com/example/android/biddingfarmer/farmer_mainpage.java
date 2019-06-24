package com.example.android.biddingfarmer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class farmer_mainpage extends AppCompatActivity {

    Button button1,button2,button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farmer_mainpage);
        button1=(Button)findViewById(R.id.button7);
        button2=(Button)findViewById(R.id.button8);
        button3=(Button)findViewById(R.id.button22);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(farmer_mainpage.this,farmer_bid.class));
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(farmer_mainpage.this,ListBid.class));
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(farmer_mainpage.this,ListBidderFinal.class));
            }
        });
    }
}
