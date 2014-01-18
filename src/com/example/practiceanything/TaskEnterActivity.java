package com.example.practiceanything;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class TaskEnterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_enter);
		
		TextView tv = (TextView)findViewById(R.id.dayMod);
		
		Intent intent = getIntent();
		final String scheduleFor = "Schedule For ";
		final String mondayMod = intent.getExtras().getString("mondayMod");
		final String tuesdayMod = intent.getExtras().getString("tuesdayMod");
		
		final String mondayDay = intent.getExtras().getString("mondayDay");
		
		final String mondayMonth = intent.getExtras().getString("mondayMonth");
		
		final String mondayYear = intent.getExtras().getString("mondayYear");
		
		if (mondayMod!=null) {
			tv.setText(scheduleFor + "Monday, " + mondayMonth +"-" + mondayDay + "-" + mondayYear);
			} else {
			System.out.println("mondayMod has NOTHING");
			}
		
		if (tuesdayMod!=null) {
			System.out.println("tuesdayMod has stuff in it");
		} else {
			System.out.println("tuesdayMod has NOTHING");
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.task_enter, menu);
		return true;
	}

}
