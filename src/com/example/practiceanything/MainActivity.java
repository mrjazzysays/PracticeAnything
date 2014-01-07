package com.example.practiceanything;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final DBHelper db = new DBHelper(this);
//		try {
//			db.addUser("sharon", "wee" );
//			db.addUser("no", "yes");
//			db.getAllUsers();
//		} catch (SQLiteException e ) {
//			Log.e("fail", e.toString(), e);
//		}

		
//		db.getAllUsers();
		
//		final SQLiteDatabase newDB = openOrCreateDatabase("newDB", MODE_PRIVATE, null);
//		final String TABLE_NAME = "users";
//        try {
//        	newDB.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLE_NAME +" ('_id' integer primary key autoincrement, firstname text, lastname text);");
//        } catch (SQLiteException e) {
//        	Log.e("createTBLfailed", e.toString(), e);
//        }
//		
//        try {
//        	newDB.execSQL("INSERT INTO " + TABLE_NAME + "(firstname, lastname) VALUES('jeff', 'cedilla');");
//        } catch (SQLiteException e) {
//        	Log.e("insertTBLfailed", e.toString(), e);
//        }
//        
//        try {
//        	Cursor c = newDB.rawQuery("SELECT * FROM " + TABLE_NAME + "; ", null);
//        	c.moveToPosition(1);
//        	String usernameTest = c.getString(1).toString();
////        	Toast.makeText(MainActivity.this, usernameTest, Toast.LENGTH_LONG).show();
//        } catch (SQLiteException e) {
//        	Log.e("cursorFAIL", e.toString(), e);
//        }
        
        
		final CalendarView cal = (CalendarView) findViewById(R.id.calendarView1);
		cal.setOnDateChangeListener(new OnDateChangeListener() {
			
			@Override
			public void onSelectedDayChange(CalendarView view, int year, int month,
					int dayOfMonth) {
				
				long cviewdate = cal.getDate();
                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(cviewdate);
                c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
                
                List<String> list = new ArrayList();
                
                
                
                DateFormat df = new SimpleDateFormat("EEE MM/dd/yyyy");
                for (int i = 0; i < 7; i++) {
                    String weekday = (df.format(c.getTime()));
                    c.add(Calendar.DATE, 1);
                    list.add(weekday);
                    System.out.println(weekday);

                }
				
                
                String monday = list.get(list.size()-7);
                String sunday = list.get(list.size()-1);
                final String listweek = "Week Selected: " + monday + " - " + sunday;
                
				TextView tv = (TextView)findViewById(R.id.displayDate);
				TextView tv2 = (TextView)findViewById(R.id.currentDay);
				String day2 = Integer.toString(dayOfMonth);
				String year2= Integer.toString(year);
				String month2 = Integer.toString(month);
				int firstDayOfWeek = cal.getFirstDayOfWeek();
				
				
//				Toast.makeText(MainActivity.this, "Month: " +month2+ "\n"+
//													"Day:" +dayOfWeek+ "\n"+ 
//								 				   "Year: " +year2+ "\n", Toast.LENGTH_LONG).show();
				
				
				tv.setText(month2+" " +day2+ " " +year2);
				
				
				tv2.setText(listweek);
				final Intent i = new Intent(MainActivity.this, WeekActivity.class);
				i.putExtra("listweek", listweek);
				
				Button btn = (Button)findViewById(R.id.goToWeek);
				btn.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						db.close();
						startActivity(i);
						
					}
				});
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
//	protected void onDestroy() {
//	    DBHelper db = new DBHelper(this);
//		super.onDestroy();
//	    if (db != null) {
//	        db.close();
//	    }
//
//	}
}
