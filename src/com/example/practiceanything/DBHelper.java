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
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "db";
	private static final String TABLE_USERS = "users";
	private static final String KEY_ID = "_id";
	private static final String COLUMN_FIRSTNAME = "firstname";
	private static final String COLUMN_LASTNAME = "lastname";
	private static final String DATABASE_CREATE = "create table if not exists users('_id' integer primary key autoincrement, firstname text, lastname text);";
	
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

	void addUser(String firstname, String lastname) {
		
//		Toast.makeText(MainActivity.this, "yes", Toast.LENGTH_LONG).show();
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COLUMN_FIRSTNAME, firstname);
		values.put(COLUMN_LASTNAME, lastname);
		db.insert(TABLE_USERS, null, values);
		
		
		try { 
			Cursor c = db.rawQuery("SELECT * FROM users;", null);
			if (c!=null) {
				c.moveToLast();
				String lastUser = c.getString(1).toString() + " " + c.getString(2).toString();
				System.out.println("User added: " + lastUser);
//				Toast.makeText(haha, lastUser, Toast.LENGTH_LONG).show();
			} else {
				Context context = null;
				Toast.makeText(context, "fail", Toast.LENGTH_LONG).show();
			}

//			if( c != null && c.moveToFirst() ){
//				String tester = c.getString(1).toString();
//				String tester2 = c.getString(1).toString();
//				System.out.println(tester+tester2);
//			} else {
//				System.out.println("no");
//			}

			

		} catch (SQLiteException e ) {
			Log.e("addUSERfailed", e.toString(), e);
		}

		db.close();
	}
	
	public final List<String> getAllUsers() {
		final List<String> userList = new ArrayList<String>();
		String selectQuery = "SELECT * FROM " + TABLE_USERS + ";";
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);
		if (c.moveToFirst()) {
            do {
                String string = new String();
                string = c.getString(1) + " " + c.getString(2);
                // Adding contact to list
                userList.add(string);
                System.out.println(string);
            } while (c.moveToNext());
        }
		return userList;
	}
	
	public void deleteUser(String username) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_USERS, COLUMN_FIRSTNAME + "= ?", new String[]{username});
		System.out.println("User deleted: " + username);
		db.close();
		
	}
	
}
