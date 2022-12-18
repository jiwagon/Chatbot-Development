/*
 * SlotFiller.java is added for Assignment 4 (Language Understanding)
 */

package chatbot.component;

import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SlotFiller {

	/*
	 * Task 1: Extract slot values from the input user message
	 * 
	 * [Input] 
	 * One user message (e.g., "What's the weather in State College?")
	 * 
	 * [Output]
	 * A hash table that contains a set of (key, value) tuples, where the "key"
	 * is the name of the slot (e.g., "location") and "value" is the extracted
	 * value (e.g., "State College").
	 * 
	 */
	public Hashtable<String, String> extractSlotValues(String nowInputText) {
		
		//initialize the hash table. You do not need to change this line of code.
		Hashtable<String, String> result = new Hashtable<String, String>();
		
		//-------------- Modify Code Here (Assignment 4) Begins ---------------
		
		Pattern nowPattern = Pattern.compile("\\b\\w+@PSU\\.EDU\\b");
	    Matcher nowMatcher = nowPattern.matcher(nowInputText.trim().toUpperCase());
	    while (nowMatcher.find()) {
	    	String nowMatchedSubstring = nowMatcher.group();
	    	System.out.println("PSU Email found: " + nowMatchedSubstring);
	    	//adding value to the result hash table
			result.put("Email", nowMatchedSubstring);
	  	}
		
		
		//modify the following code to implement your own slot extractor
		String[] dayOfWeekList = new String[] {"FRIDAY", "MONDAY", "SATURDAY", "SUNDAY", "THURSDAY", "TUESDAY", "WEDNESDAY"};
		for(String nowDayOfWeek: dayOfWeekList) {
			if(nowInputText.toUpperCase().contains(nowDayOfWeek)) {
				//adding value to the result hash table
				result.put("DayOfWeek", nowDayOfWeek);
			}
		}
		
		//modify the following code to implement your own slot extractor
		String[] relativeDateList = new String[] {"TODAY", "TOMORROW", "YESTERDAY"};
		for(String nowRelativeDate: relativeDateList) {
			if(nowInputText.toUpperCase().contains(nowRelativeDate)) {
				//adding value to the result hash table
				result.put("RelativeDate", nowRelativeDate);
			}
		}
		
		//-------------- Modify Code Here (Assignment 4) Ends ---------------
		
		//return the result hash table. You do not need to change this part of code.
		return result;
		
	}

}
