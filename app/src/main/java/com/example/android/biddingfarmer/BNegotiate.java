package com.example.android.biddingfarmer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BNegotiate extends AppCompatActivity {

    DataManager dm;
    EditText e1;
    TextView t1;
    Button go,home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bnegotiate);
        dm=new DataManager(this);
        t1=(TextView)findViewById(R.id.textView39);
        t1.setText(dm.getCost(ListBidBidder.current_id));
        go=(Button)findViewById(R.id.button20);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                e1=(EditText) findViewById(R.id.editText17);
                dm.updateCost(ListBidBidder.current_id,e1.getText().toString());
                dm.updateSide(ListBid.current_id,"B");
                Toast.makeText(BNegotiate.this,"Request Sent to Farmer",Toast.LENGTH_LONG).show();

            }
        });
        home=(Button)findViewById(R.id.btn_home6);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BNegotiate.this,firstpage.class));
            }
        });
    }
}
