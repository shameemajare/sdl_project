package com.example.android.biddingfarmer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FarmerNegotiate extends AppCompatActivity {

    static String flag="BidderSide";
    DataManager dm;
    TextView t1,t2;
    String status;
    Button b1,b2,b3,b4,b5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_negotiate);
        dm=new DataManager(this);

        t1=(TextView)findViewById(R.id.textView22);
        t2=(TextView)findViewById(R.id.textView32);


        b1=(Button)findViewById(R.id.button12);
        b2=(Button)findViewById(R.id.button13);
        b3=(Button)findViewById(R.id.button14);




        if(dm.getStatus(ListBid.current_id).equals("NegByBidder"))
        {
            t2.setText("The Bidder has requested for rise in price\nNew Price is :-"+dm.getCost(ListBid.current_id)+" per Kgs");
        }
        else if(dm.getStatus(ListBid.current_id).equals("AcceptByBidder"))
        {
            t2.setText("Hurray!! The Bidder is willing to pay you requested amount!!");
        }
        else if(dm.getStatus(ListBid.current_id).equals(""))
        {
            dm.updateCost(ListBid.current_id,ListBid.base_price);
            t2.setText("The Bidder has offered you RS. :- "+dm.getCost(ListBid.current_id)+" per Kgs");

//            t2.setText("Your request is being processed!!");
        }
        else
        {
//            Negotiate.current_cost=ListBid.base_price;
            t2.setText("Your request is being processed.....");
        }

        t1.setText(dm.getCost(ListBid.current_id));

        if(dm.getStatus(ListBid.current_id).equals("AcceptByBidder") || dm.getStatus(ListBid.current_id).equals("AcceptByFarmer") )
        {
            t2.setText("");
            t2.setText("Sold "+ListBid.current_veg+" for RS. "+dm.getCost(ListBid.current_id));
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(FarmerNegotiate.this,"Bid Already Accepted",Toast.LENGTH_LONG).show();

                }
            });

            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(FarmerNegotiate.this,"Bid Already Accepted",Toast.LENGTH_LONG).show();
                }
            });

            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(FarmerNegotiate.this,"Bid Already Accepted",Toast.LENGTH_LONG).show();
                }
            });
        }
        else if(dm.getSide(ListBid.current_id).equals("B"))
        {

                b1.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if(dm.getSide(ListBid.current_id).equals("B"))
                        {
                            status="NegByFarmer";
                        int count=dm.update_status(status,ListBid.current_id);
                        if(count>0)
                        {
                            Toast.makeText(FarmerNegotiate.this,"Update Succeded",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(FarmerNegotiate.this,"Failed to update",Toast.LENGTH_LONG).show();
                        }
                            startActivity(new Intent(FarmerNegotiate.this,Negotiate.class));

                        }
                        else
                            Toast.makeText(FarmerNegotiate.this,"Can't negotiate this time",Toast.LENGTH_LONG).show();


                    }
                });

                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(dm.getSide(ListBid.current_id).equals("B"))
                        {
                            status="AcceptByFarmer";
                            int count=dm.update_status(status,ListBid.current_id);
                            if(count>0)
                            {
                                Toast.makeText(FarmerNegotiate.this,"Update Succeded",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(FarmerNegotiate.this,"Failed to update",Toast.LENGTH_LONG).show();
                            }
//                    Negotiate.current_cost=Negotiate.bidder_cost;
                            dm.updateSide(ListBid.current_id,"F");

                            dm.updateQty(ListBid.current_id,farmer_login.str,ListBid.current_veg,ListBid.current_grade);

                        }
                        else
                            Toast.makeText(FarmerNegotiate.this,"You can't accept bid yourself",Toast.LENGTH_LONG).show();


                    }
                });

                b3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(dm.getSide(ListBid.current_id).equals("B"))
                        {
                            status="EndByFarmer";
                            int count=dm.update_status(status,ListBid.current_id);
                            if(count>0)
                            {
                                Toast.makeText(FarmerNegotiate.this,"Update Succeded",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(FarmerNegotiate.this,"Failed to update",Toast.LENGTH_LONG).show();
                            }

                            dm.updateSide(ListBid.current_id,"F");

//                            flag="";
//                            flag="FarmerSide";
                        }
                        else
                            Toast.makeText(FarmerNegotiate.this,"You can't block bidder this time",Toast.LENGTH_LONG).show();


                    }
                });

                b4=(Button)findViewById(R.id.btn_home3);
                b4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(FarmerNegotiate.this,firstpage.class));
                    }
                });

                b5=(Button)findViewById(R.id.btn_bck2);
                b5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(FarmerNegotiate.this,farmer_mainpage.class));
                    }
                });


        }


    }
}
