package com.example.practiceanything;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "db";
	private static final String TABLE_USERS = "users";
	private static final String KEY_ID = "'_id'";
	private static final String COLUMN_FIRSTNAME = "firstname";
	private static final String COLUMN_LASTNAME = "lastname";
	private static final String DATABASE_CREATE = "create table if not exists" + TABLE_USERS + 
			" (" + KEY_ID + " integer primary key autoincrement, " + COLUMN_FIRSTNAME + 
			" text, " + COLUMN_LASTNAME + " text);";
	
	//constructor
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(DATABASE_CREATE);
		} catch (SQLiteException e){
			Log.e("createFAIL", e.toString(), e);
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	void addUser(String string) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COLUMN_FIRSTNAME, "jeff");
		db.insert(TABLE_USERS, null, values);
		db.close();
	}
	
	public List<String> getAllUsers() {
		List<String> userList = new ArrayList<String>();
		String selectQuery = "SELECT * FROM " + TABLE_USERS + ";";
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);
		if (c.moveToFirst()) {
            do {
                String string = new String();
                string = c.getString(1);
                // Adding contact to list
                userList.add(string);
                System.out.println(string);
            } while (c.moveToNext());
        }
		return userList;
	}
	
}
