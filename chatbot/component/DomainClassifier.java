/*
 * chatbot.component is added for Assignment 3 (Language Understanding)
 * 
 * DomainClassifier.java is added for Assignment 3 (Language
 * Understanding)
 */

package chatbot.component;

import java.util.Arrays;

public class DomainClassifier {
	
	private static String[] domainDictionary;
	
	public DomainClassifier() {
		initializeDomainDictionary();
	}
	
	/**
	 * Create a dictionary of domains  
	 */
	private void initializeDomainDictionary() {
		
		//list all the domains - orders matter 
		domainDictionary = new String[]{"Other", "Weather", "Food"};
		
		//create the display string
		System.out.print("Domains: (");
		for(int i=0;i<domainDictionary.length;i++) {
			System.out.print(domainDictionary[i]);
			if(i!=domainDictionary.length-1) {
				System.out.print(", ");
			}
		}
		System.out.println(")");
		
	}
	
	/**
	 * Calculate the given meesage's score for each domain. The chatbot will
	 * select the domain with the *highest* score.
	 * 
	 * The initial score of each domain is 0.0.
	 * 
	 * @param nowInputText	An English message sent from the user.
	 * @return				An Double array that contains the score of each
	 * 						domain.
	 */
	private Double[] calculateDomainScores(String nowInputText) {
		
		//DO NOT change the following 4 lines
		//initiate all the scores to 0.0 
		Double[] scoreArray = new Double[domainDictionary.length];
		for(int i=0;i<scoreArray.length;i++) {
			scoreArray[i] = Double.valueOf(0.0);
		}
		
		//The following is the part you need to modify.
		
		//============= Please Modify Here (begins) =============== 
		
		//Count key words in a small Weather dictionary
		String[] weatherDictionary = new String[] {"snow", "rain", "weather", "outside"};
		
		//Ji edit: Added tokenization by splitting the text into individual words 
		String[] inputWord = nowInputText.trim().toLowerCase().split("\\W");
		
	
		for (String weatherKeyword: weatherDictionary) {
			for(int i = 0; i < inputWord.length; i++) {
				//Ji's edit: removed indexOf(), instead compared the values of each input keyword and weather keyword 
				if(inputWord[i].compareTo(weatherKeyword) == 0) { 
					//{"Other", "Weather", "Food"}, so scoreArray[1] indicates the score for Weather domain
					scoreArray[1] = scoreArray[1].doubleValue()+1.0;
				}
			}
		}
		
		//Count key words in a small Food dictionary
		String[] foodDictionary = new String[] {"food", "eat", "hungry"};
		for(String foodKeyword: foodDictionary) {
			for(int j = 0; j < inputWord.length; j++) {
				//Ji's edit: removed indexOf(), instead compared the values of each input keyword and food keyword
				if(inputWord[j].compareTo(foodKeyword) == 0) {
					//{"Other", "Weather", "Food"}, so scoreArray[2] indicates the score for Food domain
					scoreArray[2] = scoreArray[2].doubleValue()+1.0;
				}
			}
		}
		
		//============= Please Modify Here (ends) =============== 
		
		//Do not change the following lines
		//Check before returning the scoreArray
		if(scoreArray.length!=domainDictionary.length) {
			System.err.println("The score array size does not equal to the domain array size.");
			System.exit(1);
		}
		for(Double nowValue: scoreArray) {
			if(nowValue==null) {
				System.err.println("The score array contains null values.");
				System.exit(1);
			}
		}
		return scoreArray;
	}
	


	/**
	 * Input:
	 * 	nowInputText: the message that the user sent to your chatbot
	 * 
	 * Output:
	 * 	the label (domain) name string
	 * 
	 * @param nowInputText	An English message sent from the user.
	 * @return 				The name of the domain.
	 * 
	 */
	public String getLabel(String nowInputText) {
		
		//get the score array
		Double[] intentScores = calculateDomainScores(nowInputText);
		
		//print the scores of each domain
		Double nowMaxScore = null;
		int nowMaxIndex = -1;
		System.out.print("Domain Scores: (");
		for(int i=0;i<intentScores.length;i++){
			System.out.print(intentScores[i].doubleValue());
			if(i!=intentScores.length-1) {
				System.out.print(", ");
			}
			if(nowMaxScore==null||nowMaxIndex==-1||intentScores[i].doubleValue()>nowMaxScore.doubleValue()) {
				nowMaxIndex = i;
				nowMaxScore = intentScores[i].doubleValue();
			}
		}
		System.out.println(")");
		
		return domainDictionary[nowMaxIndex];
	}

}
