package com.example.android.biddingfarmer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BidderNegotiate extends AppCompatActivity {

    Button b1,b2,b3,b4,b5;
    TextView t,t1;
    DataManager dm;
    String status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidder_negotiate);

        dm=new DataManager(this);
        b1=(Button)findViewById(R.id.button17);
        b2=(Button)findViewById(R.id.button18);
        b3=(Button)findViewById(R.id.button19);

        t=(TextView) findViewById(R.id.textView34);
        t1=(TextView) findViewById(R.id.textView45);
        t1.setText(dm.getCost(ListBidBidder.current_id));
        if(dm.getStatus(ListBidBidder.current_id).equals("AcceptByFarmer"))
        {
            t.setText("Farmer "+ListBidBidder.current_farmer+" has agreed to pay you ");
        }
        else if(dm.getStatus(ListBidBidder.current_id).equals("NegByFarmer"))
        {
            t.setText("Farmer request for rise in price.\nNew price is "+dm.getCost(ListBidBidder.current_id)+" per Kgs");
        }
        else if(dm.getStatus(ListBidBidder.current_id).equals(""))
        {
            dm.updateCost(ListBidBidder.current_id,ListBid.base_price);
            t.setText("Waiting for response from Farmer side.....");
        }
        else
        {
            t.setText("Your request is being processed!!");
        }


        if(dm.getStatus(ListBidBidder.current_id).equals("AcceptByFarmer") || dm.getStatus(ListBidBidder.current_id).equals("AcceptByBidder"))
        {
            t.setText("");
            t.setText("Bought "+ListBidBidder.current_veg+" for RS "+dm.getCost(ListBidBidder.current_id));

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(BidderNegotiate.this,"Bid Already Accepted",Toast.LENGTH_LONG).show();
                }
            });

            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(BidderNegotiate.this,"Bid Already Accepted",Toast.LENGTH_LONG).show();
                }
            });

            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(BidderNegotiate.this,"Bid Already Accepted",Toast.LENGTH_LONG).show();

                }
            });
        }
        else if(dm.getSide(ListBidBidder.current_id).equals("F"))
        {
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(dm.getSide(ListBidBidder.current_id).equals("F"))
                        {
                            status="AcceptByBidder";
                            int count=dm.update_status(status,ListBidBidder.current_id);
                            if(count>0)
                            {
                                FarmerNegotiate.flag="";
                                FarmerNegotiate.flag="BidderSide";
                                Toast.makeText(BidderNegotiate.this,"Update Succeded",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(BidderNegotiate.this,"Failed to update",Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(BidderNegotiate.this,"Can't Accept offer yourself",Toast.LENGTH_LONG).show();
                        }

                        dm.updateSide(ListBid.current_id,"B");

//                    Negotiate.current_cost=Negotiate.requested_farmer_cost;
                    }
                });



                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(dm.getSide(ListBidBidder.current_id).equals("F"))
                        {
                            status="NegByBidder";
                            int count=dm.update_status(status,ListBidBidder.current_id);
                            if(count>0)
                            {
                                Toast.makeText(BidderNegotiate.this,"Update Succeded",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(BidderNegotiate.this,"Failed to update",Toast.LENGTH_LONG).show();
                            }

                            startActivity(new Intent(BidderNegotiate.this,BNegotiate.class));
                        }
                        else
                        {
                            Toast.makeText(BidderNegotiate.this,"Can't Negotiate this time",Toast.LENGTH_LONG).show();
                        }

                    }
                });
                b3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(dm.getSide(ListBidBidder.current_id).equals("F"))
                        {
                            status="EndByBidder";
                            int count=dm.update_status(status,ListBidBidder.current_id);
                            if(count>0)
                            {
                                Toast.makeText(BidderNegotiate.this,"Update Succeded",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(BidderNegotiate.this,"Failed to update",Toast.LENGTH_LONG).show();
                            }
                            dm.updateSide(ListBid.current_id,"B");

                        }
                        else
                        {
                            Toast.makeText(BidderNegotiate.this,"Cant block this Farmer now",Toast.LENGTH_LONG).show();
                        }

                    }
                });

            b4=(Button)findViewById(R.id.btn_home4);
            b4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(BidderNegotiate.this,firstpage.class));
                }
            });

            b5=(Button)findViewById(R.id.btn_bck3);
            b5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(BidderNegotiate.this,biddermainpage.class));
                }
            });

        }

    }
}
