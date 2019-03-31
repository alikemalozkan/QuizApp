/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizapp;

//comment for change

public class Player {
    //variables
    private String playerName;
    private int point;
	//constructor
	public Player(String name){
		playerName = name;
		point = 0;
	}
	//getters
	public String getName(){
		return playerName;
	}
  	int a=5; //new code
	
	public int getPoint(){
		return point;
	}
	//setters
	public void setName(String pn){
		playerName = pn;
	}
	
	public void setPoint(int p){
		point += p;
	}

}
