package com.example.android.biddingfarmer;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListBidBidder extends AppCompatActivity {
    DataManager dm;
    ListView l;
    static String base_price;
    static String current_city;
    static String current_grade;
    static String current_farmer;
    static String current_bidder;
    static String current_veg;
    static String current_qty;
    static String current_id;

    public ArrayList<String> id;
    public ArrayList<String> farmer;
    public ArrayList<String> bidder;
    public ArrayList<String> veg;
    public ArrayList<String> qty;
    public ArrayList<String> grade;
    public ArrayList<String> price;
    public ArrayList<String> city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bid_bidder);
        id=new ArrayList<String>();
        farmer=new ArrayList<String>();
        bidder=new ArrayList<String>();
        veg=new ArrayList<String>();
        qty=new ArrayList<String>();
        grade=new ArrayList<String>();
        price=new ArrayList<String>();
        city=new ArrayList<String>();

        l=(ListView)findViewById(R.id.list_bid_bidder);
        dm=new DataManager(this);
        Cursor cursor=dm.get_rows_after_price_entry_bidder(bidder_login.bidder_name);

        while(cursor.moveToNext())
        {
            id.add(cursor.getString(0));
            farmer.add(cursor.getString(1));
            bidder.add(cursor.getString(2));
            veg.add(cursor.getString(3));
            qty.add(cursor.getString(4));
            grade.add(cursor.getString(5));
            price.add(cursor.getString(6));
            city.add(cursor.getString(7));
            C_Adapter c=new C_Adapter(this,id,farmer,bidder,veg,qty,grade,price,city);
            l.setAdapter(c);
        }

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long ids) {
                base_price=price.get(position);
                current_bidder=bidder.get(position);
                current_farmer=farmer.get(position);
                current_veg=veg.get(position);
                current_qty=qty.get(position);
                current_grade=grade.get(position);
                current_city=city.get(position);
                current_id=id.get(position);
                startActivity(new Intent(ListBidBidder.this,BidderNegotiate.class));
            }
        });
    }
}
class C_Adapter extends ArrayAdapter<String>
{
    Context c;

    ArrayList<String> id;
    ArrayList<String> farmer;
    ArrayList<String> bidder;
    ArrayList<String> veg;
    ArrayList<String> qty;
    ArrayList<String> grade;
    ArrayList<String> price;
    ArrayList<String> city;

    public C_Adapter(@NonNull Context context, ArrayList<String> id, ArrayList<String> farmer, ArrayList<String> bidder, ArrayList<String> veg, ArrayList<String> qty, ArrayList<String> grade, ArrayList<String> price, ArrayList<String> city) {
        super(context, R.layout.single_row_seeoffer,R.id.textView23,farmer);
        this.id=id;
        this.farmer=farmer;
        this.bidder=bidder;
        this.veg=veg;
        this.qty=qty;
        this.grade=grade;
        this.price=price;
        this.city=city;
        this.c=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater l_inf= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=l_inf.inflate(R.layout.single_row_seeoffer,parent,false);
//        TextView t1=(TextView) v.findViewById(R.id.textView22);
        TextView t2=(TextView) v.findViewById(R.id.textView23);
        TextView t3=(TextView) v.findViewById(R.id.textView24);
        TextView t4=(TextView) v.findViewById(R.id.textView25);
        TextView t5=(TextView) v.findViewById(R.id.textView26);
        TextView t6=(TextView) v.findViewById(R.id.textView27);
        TextView t7=(TextView) v.findViewById(R.id.textView28);
        TextView t8=(TextView) v.findViewById(R.id.textView29);

//        t1.setText(id.get(position));
        t2.setText(farmer.get(position));
        t3.setText(bidder.get(position));
        t4.setText(veg.get(position));
        t5.setText(qty.get(position));
        t6.setText(grade.get(position));
        t7.setText(price.get(position));
        t8.setText(city.get(position));
        return v;
    }
}