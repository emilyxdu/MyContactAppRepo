package com.example.emily.mycontactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

/**
 * Created by emily on 5/15/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

        public static final String DATABASE_NAME = "Contact.db";
        public static final String TABLE_NAME = "Contact_table";
        public static final String COL_1 = "ID";
        public static final String COL_2 = "NAME";
        public static final String COL_3 = "AGE";
        public static final String COL_4 = "PHONE";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, 2);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, AGE TEXT, PHONE TEXT)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }


        public boolean  insertData(String name, String age, String phone){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_2, name);
            contentValues.put(COL_3, age);
            contentValues.put(COL_4, phone);

            long result = db.insert(TABLE_NAME, null, contentValues);
            if(result == -1) return false;
            else return true;
        }


        public Cursor getAllData() {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
            return res;
        }


    public Cursor  findData(EditText find){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " WHERE NAME =?", new String[] {find.getText().toString()});
        return res;

        /*while(res.moveToNext()) {
            if (find.equals(res.getString(1))) {
                StringBuffer buffer = new StringBuffer();
                buffer.append("ID " + res.getString(0) + "\n");
                buffer.append("Name " + res.getString(1) + "\n");
                buffer.append("Age " + res.getString(2) + "\n");
                buffer.append("Phone Number " + res.getString(3) + "\n");
                return buffer;
            }
        }
        return null;*/
    }



}
