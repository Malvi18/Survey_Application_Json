package com.example.registerentrysir.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.registerentrysir.db.DBConstants.COL_ID;
import static com.example.registerentrysir.db.DBConstants.CREATE;
import static com.example.registerentrysir.db.DBConstants.DBNAME;
import static com.example.registerentrysir.db.DBConstants.TABLE_NAME;

/**
 * Created by Dell on 05-01-2018.
 */

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    /*public boolean insertData(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, username);
        contentValues.put(COL_PASS, password);

        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }*/
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_NAME, null);
        return cursor;
    }

    public int removeProduct(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String selection=COL_ID+" = ?";
        String[] selectionsArgs={String.valueOf(id)};
        int count=db.delete(TABLE_NAME,selection,selectionsArgs);
        return count;
    }

}
