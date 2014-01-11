package com.example.practiceanything;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
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
		
		Button btn = (Button)findViewById(R.id.deleteUsername);
		Button btn2 = (Button)findViewById(R.id.addUser);
		
		final List<String> userList = db.getAllUsers();
		
		final DBHelperEventLog db2 = new DBHelperEventLog(this);
		db2.addUserTask("Jeff");
		db2.updateLastAddedTask("2014", "01", "10");
		
		final ArrayAdapter<String> aa = new ArrayAdapter<String>(this, R.layout.simple_list_item, userList);
		lv.setAdapter(aa);
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final String userDelete = et.getText().toString();
				Toast.makeText(WeekActivity.this, userDelete, Toast.LENGTH_LONG).show();
				db.deleteUser(userDelete);
//				final ArrayAdapter<String> aa2 = new ArrayAdapter<String>(WeekActivity.this, R.layout.simple_list_item, userList);
				aa.clear();
				final List<String> userList2 = db.getAllUsers();
				aa.addAll(userList2);
				//print userList
//				lv2.setAdapter(aa);
//				String[] userArr = new String[userList.size()];
//				userArr = userList.toArray(userArr);
//			    for(String s : userArr)
//			        System.out.println(s);
////				aa.notifyDataSetChanged();
//				runOnUiThread(new Runnable() {
//			        @Override
//			        public void run() {
//			               aa.notifyDataSetChanged();
//			        }
//			    });
				db.getAllUsers();
			}
		});
		
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final String userAdd = et2.getText().toString();
				Toast.makeText(WeekActivity.this, userAdd, Toast.LENGTH_LONG).show();
				db.addUser(userAdd, "lastname");
				aa.clear();
				final List<String> userList2 = db.getAllUsers();
				aa.addAll(userList2);
//				lv.setAdapter(aa);
//				aa.notifyDataSetChanged();
				
				db.getAllUsers();
			}
		});
		

		
		
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
