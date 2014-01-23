package com.example.practiceanything;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TaskView extends LinearLayout{

	public TaskView(Context context) {
		super(context);
//		setContentView(R.layout.activity_task_enter);
	}
	
	
	final TextView hoursAmountTv = new TextView(this.getContext());
	final LinearLayout ll = new LinearLayout(this.getContext());
	final TextView tasknameTv = new TextView(this.getContext());
	final Button subtractBtn = new Button(this.getContext());
	final Button addBtn = new Button(this.getContext());
	
	
	int i = 0;
	

	public void addButtonToLinearLayout(LinearLayout rl) {
		addBtn.setId(1);
		rl.addView(addBtn);
	}
	
	public void addSubButtonToLinearLayout(LinearLayout rl) {
		addBtn.setId(2);
		rl.addView(addBtn);
	}
	
	public void addLinearLayoutToLinearLayout(LinearLayout rl, String taskname, int id) {

		 
		subtractBtn.setText("-");
		subtractBtn.setId(id);
		addBtn.setText("+");
		addBtn.setId(id);
		tasknameTv.setText(taskname);
		tasknameTv.setId(id);
//		hoursAmountTv.setText("1");
		ll.addView(tasknameTv);
		ll.addView(subtractBtn);
		ll.addView(hoursAmountTv);
		ll.addView(addBtn);
		ll.setOrientation(HORIZONTAL);
		ll.setId(id);
		rl.addView(ll);
		
		subtractBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				i--;
				String hourSubtract = String.valueOf(i);
				hoursAmountTv.setText(hourSubtract);
				
			}
		});
		
		addBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				i++;
				String hourAdd = String.valueOf(i);
				hoursAmountTv.setText(hourAdd);
				
			}
		});
		
		//create a coordinate that allows placing others below it
	}
	

	
	public void addHours(){

	}
	
	
	
}
