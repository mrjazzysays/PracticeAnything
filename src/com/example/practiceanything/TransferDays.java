package com.example.practiceanything;

public class TransferDays {
	public String getDayInfo(String day) {
		String formattedDay = Character.toString(day.charAt(0)) + Character.toString(day.charAt(1)) +Character.toString(day.charAt(2)) +"\n" +Character.toString(day.charAt(7)) +  Character.toString(day.charAt(8));
		return formattedDay;
	}
	
	public String getDayOnlyInfo(String day) {
		String formattedDayOnly = Character.toString(day.charAt(7)) +  Character.toString(day.charAt(8));
		return formattedDayOnly;
	}
	
	public String getMonthOnlyInfo(String month) {
		String formattedMonthOnly = Character.toString(month.charAt(4)) +  Character.toString(month.charAt(5));
		return formattedMonthOnly;
	}
	
	public String getYearOnlyInfo(String year) {
		String formattedYearOnly = Character.toString(year.charAt(10)) +  Character.toString(year.charAt(11)) +  Character.toString(year.charAt(12)) +  Character.toString(year.charAt(13));
		return formattedYearOnly;
	}
}
