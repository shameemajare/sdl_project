package com.example.android.biddingfarmer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FinalFarmer extends AppCompatActivity {

    DataManager dm;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_farmer);

        dm=new DataManager(this);
        t1=(TextView)findViewById(R.id.textView47);
        t1.setText("Sold "+ListBidderFinal.current_veg+" for RS. "+dm.getCost(ListBidderFinal.current_id));
    }
}
