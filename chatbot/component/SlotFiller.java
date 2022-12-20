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
		
		String[] nowInputWords = nowInputText.trim().toUpperCase().split("[\\s]+");
		String[] cityList = new String[] {"LA", "NEW YORK CITY", "STATE COLLEGE","NYC", "LAS VEGAS"};
		for(String nowCity: cityList) {
			String[] nowCityWords = nowCity.trim().toUpperCase().split("[\\s]+");
			if(findPhrase(nowCityWords, nowInputWords)){
				//adding value to the result hash table
				result.put("Location", nowCity);
			}
		}
		
		String[] foodTypeList = new String[] {"PIZZA", "BURGER", "STEAK"};
		for(String nowFoodType: foodTypeList) {
			if(nowInputText.toUpperCase().contains(nowFoodType)) {
				//adding value to the result hash table
				result.put("FoodType", nowFoodType);
			}
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
		String[] nowRelativeList = new String[] {"TODAY", "TOMORROW", "YESTERDAY"};
		for(String nowRelativeDate: nowRelativeList) {
			if(nowInputText.toUpperCase().contains(nowRelativeDate)) {
				//adding value to the result hash table
				result.put("RelativeDate", nowRelativeDate);
			}
		}
		
		String[] patternArray = {"\\b\\w+\\s(PM|AM)\\b", "\\b\\s\\w+\\s(PM|AM)\\b"
				, "\\b\\w+(PM|AM)\\b", "([01]?[0-9]|2[0-3]):[0-5][0-9]"};		
		
		for(String nowPatternStr: patternArray) {
			Pattern nowPattern = Pattern.compile(nowPatternStr);
		//Pattern nowPattern = Pattern.compile("\\b\\w+\\s(PM|AM)\\b");
			    Matcher nowMatcher = nowPattern.matcher(nowInputText.trim().toUpperCase());
			    while (nowMatcher.find()) {
			    	String nowMatchedSubstring = nowMatcher.group();
			    	//adding value to the result hash table
					result.put("TimeOfTheDay", nowMatchedSubstring);
			  	}
			//-------------- Modify Code Here (Assignment 4) Ends ---------------
			//}
		//return the result hash table. You do not need to change this part of code.
		}
		return result;
		
	}
	
	private boolean findPhrase(String[] nowCityWords, String[] nowInputWords) {
		
		//iterate through each word in the sentence
		for(int i = 0; i < nowInputWords.length;i++) {
			if(allWordsMatchStartsWith(nowCityWords, nowInputWords, i)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean allWordsMatchStartsWith(String[] nowCityWords, String[] nowInputWords, int index) {
		for(int i = 0; i < nowCityWords.length;i++) {
			//If strings in nowInputWords doesn't match with strings in nowCityWords, function returns false
			if(!nowCityWords[i].equals(nowInputWords[index+i])) {
				return false;
			}
		}
		//if all strings matched, then function returns true
		return true;
	}

}
