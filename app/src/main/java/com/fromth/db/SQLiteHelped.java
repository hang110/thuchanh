package com.fromth.db;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.fromth.model.Item;
import com.fromth.model.NumberTheLoai;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelped  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "thuchanh1.db";
    private static int DATABASE_VERSION = 1;

    public SQLiteHelped(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE items (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "name TEXT,item1 Text,item2 Text, item3 Text, item4 text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }


    //add
    public void addItem(Item i){
        String sql = "INSERT INTO items (name, item1, item2, item3, item4)"+
                "VALUES(?,?,?,?,?)";
        String[] args = {i.getName(),i.getItem1(), i.getItem2(),i.getItem3(),i.getItem4()};
        SQLiteDatabase st =getWritableDatabase();
        st.execSQL(sql,args);
    }

    public List<Item> getAll() {
        List<Item> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor rs = sqLiteDatabase.query("items",
                null, null, null,
                null, null, null);
        while ((rs != null) && (rs.moveToNext())) {
            int id= rs.getInt(0);
            String name = rs.getString(1);
            String item1 = rs.getString(2);
            String item2 = rs.getString(3);
            String item3 = rs.getString(4);
            String item4 = rs.getString(5);
            list.add(new Item(id,name,item1, item2, item3, item4));
        }
        return list;
    }

//sửa
    public void updateItem(Item i){
        String sql = "UPDATE items SET name=?, item1=?, item2=?, item3=?, item4=? WHERE id = ?";
        String[] args = {i.getName(), i.getItem1(), i.getItem2(),i.getItem3(),i.getItem4(), String.valueOf(i.getId())};
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql,args);
    }
//xoa
    public  void delete(int id)
    {
        String sql = "DELETE FROM items WHERE id=?";
        String[] args = {Integer.toString(id)};
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql, args);
    }
    public List<Item> searchByAlbum(String a) {
        List<Item> list = new ArrayList<>();
        String whereClause = "album like ?";
        String[] whereArgs = {a};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor rs = sqLiteDatabase.query("items",
                null, whereClause, whereArgs,
                null, null, null);
        while ((rs != null) && (rs.moveToNext())) {
            int id= rs.getInt(0);
            String name = rs.getString(1);
            String item1 = rs.getString(2);
            String item2 = rs.getString(3);
            String item3 = rs.getString(4);
            String item4 = rs.getString(5);
            list.add(new Item(id,name,item1, item2, item3, item4));
        }
        return list;
    }

    /*public List<NumberTheLoai> thongke() {
        List<NumberTheLoai> list = new ArrayList<NumberTheLoai>();
        String[] columns= {"theloai","Count(*) as songs"};
        String groupby ="theloai";
        String orderby="songs DESC";
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor rs = sqLiteDatabase.query("items",
                columns, null, null,
                groupby, null, orderby);
        while ((rs != null) && (rs.moveToNext())) {
            String theloai = rs.getString(0);
            int number = rs.getInt(1);
            NumberTheLoai n = new NumberTheLoai(theloai, number);
            list.add(n);
        }
        return list;
    }*/
}
