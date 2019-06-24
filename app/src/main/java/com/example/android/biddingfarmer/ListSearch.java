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

public class ListSearch extends AppCompatActivity {
    ListView l;
    DataManager dm;
    TextView t1;
    public ArrayList<String> veg;
    public ArrayList<String> user;
    public ArrayList<String> q;
    public ArrayList<String> g;
    public ArrayList<String> ids;
    static String id_for_priceentry;
    static String farmer_name_for_priceentry;
    static String veg_name_for_priceentry;
    static String qty_for_priceentry;
    static String grade_for_priceentry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_search);
        veg=new ArrayList<String>();
        user=new ArrayList<String>();
        q=new ArrayList<String>();
        g=new ArrayList<String>();
        ids=new ArrayList<String>();
        l=(ListView)findViewById(R.id.list_s) ;
        dm=new DataManager(this);
        Cursor cursor=dm.get_rows(biddermainpage.input_veg_name,biddermainpage.input_qty,biddermainpage.input_grade);

        while (cursor.moveToNext())
        {
            ids.add(cursor.getString(0));
            user.add(cursor.getString(1));
            veg.add(cursor.getString(2));
            q.add(cursor.getString(3));
            g.add(cursor.getString(4));
            Custom_adapter c=new Custom_adapter(this,ids,user,veg,q,g);
            l.setAdapter(c);
        }
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                id_for_priceentry=ids.get(position);
                farmer_name_for_priceentry=user.get(position);
                veg_name_for_priceentry=veg.get(position);
                qty_for_priceentry=q.get(position);
                grade_for_priceentry=g.get(position);

                startActivity(new Intent(ListSearch.this,PriceEntryBidder.class));

            }
        });
    }
}
class Custom_adapter extends ArrayAdapter<String>
{
    Context c;

    ArrayList<String> ids;
    ArrayList<String> user;
    ArrayList<String> veg;
    ArrayList<String> q;
    ArrayList<String> g;

    public Custom_adapter(@NonNull Context context,ArrayList<String> ids,ArrayList<String> user, ArrayList<String> veg, ArrayList<String> q, ArrayList<String> g) {
        super(context, R.layout.single_row,R.id.textView19,g);
        this.ids=ids;
        this.user=user;
        this.veg=veg;
        this.q=q;
        this.g=g;
        this.c=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater l_inf= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=l_inf.inflate(R.layout.single_row,parent,false);
//        TextView t1=(TextView) v.findViewById(R.id.textView18);
        TextView t2=(TextView) v.findViewById(R.id.textView14);
        TextView t3=(TextView) v.findViewById(R.id.textView16);
        TextView t4=(TextView) v.findViewById(R.id.textView17);
        TextView t5=(TextView) v.findViewById(R.id.textView19);

//        t1.setText(id.get(position));
        t2.setText(user.get(position));
        t3.setText(veg.get(position));
        t4.setText(q.get(position));
        t5.setText(g.get(position));
        return v;
    }
}
