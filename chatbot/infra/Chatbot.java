package chatbot.infra;

import chatbot.component.DomainClassifier;
import chatbot.component.WeatherIntentClassifier;
import chatbot.component.FoodIntentClassifier;

public class Chatbot {
	
	private String userName = "YOUR NAME HERE";
	private String botName = "BOT NAME HERE";
	
	//=====Code Added for Assignment 3 (Language Understanding) Begins=====
	
		//one domain classifier
		private DomainClassifier nowDomainClassifier;
		
		//each domain has one intent classifier 
		private WeatherIntentClassifier weatherIntentClassifier;
		private FoodIntentClassifier foodIntentClassifier;
	
	//=====Code Added for Assignment 3 (Language Understanding) Ends=====
	
	
	public Chatbot(String userName, String botName) {
		
		this.userName = userName;
		this.botName = botName;
		
		//=====Code Added for Assignment 3 (Language Understanding) Begins=====
		
			this.nowDomainClassifier = new DomainClassifier();
			this.weatherIntentClassifier = new WeatherIntentClassifier();
			this.foodIntentClassifier = new FoodIntentClassifier();
		
		//=====Code Added for Assignment 3 (Language Understanding) Ends=====
		
	}
	
	/*
	 * Task 3: Add a response in Chatbot.java to respond to user message
	 * 
	 * Please modify the getResponse() method in the Chatbot class to respond
	 * to three or more different user messages meaningfully. I provided one
	 * example in the getResponse().
	 */
	
	public String getResponse(String nowInputText) {
		
		
		//=====Code Added for Assignment 3 (Language Understanding) Begins=====
		
		System.out.println("--------------");
		System.out.println("User Message: "+nowInputText);
		String nowDomain = nowDomainClassifier.getLabel(nowInputText);  //return domain name 
		System.out.println("Domain: "+nowDomain);
		String nowIntent = "";
		
		// Logic: if domain is not "Other", it will point to weather or food 
		if(!nowDomain.equals("Other")) {//in-domain message
					
			if(nowDomain.equals("Food")) {//Food domain
				nowIntent = foodIntentClassifier.getLabel(nowInputText);
			}else if(nowDomain.equals("Weather")) {//Weather domain
				nowIntent = weatherIntentClassifier.getLabel(nowInputText);
			}else {//this shouldn't happen
				System.err.println("Domain name is incorrect!");
				System.exit(1);
				return null;
			}
		}else {//out-of-domain message. 
			return "This message is out of the domains of the chatbot.";
		}
		
		System.out.println("Intent: "+nowIntent);
		String nowResponse = "Domain = "+nowDomain+"; Intent = "+nowIntent;
		
		return nowResponse;
		
		//=====Code Added for Assignment 3 (Language Understanding) Ends=====
		
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBotName() {
		return botName;
	}

	public void setBotName(String botName) {
		this.botName = botName;
	}

	
	
	

}
