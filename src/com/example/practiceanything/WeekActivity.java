package com.example.practiceanything;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class WeekActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_week);
		
		final DBHelper db = new DBHelper(this);
		
		final TextView tv2 = (TextView)findViewById(R.id.listWeek);
		
		
		final ListView lv = (ListView)findViewById(R.id.addTaskList);
		
		final ListView mondayListview = (ListView)findViewById(R.id.mondayList);
		final ListView tuesdayListview = (ListView)findViewById(R.id.tuesdayList);
		final ListView wednesdayListview = (ListView)findViewById(R.id.wednesdayList);
		final ListView thursdayListview = (ListView)findViewById(R.id.thursdayList);
		final ListView fridayListview = (ListView)findViewById(R.id.fridayList);
		final ListView saturdayListview = (ListView)findViewById(R.id.saturdayList);
		final ListView sundayListview = (ListView)findViewById(R.id.sundayList);
				

		
		final Button addUserDialog = (Button)findViewById(R.id.addUsersBtn);
		final Button deleteUserDialog = (Button)findViewById(R.id.deleteUsersBtn);
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
		final String listweek = intent.getExtras().getString("listweek");
		
	
		
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
		
		addUserDialog.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder aud = new AlertDialog.Builder(WeekActivity.this);
				final EditText input = new EditText(WeekActivity.this);
				
				
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				        LinearLayout.LayoutParams.FILL_PARENT,
				        LinearLayout.LayoutParams.FILL_PARENT);
				input.setLayoutParams(lp);
				aud.setTitle("Enter new username:");
				aud.setView(input);
				aud.setPositiveButton("Add", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						final String userAdd = input.getText().toString();
						Toast.makeText(WeekActivity.this, "User added: " + userAdd, Toast.LENGTH_LONG).show();
						db.addUser(userAdd);
						aa.clear();
						final List<String> userList2 = db.getAllUsers();
						aa.addAll(userList2);

						
						db.getAllUsers();
						
						
					}
				});
				aud.show();
				
				
			}
		});
		
		deleteUserDialog.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder aud = new AlertDialog.Builder(WeekActivity.this);
				final EditText input = new EditText(WeekActivity.this);
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				        LinearLayout.LayoutParams.FILL_PARENT,
				        LinearLayout.LayoutParams.FILL_PARENT);
				input.setLayoutParams(lp);
				aud.setTitle("Enter username to delete:");
				aud.setView(input);
				
				aud.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						final String userDelete = input.getText().toString();
						Toast.makeText(WeekActivity.this, "User deleted: " + userDelete, Toast.LENGTH_LONG).show();
						db.deleteUser(userDelete);
						
						aa.clear();
						final List<String> userList2 = db.getAllUsers();
						aa.addAll(userList2);
						//print userList

						db.getAllUsers();
						
					}
				});
				aud.show();
			}
		});
				
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				final TextView tv = (TextView)findViewById(R.id.usernameCurrentSchedule);
				final TextView tv3 = (TextView)findViewById(R.id.selectUserToViewSchedule);
				tv.setText(((TextView) view).getText() + "'s Schedule");
				String userSelected =(String) (lv.getItemAtPosition(position));
				db.addUserTask(userSelected);
				tv.setVisibility(View.VISIBLE);
				
				tv2.setText(listweek);
				tv2.setVisibility(View.VISIBLE);
				tv3.setVisibility(View.INVISIBLE);
				
				final List<String> mondayList = db.getFullRecordsFromEventLog(userSelected, mondayYear, mondayMonth, mondayDay);
				final List<String> tuesdayList = db.getFullRecordsFromEventLog(userSelected, tuesdayYear, tuesdayMonth, tuesdayDay);
				final List<String> wednesdayList = db.getFullRecordsFromEventLog(userSelected, wednesdayYear, wednesdayMonth, wednesdayDay);
				final List<String> thursdayList = db.getFullRecordsFromEventLog(userSelected, thursdayYear, thursdayMonth, thursdayDay);
				final List<String> fridayList = db.getFullRecordsFromEventLog(userSelected, fridayYear, fridayMonth, fridayDay);
				final List<String> saturdayList = db.getFullRecordsFromEventLog(userSelected, saturdayYear, saturdayMonth, saturdayDay);
				final List<String> sundayList = db.getFullRecordsFromEventLog(userSelected, sundayYear, sundayMonth, sundayDay);
				
				final ArrayAdapter<String> mondayAA = new ArrayAdapter<String>(WeekActivity.this, R.layout.weekview_list_item, mondayList);
				final ArrayAdapter<String> tuesdayAA = new ArrayAdapter<String>(WeekActivity.this, R.layout.weekview_list_item, tuesdayList);
				final ArrayAdapter<String> wednesdayAA = new ArrayAdapter<String>(WeekActivity.this, R.layout.weekview_list_item, wednesdayList);
				final ArrayAdapter<String> thursdayAA = new ArrayAdapter<String>(WeekActivity.this, R.layout.weekview_list_item, thursdayList);
				final ArrayAdapter<String> fridayAA = new ArrayAdapter<String>(WeekActivity.this, R.layout.weekview_list_item, fridayList);
				final ArrayAdapter<String> saturdayAA = new ArrayAdapter<String>(WeekActivity.this, R.layout.weekview_list_item, saturdayList);
				final ArrayAdapter<String> sundayAA = new ArrayAdapter<String>(WeekActivity.this, R.layout.weekview_list_item, sundayList);
				
				mondayListview.setAdapter(mondayAA);
				tuesdayListview.setAdapter(tuesdayAA);
				wednesdayListview.setAdapter(wednesdayAA);
				thursdayListview.setAdapter(thursdayAA);
				fridayListview.setAdapter(fridayAA);
				saturdayListview.setAdapter(saturdayAA);
				sundayListview.setAdapter(sundayAA);

			}
		});
		
		mondayBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				db.updateLastAddedTask(mondayYear, mondayMonth, mondayDay);
				String lastRowName = db.getLastRowUserName();
				
				Intent i = new Intent(WeekActivity.this, TaskEnterActivity.class);
				i.putExtra("lastRowName", lastRowName);
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
				db.updateLastAddedTask(tuesdayYear, tuesdayMonth, tuesdayDay);
				String lastRowName = db.getLastRowUserName();
				
				Intent i = new Intent(WeekActivity.this, TaskEnterActivity.class);
				i.putExtra("lastRowName", lastRowName);
				i.putExtra("tuesdayMod", tuesdayMod);
				i.putExtra("tuesdayDay", tuesdayDay);
				i.putExtra("tuesdayMonth", tuesdayMonth);
				i.putExtra("tuesdayYear", tuesdayYear);
				startActivity(i);
			}
		});
		
		wednesdayBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				db.updateLastAddedTask(wednesdayYear, wednesdayMonth, wednesdayDay);
				String lastRowName = db.getLastRowUserName();
				
				Intent i = new Intent(WeekActivity.this, TaskEnterActivity.class);
				i.putExtra("lastRowName", lastRowName);
				i.putExtra("wednesdayMod", wednesdayMod);
				i.putExtra("wednesdayDay", wednesdayDay);
				i.putExtra("wednesdayMonth", wednesdayMonth);
				i.putExtra("wednesdayYear", wednesdayYear);
				startActivity(i);
				
			}
		});
		
		thursdayBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				db.updateLastAddedTask(thursdayYear, thursdayMonth, thursdayDay);
				String lastRowName = db.getLastRowUserName();
				
				Intent i = new Intent(WeekActivity.this, TaskEnterActivity.class);
				i.putExtra("lastRowName", lastRowName);
				i.putExtra("thursdayMod", thursdayMod);
				i.putExtra("thursdayDay", thursdayDay);
				i.putExtra("thursdayMonth", thursdayMonth);
				i.putExtra("thursdayYear", thursdayYear);
				startActivity(i);
				
			}
		});
		
		fridayBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				db.updateLastAddedTask(fridayYear, fridayMonth, fridayDay);
				String lastRowName = db.getLastRowUserName();
				
				Intent i = new Intent(WeekActivity.this, TaskEnterActivity.class);
				i.putExtra("lastRowName", lastRowName);
				i.putExtra("fridayMod", fridayMod);
				i.putExtra("fridayDay", fridayDay);
				i.putExtra("fridayMonth", fridayMonth);
				i.putExtra("fridayYear", fridayYear);
				startActivity(i);
				
			}
		});
		
		saturdayBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				db.updateLastAddedTask(saturdayYear, saturdayMonth, saturdayDay);
				String lastRowName = db.getLastRowUserName();
				
				Intent i = new Intent(WeekActivity.this, TaskEnterActivity.class);
				i.putExtra("lastRowName", lastRowName);
				i.putExtra("saturdayMod", saturdayMod);
				i.putExtra("saturdayDay", saturdayDay);
				i.putExtra("saturdayMonth", saturdayMonth);
				i.putExtra("saturdayYear", saturdayYear);
				startActivity(i);
				
			}
		});
		
		sundayBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				db.updateLastAddedTask(sundayYear, sundayMonth, sundayDay);
				String lastRowName = db.getLastRowUserName();
				
				Intent i = new Intent(WeekActivity.this, TaskEnterActivity.class);
				i.putExtra("lastRowName", lastRowName);
				i.putExtra("sundayMod", sundayMod);
				i.putExtra("sundayDay", sundayDay);
				i.putExtra("sundayMonth", sundayMonth);
				i.putExtra("sundayYear", sundayYear);
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
