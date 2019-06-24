package com.example.android.biddingfarmer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Negotiate extends AppCompatActivity {

    DataManager dm;
    Button b,b1;
    TextView t1;
    EditText e1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_negotiate);
        dm=new DataManager(this);
        e1=(EditText) findViewById(R.id.editText16);
        t1=(TextView) findViewById(R.id.textView37);
        b=(Button) findViewById(R.id.button15);
        t1.setText(dm.getCost(ListBid.current_id));
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dm.updateCost(ListBid.current_id,e1.getText().toString());
                dm.updateSide(ListBid.current_id,"F");
                Toast.makeText(Negotiate.this,"Request Sent to Bidder",Toast.LENGTH_LONG).show();

            }
        });

        b1=(Button)findViewById(R.id.btn_home5);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Negotiate.this,firstpage.class));
            }
        });
    }
}
