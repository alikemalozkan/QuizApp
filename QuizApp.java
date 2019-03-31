/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizapp;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JFrame;

public class QuizApp {
    
    //ADDING FRAME and ADJUSTING PROPERTIES
    public static JFrame frame;
    public static void main(String[] args) {
        frame = new JFrame("QuizApp");
        frame.setLayout(new FlowLayout());
        frame.getContentPane().setBackground(Color.white);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(200,60,880,640);
        //frame.setUndecorated(true);
        frame.setResizable(false);
        frame.add(new mainPanel());
        frame.setVisible(true);
        

    }
    
}
