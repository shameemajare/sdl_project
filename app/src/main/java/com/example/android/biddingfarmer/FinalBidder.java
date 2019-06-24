package com.example.android.biddingfarmer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FinalBidder extends AppCompatActivity {

    DataManager dm;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_bidder);

        dm=new DataManager(this);
        t1=(TextView)findViewById(R.id.textView46);
        t1.setText("Bought "+FinalList.current_veg+" for RS. "+dm.getCost(FinalList.current_id));
    }

}
