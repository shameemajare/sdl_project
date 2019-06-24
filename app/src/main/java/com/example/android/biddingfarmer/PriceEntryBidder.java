package com.example.android.biddingfarmer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PriceEntryBidder extends AppCompatActivity {

    DataManager dm;
    EditText e1;
    TextView t;
    Button button,btn_home,go_loc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_entry_bidder);
        e1=(EditText) findViewById(R.id.editText9);
        dm=new DataManager(this);
        button=(Button) findViewById(R.id.button11);
        btn_home=(Button)findViewById(R.id.btn_home2);
        go_loc=(Button)findViewById(R.id.button);
        t=findViewById(R.id.textView50);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag=dm.start_bid_insert(ListSearch.farmer_name_for_priceentry,bidder_login.bidder_name, ListSearch.veg_name_for_priceentry,ListSearch.qty_for_priceentry,ListSearch.grade_for_priceentry,e1.getText().toString(),MapsActivity.result,biddermainpage.input_qty);
                if(flag==true)
                {
                    Toast.makeText(PriceEntryBidder.this,"Data Inserted",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(PriceEntryBidder.this,"Failed",Toast.LENGTH_LONG).show();
                }
                e1.setText("");
//                startActivity(new Intent(PriceEntryBidder.this,List.class));
            }
        });
        Toast.makeText(PriceEntryBidder.this,ListSearch.farmer_name_for_priceentry,Toast.LENGTH_LONG).show();

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PriceEntryBidder.this,firstpage.class));
            }
        });
        go_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PriceEntryBidder.this,MapsActivity.class));
                t.setText(MapsActivity.result1);
            }
        });
    }
}
