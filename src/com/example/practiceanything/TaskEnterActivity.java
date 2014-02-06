package com.example.practiceanything;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TaskEnterActivity extends Activity {
	double hoursWorked = 0;
	Map<String, String> testMap = new HashMap();
	String strHoursWorked = String.valueOf(hoursWorked);
	DBHelper db = new DBHelper(this);


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_enter);
		
		db.getFullRecordsFromEventLog("jeff", "1", "1", "1");
		
		final TextView tv = (TextView)findViewById(R.id.dayMod);
		final TextView tv2 = new TextView(getBaseContext());
		
		final Button addTaskbtn = (Button)findViewById(R.id.addTask);
		final Button lessHoursbtn = (Button)findViewById(R.id.lessHours);
		final Button submitTasksbtn = (Button)findViewById(R.id.submitTasks);
		
		final RelativeLayout enterTaskRL = (RelativeLayout)findViewById(R.id.activityTaskEnterRL);
		final LinearLayout enterTaskLL = (LinearLayout)findViewById(R.id.activityEnterTaskEnterMainLL);
		final ListView addTaskLV = (ListView)findViewById(R.id.addTaskList);
		
		Intent intent = getIntent();
		final String lastRowName = intent.getExtras().getString("lastRowName");
		final String scheduleFor = lastRowName + "'s Schedule For ";
		
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
		
		
		if (mondayMod!=null) {
			tv.setText(scheduleFor + "Monday, " + mondayMonth +"-" + mondayDay + "-" + mondayYear);
			final String year = mondayYear;
			final String month = mondayMonth;
			final String day = mondayDay;
			} else {
			System.out.println("mondayMod has NOTHING");
			}
		
		if (tuesdayMod!=null) {
			System.out.println("tuesdayMod has stuff in it");
		} else {
			System.out.println("tuesdayMod has NOTHING");
		} 
		
		ArrayList<String> testList = new ArrayList<String>();
		String[] otherList = {"array1","array2"};
		testList.addAll(Arrays.asList(otherList));
		
		testList.add("work");
		final ArrayAdapter<String> aa = new ArrayAdapter<String>(this, R.layout.taskview, R.id.taskName, testList);
		addTaskLV.setAdapter(aa);
		
		addTaskLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				String itemPosition = String.valueOf(addTaskLV.getItemAtPosition(arg2));
				System.out.println(itemPosition);
				testMap.put(itemPosition, strHoursWorked);
				System.out.println("Really doe: " + testMap.get(itemPosition));
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
		
		

		

		
		addTaskbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				testMap.put("yes", "no");
			
				
			}
		});
		
		submitTasksbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			    LinearLayout addNoteLayout = (LinearLayout) findViewById(R.id.pleaseWorkLL);
			    TextView textViewHopefully = (TextView) addNoteLayout.getChildAt(0);
			    String currentDay =  tv.getText().toString();
			    String[] splitString = currentDay.split(" ");
			    String datePart = splitString[4];
			    String[] splitDate = datePart.split("-");
			    
			    String monthPart = splitDate[0];
			    String dayPart = splitDate[1];
			    String yearPart = splitDate[2];		
			    System.out.println("Current day is: " + currentDay + ", Date is: " + datePart + "\n" + 
			    		"Month: " + monthPart + " Day: " + dayPart + " Year: " + yearPart
			    		);
			    
				int listNumber = aa.getCount();
			    int listViewCount = addNoteLayout.getChildCount();
			    int positionInList = addTaskLV.getFirstVisiblePosition();
			    int listViewChildCount = addTaskLV.getChildCount();
				
			    String listCount = String.valueOf(listNumber);
			    
				System.out.println(listCount);
//				printMap(testMap);
						    
			    System.out.println("Textview listed: " + textViewHopefully.getText().toString());
			    
			    for (int i=0; i<listViewChildCount;i++) {
				    LinearLayout LL = (LinearLayout) addTaskLV.getChildAt(positionInList + i);
				    TextView nextRow = (TextView)LL.findViewById(R.id.taskName);
				    TextView hourNumber = (TextView)LL.findViewById(R.id.hourNumber);
				    String taskname = nextRow.getText().toString();
				    String hourAmount = hourNumber.getText().toString();
				    float duration = Float.parseFloat(hourAmount);
				    System.out.println("Row " + i + ": " + taskname + " // Hours: " + hourAmount);
				    testMap.put(taskname, hourAmount);
				    db.insertCompleteTasknameIntoEventlog(taskname, duration, lastRowName, yearPart, monthPart, dayPart);
			    }
			    
			    
			    printMap(testMap);
			    
			    System.out.println("Listview layout childcount of items: " + String.valueOf(listViewCount));
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
         int vwChildCount = vwParentRow.getChildCount();
        final TextView child = (TextView)vwParentRow.getChildAt(0);
        final TextView hourNumber = (TextView)vwParentRow.getChildAt(2);
        
        final Button subtractBtnChild = (Button)vwParentRow.getChildAt(1);
        final Button addBtnChild = (Button)vwParentRow.getChildAt(3);
        
        System.out.println("Selected list item child count: " + String.valueOf(vwChildCount));
//        btnChild.setText(hourNumber.getText());
        
        
        subtractBtnChild.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final ArrayList<String> subtractHourList = new ArrayList<String>();
				hoursWorked-=.5;
				String rowTaskName = (String) child.getText();
				String strHoursWorked = String.valueOf(hoursWorked);
				
				
				testMap.put(rowTaskName, strHoursWorked);
				String valueOfRowInHoursWorked = testMap.get(rowTaskName);
				
				System.out.println(strHoursWorked);
				System.out.println(rowTaskName + " has a value of: " + valueOfRowInHoursWorked);
				System.out.println("array1: " + testMap.get(rowTaskName));
				System.out.println("array2: " + testMap.get("array2"));
				System.out.println(rowTaskName + " has a value of: " + valueOfRowInHoursWorked);
				subtractHourList.add(strHoursWorked);
				
				String lastRowNumber = db.getLastRowNumber();
				String lastRowName = db.getLastRowUserName();
				db.updateTasknameIntoEventlog(rowTaskName);
//				String lastTaskname = db.getLastTaskname(); // need to insert username somehow before querying it
				
				
				String lastRowYear = db.getLastRowYear();
				String lastRowMonth = db.getLastRowMonth();
				String lastRowDay = db.getLastRowDay();
				
				
//				
////				if (rowTaskName.equals(lastRowName) && mondayDay.equals(day) && mondayMonth.equals(month) && mondayYear.equals(year)) {
////					System.out.println("wow, they're the same");
////				} else {
////					System.out.println("they ain't the same");
////				}
//				
////				db.addUserTask(lastRowName);
//				db.updateLastAddedTask(year, month, day);
				
				
				//if entered info = last info , then do not add another row! last username, date, and taskname match, then do not add another row
				 
				
				hourNumber.setText(strHoursWorked);
				
				db.updateTaskDuration(valueOfRowInHoursWorked);
				String lastRowDuration = db.getLastRowDuration();
				
				//make new record 
				
				//insert rowName into userTable. then valueOfRowInHoursWorked into duration
				//submit button: get names of all the tasks today, loop through that. then get all the values of all those tasks. then submit them all into database at current date
				System.out.println(
						"Last row: " + lastRowNumber + 
						" \nName: " + lastRowName + 
						" \nDuration: " + lastRowDuration + 
						" \nDate: "+ lastRowMonth + "-" + lastRowDay + "-" + lastRowYear + 
						"\n==========");
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
				String rowName = (String) child.getText();
				System.out.println("Row name: " + rowName);
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
				
				System.out.println(strHoursWorked);
				hourNumber.setText(strHoursWorked);
			}
		}); 
        
        int c = Color.RED;
        
        vwParentRow.setBackgroundColor(c); 
        vwParentRow.refreshDrawableState(); 		
	}
	
	public static void printMap(Map mp) {
	    Iterator it = mp.entrySet().iterator();
	    
	    if (mp.isEmpty()) {
	    	System.out.println("Map is empty.");
	    }
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        System.out.println(pairs.getKey() + " = " + pairs.getValue());
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	}
	
}
	
