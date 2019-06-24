package com.example.android.biddingfarmer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class firstpage extends AppCompatActivity {



    ImageView bidder_img,farmer_img;
    TextView sign_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstpage);
        registerButton();
        farmer_login();
        bidder_login();
    }
    public void bidder_login()
    {
        bidder_img=(ImageView) findViewById(R.id.iv_bidder);
        bidder_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(firstpage.this,bidder_login.class));
            }
        });
    }
    public void farmer_login()
    {
        farmer_img=(ImageView) findViewById(R.id.iv_farmer);
        farmer_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(firstpage.this,farmer_login.class));
            }
        });
    }
    public void registerButton()
    {

        sign_up=(TextView) findViewById(R.id.tv_sign_up);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(firstpage.this,signup.class));
            }
        });
    }
}
