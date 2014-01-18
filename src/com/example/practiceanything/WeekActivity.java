package com.example.practiceanything;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class WeekActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_week);
		
		final DBHelper db = new DBHelper(this);
		
		TextView tv = (TextView)findViewById(R.id.listWeek);
		
		final ListView lv = (ListView)findViewById(R.id.listView1);
				
		final EditText et = (EditText)findViewById(R.id.editText1);
		final EditText et2 = (EditText)findViewById(R.id.editText2);
		
		Button deleteUserbtn = (Button)findViewById(R.id.deleteUsername);
		Button addUserbtn = (Button)findViewById(R.id.addUser);
		Button mondayBtn = (Button)findViewById(R.id.mondayBtn);
		Button tuesdayBtn = (Button)findViewById(R.id.tuesdayBtn);
		Button wednesdayBtn = (Button)findViewById(R.id.wednesdayBtn);
		Button thursdayBtn = (Button)findViewById(R.id.thursdayBtn);
		Button fridayBtn = (Button)findViewById(R.id.fridayBtn);
		Button saturdayBtn = (Button)findViewById(R.id.saturdayBtn);
		Button sundayBtn = (Button)findViewById(R.id.sundayBtn);
		
		final List<String> userList = db.getAllUsers();
			
		final ArrayAdapter<String> aa = new ArrayAdapter<String>(this, R.layout.simple_list_item, userList);
		lv.setAdapter(aa);
		
		Intent intent = getIntent();
		String listweek = intent.getExtras().getString("listweek");
		tv.setText(listweek);
		
		final String mondayMod = intent.getExtras().getString("mondayMod");
		final String tuesdayMod = intent.getExtras().getString("tuesdayMod");
		final String wednesdayMod = intent.getExtras().getString("wednesdayMod");
		final String thursdayMod = intent.getExtras().getString("thursdayMod");
		final String fridayMod = intent.getExtras().getString("fridayMod");
		final String saturdayMod = intent.getExtras().getString("saturdayMod");
		final String sundayMod = intent.getExtras().getString("sundayMod");
		
		final String mondayDay = intent.getExtras().getString("mondayDay");
		final String tuesdayDay = intent.getExtras().getString("tuesdayDay");
		final String wednesdayDay = intent.getExtras().getString("wednesdayDay");
		final String thursdayDay = intent.getExtras().getString("thursdayDay");
		final String fridayDay = intent.getExtras().getString("fridayDay");
		final String saturdayDay = intent.getExtras().getString("saturdayDay");
		final String sundayDay = intent.getExtras().getString("sundayDay");
		
		final String mondayMonth = intent.getExtras().getString("mondayMonth");
		final String tuesdayMonth = intent.getExtras().getString("tuesdayMonth");
		final String wednesdayMonth = intent.getExtras().getString("wednesdayMonth");
		final String thursdayMonth = intent.getExtras().getString("thursdayMonth");
		final String fridayMonth = intent.getExtras().getString("fridayMonth");
		final String saturdayMonth = intent.getExtras().getString("saturdayMonth");
		final String sundayMonth = intent.getExtras().getString("fridayMonth");
		
		final String mondayYear = intent.getExtras().getString("mondayYear");
		final String tuesdayYear = intent.getExtras().getString("tuesdayYear");
		final String wednesdayYear = intent.getExtras().getString("wednesdayYear");
		final String thursdayYear = intent.getExtras().getString("thursdayYear");
		final String fridayYear = intent.getExtras().getString("fridayYear");
		final String saturdayYear = intent.getExtras().getString("saturdayYear");
		final String sundayYear = intent.getExtras().getString("sundayYear");
		
		mondayBtn.setText(mondayMod);
		tuesdayBtn.setText(tuesdayMod);
		wednesdayBtn.setText(wednesdayMod);
		thursdayBtn.setText(thursdayMod);
		fridayBtn.setText(fridayMod);
		saturdayBtn.setText(saturdayMod);
		sundayBtn.setText(sundayMod);
		
		deleteUserbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final String userDelete = et.getText().toString();
				Toast.makeText(WeekActivity.this, userDelete, Toast.LENGTH_LONG).show();
				db.deleteUser(userDelete);
				
				aa.clear();
				final List<String> userList2 = db.getAllUsers();
				aa.addAll(userList2);
				//print userList

				db.getAllUsers();
			}
		});
		
		addUserbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final String userAdd = et2.getText().toString();
				Toast.makeText(WeekActivity.this, userAdd, Toast.LENGTH_LONG).show();
				db.addUser(userAdd);
				aa.clear();
				final List<String> userList2 = db.getAllUsers();
				aa.addAll(userList2);

				
				db.getAllUsers();
			}
		});
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				final TextView tv = (TextView)findViewById(R.id.usernameCurrentSchedule);
				tv.setText(((TextView) view).getText() + "'s Schedule");
				String userSelected =(String) (lv.getItemAtPosition(position));
				db.addUserTask(userSelected);
				tv.setVisibility(View.VISIBLE);
				String lastRowName = db.getLastRowUserName();
				System.out.println(lastRowName);
			}
		});
		
		mondayBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				db.updateLastAddedTask(mondayYear, mondayMonth, mondayDay);
				Intent i = new Intent(WeekActivity.this, TaskEnterActivity.class);
				i.putExtra("mondayMod", mondayMod);
				i.putExtra("mondayDay", mondayDay);
				i.putExtra("mondayMonth", mondayMonth);
				i.putExtra("mondayYear", mondayYear);
				startActivity(i);
				
				
				
			}
		});
		
		tuesdayBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(WeekActivity.this, TaskEnterActivity.class);
				i.putExtra("tuesdayMod", tuesdayMod);
				startActivity(i);
				
			}
		});
		

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.week, menu);
		return true;
	}

}
