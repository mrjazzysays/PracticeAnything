package com.example.practiceanything;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
		
		List<String> userList = db.getAllUsers();
		List<String> testing = new ArrayList<String>();
		
		testing.add("no");
		final ArrayAdapter<String> aa = new ArrayAdapter<String>(this, R.layout.simple_list_item, userList);
		lv.setAdapter(aa);
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final String userDelete = et.getText().toString();
				Toast.makeText(WeekActivity.this, userDelete, Toast.LENGTH_LONG).show();
				db.deleteUser(userDelete);
				aa.notifyDataSetChanged();
				lv.invalidateViews();
				db.getAllUsers();
			}
		});
		
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final String userAdd = et2.getText().toString();
				Toast.makeText(WeekActivity.this, userAdd, Toast.LENGTH_LONG).show();
				db.addUser(userAdd, "lastname");
				aa.notifyDataSetChanged();
				lv.invalidateViews();
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
