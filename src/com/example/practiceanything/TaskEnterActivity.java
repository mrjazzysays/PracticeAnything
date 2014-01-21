package com.example.practiceanything;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TaskEnterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_enter);
		
		final TextView tv = (TextView)findViewById(R.id.dayMod);
		final TextView tv2 = new TextView(getBaseContext());
		final Button addTaskbtn = (Button)findViewById(R.id.addTask);
		final RelativeLayout enterTaskRL = (RelativeLayout)findViewById(R.id.activityTaskEnterRL);
		final LinearLayout enterTaskLL = (LinearLayout)findViewById(R.id.activityEnterTaskEnterMainLL);
		final LinearLayout addTaskLL = new LinearLayout(getBaseContext());
		

		TaskView taskview = new TaskView(this);
//		taskview.subtractHours();
		taskview.addLinearLayoutToLinearLayout(enterTaskLL, "New DNA Isolation");
		taskview.addLinearLayoutToLinearLayout(enterTaskLL, "Accessioning");
		
		
		Intent intent = getIntent();
		final String lastRowName = intent.getExtras().getString("lastRowName");
		final String scheduleFor = lastRowName + "'s Schedule For ";
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
		
		addTaskbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
			}
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.task_enter, menu);
		return true;
	}

}
