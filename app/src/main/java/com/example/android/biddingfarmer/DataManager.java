package com.example.android.biddingfarmer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DataManager extends SQLiteOpenHelper {

    public static String db_name = "FARMER_BID.DB";

    public static String db_table_name = "FARMER_LOGIN";
    public static String col1 = "ID";
    public static String col2 = "NAME";
    public static String col3 = "PHONE";
    public static String col4 = "USER";
    public static String col5 = "PASS";
    public static String col6 = "TYPE";

    public static String db_farmer_bid_table = "FARMER_BID";
    public static String c1 = "farmer_name";
    public static String c2 = "veg_name";
    public static String c3 = "qty";
    public static String c4 = "grade";
    public static String c5 = "ID";

    public static String db_start_bid = "START_BID";
    public static String col_1 = "ID";
    public static String col_2 = "farmer";
    public static String col_3 = "bidder";
    public static String col_4 = "veg_name";
    public static String col_5 = "qty";
    public static String col_6 = "grade";
    public static String col_7 = "price";
    public static String col_8 = "city";
    public static String col_9 = "status";
    public static String col_10 = "bid_qty";
    public static String col_11 = "bid_cost";
    public static String col_12 = "side";

    public DataManager(Context context) {
        super(context, db_name, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + db_table_name + "(id integer primary key autoincrement,name text,phone integer(9),user text,pass text,type text)");
        db.execSQL("CREATE TABLE " + db_farmer_bid_table + "(id integer primary key autoincrement,farmer_name text,veg_name text,qty integer(9),grade text)");
        db.execSQL("CREATE TABLE " + db_start_bid + "(id integer primary key autoincrement,farmer text,bidder text,veg_name text,qty integer(9) NOT NULL CHECK (qty >=0), grade text,price integer(9),city text,status text NOT NULL DEFAULT '' ,bid_qty integer(9) DEFAULT 0,bid_cost integer(9) DEFAULT 0,side text DEFAULT 'B')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if EXISTS " + db_table_name);
        db.execSQL("Drop table if EXISTS " + db_farmer_bid_table);
        db.execSQL("Drop table if EXISTS " + db_start_bid);

        onCreate(db);
    }

    public boolean insert_data(String name, String phone, String user, String pass, String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put(col2, name);
        val.put(col3, phone);
        val.put(col4, user);
        val.put(col5, pass);
        val.put(col6, type);
        long temp = db.insert(db_table_name, null, val);
        if (temp == -1)
            return false;
        return true;
    }

    public boolean insert_bid(String farmer_name, String veg_name, String qty, String grade) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put(c1, farmer_name);
        val.put(c2, veg_name);
        val.put(c3, qty);
        val.put(c4, grade);
        long temp = db.insert(db_farmer_bid_table, null, val);
        if (temp == -1)
            return false;
        return true;
    }

    public Cursor get_rows(String veg_name, String qty, String grade) {
        SQLiteDatabase db = this.getReadableDatabase();
        //       Log.d("This",veg_name);
        //        Cursor cursor=db.rawQuery("select ?,?,?,? from " +db_table_name+" where "+c2+"=? and "+c4+"=? and "+c3+">=?",new String[]{c1,c2,c3,c4,veg_name,grade,qty});
        Cursor c = db.rawQuery("select * from " + db_farmer_bid_table + " where " + c2 + "=? and " + c4 + "=? and " + c3 + ">=?", new String[]{veg_name, grade, qty});
        return c;
    }

    public boolean validate_password(String user, String pass, String type) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from " + db_table_name + " where " + col4 + "=? and " + col5 + "=? and " + col6 + "=?", new String[]{user, pass, type});
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    public boolean start_bid_insert(String farmer, String bidder, String veg_name, String qty, String grade, String price, String city, String bid_qty) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(col_2, farmer);
        values.put(col_3, bidder);
        values.put(col_4, veg_name);
        values.put(col_5, qty);
        values.put(col_6, grade);
        values.put(col_7, price);
        values.put(col_8, city);
        values.put(col_10, bid_qty);
        long temp = db.insert(db_start_bid, null, values);
        if (temp == -1)
            return false;
        return true;

    }

    public Cursor get_rows_after_price_entry_farmer(String farmer) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + db_start_bid + " where " + col_2 + "=? and " + col_9 + "<>? and " + col_9 + "<>? and " + col_9 + "<>? and " + col_9 + "<>?", new String[]{farmer, "EndByBidder", "EndByFarmer", "AcceptByFarmer", "AcceptByBidder"});
        return cursor;
    }

    public Cursor get_rows_after_price_entry_bidder(String bidder) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + db_start_bid + " where " + col_3 + "=? and " + col_9 + "<>? and " + col_9 + "<>? and " + col_9 + "<>? and " + col_9 + "<>? and " + col_9 + "<>? ", new String[]{bidder, "EndByFarmer", "EndByBidder", "AcceptByBidder", "", "AcceptByFarmer"});
        return cursor;

    }

    public Cursor get_final_rows(String bidder) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + db_start_bid + " where " + col_3 + "=? and (" + col_9 + "=? or " + col_9 + "=?)", new String[]{bidder, "AcceptByFarmer", "AcceptByBidder"});
        return cursor;
    }

    public Cursor get_final_farmer_rows(String farmer) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + db_start_bid + " where " + col_2 + "=? and (" + col_9 + "=? or " + col_9 + "=?)", new String[]{farmer, "AcceptByFarmer", "AcceptByBidder"});
        return cursor;
    }

    public int update_status(String status, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(col_9, status);
        int count = db.update(db_start_bid, values, col_1 + "=?", new String[]{id});

        return count;

    }

    public void updateCost(String id, String bid_cost) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(col_11, bid_cost);
        int count = db.update(db_start_bid, values, col_1 + "=?", new String[]{id});

    }

    public String getCost(String id) {
        String result = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select bid_cost from " + db_start_bid + " where " + col_1 + "=?", new String[]{id});
        cursor.move(0);
        if (cursor.moveToNext()) {
            result = cursor.getString(0);
        }
        Log.d("RESULT BOI:", result);
        return result;
    }

    public String getSide(String id)
    {
        String result = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select side from " + db_start_bid + " where " + col_1 + "=?", new String[]{id});
        cursor.move(0);
        if (cursor.moveToNext()) {
            result = cursor.getString(0);
        }
        return result;
    }
    public void updateSide(String id,String side)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(col_12,side);
        int count = db.update(db_start_bid, values, col_1 + "=?", new String[]{id});

    }
    public void updateQty(String id,String farmer_name,String veg_name,String grade) {
        String result;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor1 = db.rawQuery("select bid_qty from " + db_start_bid + " where " + col_1 + "=?", new String[]{id});
        Cursor cursor = db.rawQuery("select qty from " + db_start_bid + " where " + col_1 + "=?", new String[]{id});
        Cursor cursor2 = db.rawQuery("select id from " + db_farmer_bid_table + " where " + c1 + "=? and "+c2+"=? and "+c4+"=?", new String[]{farmer_name,veg_name,grade});


        cursor.move(0);
        cursor1.move(0);
        cursor2.move(0);
        if (cursor.moveToNext() && cursor1.moveToNext() && cursor2.moveToNext()) {
            result = cursor.getString(0);
            System.out.println("Difference is :"+(cursor.getInt(0)-cursor1.getInt(0)));
        }
        System.out.println("So ID of FBid is:"+cursor2.getString(0));
        ContentValues values = new ContentValues();
        values.put(col_5,cursor.getInt(0) - cursor1.getInt(0));

        ContentValues values1 = new ContentValues();
        values1.put(c3, cursor.getInt(0) - cursor1.getInt(0));
        int count1 = db.update(db_start_bid, values, col_1 + "=?", new String[]{id});
        int count2=db.update(db_farmer_bid_table,values1,c5+"=?",new String[]{cursor2.getString(0)});
    }

    public String getStatus(String id) {
        String stat;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select status from " + db_start_bid + " where " + col_1 + "=?", new String[]{id});
        cursor.move(0);
        if (cursor.moveToNext()) {
            stat = cursor.getString(0);
            Log.d("Status", stat);

            return stat;

        } else
            return "NO";
    }
}
