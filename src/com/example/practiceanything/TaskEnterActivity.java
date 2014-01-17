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
		final String mondayMod = intent.getExtras().getString("mondayMod") + "day, " + intent.getExtras().getString("mondayDay");
		
		tv.setText(mondayMod);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.task_enter, menu);
		return true;
	}

}
