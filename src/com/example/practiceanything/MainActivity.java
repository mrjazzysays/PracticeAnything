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
import android.util.MonthDisplayHelper;
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
				
                
                final String monday = list.get(list.size()-7);
                final String tuesday= list.get(list.size()-6);
                final String wednesday= list.get(list.size()-5);
                final String thursday= list.get(list.size()-4);
                final String friday= list.get(list.size()-3);
                final String saturday= list.get(list.size()-2);
                final String sunday = list.get(list.size()-1);
                
                

                
                TransferDays td = new TransferDays();
                final String mondayMod = td.getDayInfo(monday);
                final String tuesdayMod = td.getDayInfo(tuesday);
                final String wednesdayMod = td.getDayInfo(wednesday);
                final String thursdayMod = td.getDayInfo(thursday);
                final String fridayMod = td.getDayInfo(friday);
                final String saturdayMod = td.getDayInfo(saturday);
                final String sundayMod = td.getDayInfo(sunday);
                
                final String mondayDay = td.getDayOnlyInfo(monday);
                final String tuesdayDay = td.getDayOnlyInfo(tuesday);
                final String wednesdayDay = td.getDayOnlyInfo(wednesday);
                final String thursdayDay = td.getDayOnlyInfo(thursday);
                final String fridayDay = td.getDayOnlyInfo(friday);
                final String saturdayDay = td.getDayOnlyInfo(saturday);
                final String sundayDay = td.getDayOnlyInfo(sunday);
                
                final String mondayMonth = td.getMonthOnlyInfo(monday);
                final String tuesdayMonth = td.getMonthOnlyInfo(tuesday);
                final String wednesdayMonth = td.getMonthOnlyInfo(wednesday);
                final String thursdayMonth = td.getMonthOnlyInfo(thursday);
                final String fridayMonth = td.getMonthOnlyInfo(friday);
                final String saturdayMonth = td.getMonthOnlyInfo(saturday);
                final String sundayMonth = td.getMonthOnlyInfo(sunday);
                
                final String mondayYear = td.getYearOnlyInfo(monday);
                final String tuesdayYear = td.getYearOnlyInfo(tuesday);
                final String wednesdayYear = td.getYearOnlyInfo(wednesday);
                final String thursdayYear = td.getYearOnlyInfo(thursday);
                final String fridayYear = td.getYearOnlyInfo(friday);
                final String saturdayYear = td.getYearOnlyInfo(saturday);
                final String sundayYear = td.getYearOnlyInfo(sunday);
                
                final String listweek = "Week Selected: " + monday + " - " + sunday;
                
				TextView tv = (TextView)findViewById(R.id.displayDate);
				TextView tv2 = (TextView)findViewById(R.id.currentDay);
				String day2 = Integer.toString(dayOfMonth);
				String year2= Integer.toString(year);
				String month2 = Integer.toString(month);
				int firstDayOfWeek = cal.getFirstDayOfWeek();
				
				

				
				
				tv.setText(month2+" " +day2+ " " +year2);
				
				
				tv2.setText(listweek);
				final Intent i = new Intent(MainActivity.this, WeekActivity.class);
				i.putExtra("listweek", listweek);
				i.putExtra("mondayMod", mondayMod);
				i.putExtra("tuesdayMod", tuesdayMod);
				i.putExtra("wednesdayMod", wednesdayMod);
				i.putExtra("thursdayMod", thursdayMod);
				i.putExtra("fridayMod", fridayMod);
				i.putExtra("saturdayMod", saturdayMod);
				i.putExtra("sundayMod", sundayMod);
				
				i.putExtra("mondayDay", mondayDay);
				i.putExtra("tuesdayDay", tuesdayDay);
				i.putExtra("wednesdayDay", wednesdayDay);
				i.putExtra("thursdayDay", thursdayDay);
				i.putExtra("fridayDay", fridayDay);
				i.putExtra("saturdayDay", saturdayDay);
				i.putExtra("sundayDay", sundayDay);
				
				i.putExtra("mondayMonth", mondayMonth);
				i.putExtra("tuesdayMonth", tuesdayMonth);
				i.putExtra("wednesdayMonth", wednesdayMonth);
				i.putExtra("thursdayMonth", thursdayMonth);
				i.putExtra("fridayMonth", fridayMonth);
				i.putExtra("saturdayMonth", saturdayMonth);
				i.putExtra("sundayMonth", sundayMonth);
				
				i.putExtra("mondayYear", mondayYear);
				i.putExtra("tuesdayYear", tuesdayYear);
				i.putExtra("wednesdayYear", wednesdayYear);
				i.putExtra("thursdayYear", thursdayYear);
				i.putExtra("fridayYear", fridayYear);
				i.putExtra("saturdayYear", saturdayYear);
				i.putExtra("sundayYear", sundayYear);
				
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

}
