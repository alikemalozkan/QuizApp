/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizapp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class QuestionList {
    	
        //variables
        private FileReader read;
        private ArrayList<Question> questions;
        

        //constructor
        public QuestionList(String url){
            questions = new ArrayList<Question>();
            createQuestion(url);
            
        }
        //getRandom method to get random questions
        public Question getRandom(){
            int size = questions.size();
            int random = (int) (Math.random()*size);
            Question temp;
            temp = questions.get(random);
            questions.remove(random);
            return temp;
        }
	//getter for question 
        public Question getQuestion(int i)
        {
            return questions.get(i);
        }
        //create question method by using txt file
	public void createQuestion(String url){
		
                try {
			read = new FileReader(url);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
                 Scanner scan = new Scanner (read);
                
                int counter = 0;
                while(scan.hasNextLine())
                {
                  
                    String ques = null;
                    String[] choice = new String[4];
                    String answer = null;
                     
                    for(int i = 0 ; i < 6 ; i++)
                    {
                        if(i == 0)
                    {
                        ques = scan.nextLine();
                        
                    }
                    else if (i  == 1){
                        choice[0] = scan.nextLine();
                    }
                    else if (i == 2){
                        choice[1] = scan.nextLine();
                    }
                    else if (i == 3){
                        choice[2] = scan.nextLine();
                    }
                    else if (i == 4){
                        choice[3] = scan.nextLine();
                    }
                    else if (i== 5){
                        answer = scan.nextLine();
                    }
                    

                    }
                   questions.add(new Question(ques,answer,choice));
                    counter++;
}
}}