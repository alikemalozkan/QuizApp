/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//needts to change
package quizapp;

public class Question {
        //variables
	public  String question;
	public  String answer;
	public  String[] choices;
	
        //constructor
        public Question(String question, String answer, String[] choices){
            this.question = question;
            this.answer = answer;
            this.choices = choices;
            
        }
	//getters
	public String getAnswer(){
		
		return answer;
	}
	
	public String getQuestion(){
		
		return question;
	}
	
	public String[] getChoices(){
		
		return choices;
	}
	
	
	//setters
	public void setAnswer(String ans){
		
		answer = ans;
	}
	
	public void setQuestion( String ques){
		
		question = ques;
	}
	
	public void setChoices( String[] cho ){
		
		choices = cho;
	}
	
	
        //toString to test
        public String toString()
        {
            return getQuestion() + "\n"  + getChoices()[0]
                    + "\n"  + getChoices()[1]+ "\n"  + getChoices()[2]+ "\n"  + getChoices()[3];
        }

}
