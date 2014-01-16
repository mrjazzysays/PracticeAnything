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
	private static final String TABLE_EVENTLOG = "eventlog2";
	
	private static final String COLUMN_TASKNAME = "taskname";
	private static final String COLUMN_DURATION = "duration";
	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_YEAR = "year";
	private static final String COLUMN_MONTH = "month";
	private static final String COLUMN_DAY = "day";
	private static final String TABLE_USERS_CREATE = "create table if not exists users('_id' integer primary key autoincrement, firstname text);";
	private static final String TABLE_EVENTLOG_CREATE = "create table if not exists eventlog2('_id' integer primary key autoincrement, taskname text, duration int, name text, year text, month text," +
			"day text);";
	//constructor
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(TABLE_USERS_CREATE);
			db.execSQL(TABLE_EVENTLOG_CREATE);
		} catch (SQLiteException e){
			Log.e("createFAIL", e.toString(), e);
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	void addUser(String firstname) {
		
//		Toast.makeText(MainActivity.this, "yes", Toast.LENGTH_LONG).show();
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COLUMN_FIRSTNAME, firstname);
//		values.put(COLUMN_LASTNAME, lastname);
		db.insert(TABLE_USERS, null, values);
		
		
		try { 
			Cursor c = db.rawQuery("SELECT * FROM users;", null);
			if (c!=null) {
				c.moveToLast();
				String lastUser = c.getString(1).toString();
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
                string = c.getString(1);
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
	
	public void addUserTask(String username) { 
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME, username);
		db.insert(TABLE_EVENTLOG, null, values);
		
		try {
			Cursor c = db.rawQuery("select * from eventlog2;", null);
			if (c!=null) {
				c.moveToLast();
				String usernamePrint = c.getString(3).toString();
				int rowNum = c.getInt(0);
				String rowPrint = String.valueOf(rowNum);
				System.out.println("Row #: " + rowPrint + "\n" + "Name added: " + usernamePrint);
				c.close();
				db.close();
			}

		} catch (SQLiteException e) {
			Log.e("adduserFAIL", e.toString(), e);
		}
		
	}
	
	public void updateLastAddedTask(String year, String month, String day){
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery("select * from eventlog2;", null);
		c.moveToLast();
		int rowNum = c.getInt(0);
		final String lastRowId = String.valueOf(rowNum);
		System.out.println("Last row: " + lastRowId);		

		ContentValues values = new ContentValues();
		values.put(COLUMN_YEAR, year);
		values.put(COLUMN_MONTH, month);
		values.put(COLUMN_DAY, day);
		db.update(TABLE_EVENTLOG, values, KEY_ID + " = " + lastRowId, null);

		Cursor cc = db.rawQuery("select * from eventlog2;", null);
		cc.moveToLast();
		int rowNum2 = cc.getInt(0);
		final String lastRowId2 = String.valueOf(rowNum2);
		String yearPrint = cc.getString(4).toString();
		String monthPrint = cc.getString(5).toString();
		String dayPrint = cc.getString(6).toString();
		System.out.println("Row: " + lastRowId2 + " Year: " + yearPrint + " Month: " + monthPrint + " Day: " + dayPrint);
		db.close();
	}
	
	
	
}
