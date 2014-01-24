package com.example.practiceanything;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TaskEnterActivity extends Activity {
	double hoursWorked = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_enter);
		
		final TextView tv = (TextView)findViewById(R.id.dayMod);
		final TextView tv2 = new TextView(getBaseContext());
		
		final Button addTaskbtn = (Button)findViewById(R.id.addTask);
		final Button lessHoursbtn = (Button)findViewById(R.id.lessHours);
		
		final RelativeLayout enterTaskRL = (RelativeLayout)findViewById(R.id.activityTaskEnterRL);
		final LinearLayout enterTaskLL = (LinearLayout)findViewById(R.id.activityEnterTaskEnterMainLL);
//		final LinearLayout addTaskLL = new LinearLayout(getBaseContext());
		final ListView addTaskLV = (ListView)findViewById(R.id.addTaskList);
		
//		LayoutInflater inflator = LayoutInflater.from(getBaseContext());
//		View v = inflator.inflate(R.layout.taskview, null);
//		enterTaskLL.addView(v);
		
		ArrayList<String> testList = new ArrayList<String>();
		String[] otherList = {"array1","array2"};
		testList.addAll(Arrays.asList(otherList));
		
		testList.add("work");
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this, R.layout.taskview, R.id.taskName, testList);
		addTaskLV.setAdapter(aa);
		
		addTaskLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				System.out.println("does it work?!");
				String itemPosition = String.valueOf(addTaskLV.getItemAtPosition(arg2));
				System.out.println(itemPosition);
			
				
			}
		
		});
		

		
//		lessHoursbtn.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				System.out.println("-");
//				
//			}
//		});	
//		TaskView taskview = new TaskView(this);
//		taskview.addButtonToLinearLayout(enterTaskLL);
//		taskview.addSubButtonToLinearLayout(enterTaskLL);
//		taskview.addLinearLayoutToLinearLayout(enterTaskLL, "New DNA Isolation",1);
//		List<String> list = new List[]();
//		taskview.addLinearLayoutToLinearLayout(enterTaskLL, "Accessioning",2);
		
//		View populatesaddTaskLV = LayoutInflater.from(getBaseContext()).inflate(R.layout.taskview, null);
//		enterTaskLL.addView(populatesaddTaskLV);
		
		
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

	public void onSubtractHourClick(View v){
		System.out.println("-");
        //get the row the clicked button is in
		 
		
        final LinearLayout vwParentRow = (LinearLayout)v.getParent();
         
        final TextView child = (TextView)vwParentRow.getChildAt(0);
        final TextView hourNumber = (TextView)vwParentRow.getChildAt(2);
        
        final Button subtractBtnChild = (Button)vwParentRow.getChildAt(1);
        final Button addBtnChild = (Button)vwParentRow.getChildAt(3);
        
        final ArrayList<String> hourList = new ArrayList<String>();
//        btnChild.setText(hourNumber.getText());
        
        
        subtractBtnChild.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				hoursWorked-=.5;
				String strHoursWorked = String.valueOf(hoursWorked);
				System.out.println(strHoursWorked);
				hourList.add(strHoursWorked);
				hourNumber.setText(strHoursWorked);
				
			}
		}); 
		
        addBtnChild.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				hoursWorked+=.5;
				String strHoursWorked = String.valueOf(hoursWorked);
				System.out.println(strHoursWorked);
				hourNumber.setText(strHoursWorked);
			}
		}); 
        
        int c = Color.CYAN;
        
        vwParentRow.setBackgroundColor(c); 
        vwParentRow.refreshDrawableState(); 
	}

	public void onAddHourClick(View v){
		System.out.println("+");
		final LinearLayout vwParentRow = (LinearLayout)v.getParent();
        
        final TextView child = (TextView)vwParentRow.getChildAt(0);
        final TextView hourNumber = (TextView)vwParentRow.getChildAt(2);
        
        final Button subtractBtnChild = (Button)vwParentRow.getChildAt(1);
        final Button addBtnChild = (Button)vwParentRow.getChildAt(3);
        
        final ListView addTaskLV = (ListView)findViewById(R.id.addTaskList);
//        btnChild.setText(hourNumber.getText());
        
        
        subtractBtnChild.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				hoursWorked-=.5;
				String strHoursWorked = String.valueOf(hoursWorked);
				System.out.println(strHoursWorked);
				hourNumber.setText(strHoursWorked);
//				addTaskLV.setOnItemClickListener(new OnItemClickListener() {
//
//					@Override
//					public void onItemClick(AdapterView<?> arg0, View arg1,
//							int arg2, long arg3) {
//						// TODO Auto-generated method stub
//
//					}
//				});
			}
		}); 
		
        addBtnChild.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				hoursWorked+=.5;
				String strHoursWorked = String.valueOf(hoursWorked);
				System.out.println(strHoursWorked);
				hourNumber.setText(strHoursWorked);
			}
		}); 
        
        int c = Color.RED;
        
        vwParentRow.setBackgroundColor(c); 
        vwParentRow.refreshDrawableState(); 		
	}
	
}
