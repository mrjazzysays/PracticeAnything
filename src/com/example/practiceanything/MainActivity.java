package com.example.practiceanything;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		CalendarView yes = new CalendarView(null);
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
                
                DateFormat df = new SimpleDateFormat("EEE dd/MM/yyyy");
                for (int i = 0; i < 7; i++) {
                    System.out.println(df.format(c.getTime()));
                    c.add(Calendar.DATE, 1);
                }
				
				TextView tv = (TextView)findViewById(R.id.displayDate);
				TextView tv2 = (TextView)findViewById(R.id.currentDay);
				String day2 = Integer.toString(dayOfMonth);
				String year2= Integer.toString(year);
				String month2 = Integer.toString(month);
				int firstDayOfWeek = cal.getFirstDayOfWeek();
				
				
				Toast.makeText(MainActivity.this, "Month: " +month2+ "\n"+
													"Day:" +dayOfWeek+ "\n"+ 
												   "Year: " +year2+ "\n", Toast.LENGTH_LONG).show();
				
				
				tv.setText(month2+" " +day2+ " " +year2);
				
				String fdow = Integer.toString(firstDayOfWeek);
				tv2.setText(fdow);
				
				
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
