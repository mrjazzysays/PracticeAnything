package com.example.practiceanything;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

public class WeekActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_week);
		
		TextView tv = (TextView)findViewById(R.id.listWeek);
		ListView lv = (ListView)findViewById(R.id.listView1);
		
		
		Intent intent = getIntent();
		String listweek = intent.getExtras().getString("listweek");
		tv.setText(listweek);
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.week, menu);
		return true;
	}

}
