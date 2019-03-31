/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizapp;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;


public class singlePlayer extends javax.swing.JPanel {

    /**
     * Creates new form singlePlayer
     */
    //variables
    private Player player;
    private Question temp;
    private int counter;
    public int countTime;
    private QuestionList easy;
    private QuestionList medium;
    private QuestionList hard;
    private  InfoSingle info;
    private int availableAnswer;
    private int answerCount;
    Timer timer= new Timer();
    TimerTask task;
    private int timeLimit;
    private boolean doubleAvailable,fiftyAvailable;
    private boolean extraAvailable,renewAvailable;
    
    //constructor
    public singlePlayer(Player player) {
        this.easy = new QuestionList("src/quizapp/questions/easy.txt");
        this.medium = new QuestionList("src/quizapp/questions/medium.txt");
        this.hard = new QuestionList("src/quizapp/questions/hard.txt");
        this.player = player;
        initComponents();
        this.counter = 0;
        this.info = new InfoSingle();
        this.info.setUserName(player.getName());
        this.info.setPoint(player.getPoint());
        askQuestion();
        QuizApp.frame.add(info);
        QuizApp.frame.revalidate();
        QuizApp.frame.repaint();
        this.doubleAvailable=true;
        this.renewAvailable=false;
        this.fiftyAvailable=true;
        this.extraAvailable = false;
        this.doubleButton.setVisible(doubleAvailable);
        this.renewButton.setVisible(renewAvailable);
        this.fiftyButton.setVisible(fiftyAvailable);
        this.extraButton.setVisible(extraAvailable);
        
        
    }
    //ask question method
    public void askQuestion(){
        availableAnswer =1;
        answerCount = 1;
        choice1.setVisible(true);
        choice2.setVisible(true);
        choice3.setVisible(true);
        choice4.setVisible(true);
        if(counter < 5){
            askQuestionEasy();
        }else if(counter>=5 && counter<10){
            askQuestionMedium();
        }else if(counter >=10 && counter<15){
            askQuestionHard();
        }
        if(counter==10){
            extraAvailable = true;
            extraButton.setVisible(extraAvailable);
        }
        if(counter==5){
            renewAvailable = true;
            renewButton.setVisible(renewAvailable);
        }
    }
    //ask question for easy questions
    public void askQuestionEasy(){
        timeLimit = 20;
        temp = easy.getRandom();
        countTime= 0;
        task = new TimerTask(){
            
            public void run(){
                if(countTime<timeLimit){
                countTime++;
                System.out.println(countTime);
                pBar.setValue(countTime);
                }else{
                    if (JOptionPane.showConfirmDialog(null, "Time is out! \n Your Score : "+player.getPoint()+"\n Would you like to play again?", "Time out!",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        reStart();
                    }
                    else {
                        System.exit(0);
                    }
                
                timer.cancel();
                }

            }
        };
        timer= new Timer();
        
        pBar.setMaximum(timeLimit);
        pBar.setMinimum(0);
        
        
        questionArea.setText(temp.getQuestion());
        choice1.setText(temp.getChoices()[0]);
        choice2.setText(temp.getChoices()[1]);
        choice3.setText(temp.getChoices()[2]);
        choice4.setText(temp.getChoices()[3]); 
        timer.scheduleAtFixedRate(task, 1000,1000);  //scheduleAtFixedRate(TimerTask task,long delay,long period)
        
 
    }
    //ask question for medium questions
    public void askQuestionMedium(){
        timeLimit = 30;
        temp = medium.getRandom();
        
        countTime= 0;
        task = new TimerTask(){
            
            public void run(){
                if(countTime<timeLimit){
                countTime++;
                pBar.setValue(countTime);
                }else{
                if (JOptionPane.showConfirmDialog(null, "Time is out! \n Your Score : "+player.getPoint()+"\n Would you like to play again?", "Time Out !",
            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                reStart();
            } else {
                System.exit(0);
            }
                this.cancel();
                }

            }
        };
        timer= new Timer();
        pBar.setMaximum(timeLimit);
        pBar.setMinimum(0);

       
        questionArea.setText(temp.getQuestion());
        choice1.setText(temp.getChoices()[0]);
        choice2.setText(temp.getChoices()[1]);
        choice3.setText(temp.getChoices()[2]);
        choice4.setText(temp.getChoices()[3]); 
        timer.scheduleAtFixedRate(task, 1000,1000);
 
 
    }
    //ask question for hard questions
    public void askQuestionHard(){
        timeLimit = 40;
        temp = hard.getRandom();
        
        countTime= 0;
        task = new TimerTask(){
            
            public void run(){
                if(countTime<timeLimit){
                countTime++;
                pBar.setValue(countTime);
                }else{
                if (JOptionPane.showConfirmDialog(null, "Time is out! \n Your Score : "+player.getPoint()+"\n Would you like to play again?", "Time out!",
            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                reStart();
            } else {
                System.exit(0);
            }
                this.cancel();
                }

            }
        };
        timer= new Timer();
        pBar.setMaximum(timeLimit);
        pBar.setMinimum(0);


        questionArea.setText(temp.getQuestion());
        choice1.setText(temp.getChoices()[0]);
        choice2.setText(temp.getChoices()[1]);
        choice3.setText(temp.getChoices()[2]);
        choice4.setText(temp.getChoices()[3]); 
        timer.scheduleAtFixedRate(task, 1000,1000);
      
    }
    //wrong answer method
    public void wrongAnswer(){

     
            if(answerCount<availableAnswer){
             answerCount++;   
            }else{
              
                timer.cancel();
                
                if (JOptionPane.showConfirmDialog(null, "Wrong Answer!\n Your Score : "+player.getPoint()+"\n Would you like to play again?", "Game over !",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    reStart();
                } else {
                    System.exit(0);
                }
            }
            
    }
    //correct answer method
    public void correctAnswer(){
       
        if(counter>=14){
            finalOfGame();
        }else{
            counter++;
            info.nextQuestion(counter);
            if(counter<=5)
                player.setPoint(50);
            if(counter>5 && counter<=10)
                player.setPoint(100);
            if(counter>10 && counter<=15)
                player.setPoint(150);
            timer.cancel();
            askQuestion();
            info.setPoint(player.getPoint());
        }
        
    }
    //restart method when game is over
    public void reStart(){
        
        QuizApp.frame.remove(this);
        QuizApp.frame.remove(info);
        QuizApp.frame.add(new singleName());
        QuizApp.frame.revalidate();
        QuizApp.frame.repaint();
        return;
        
    }
    //fifty-fiftyJoker joker
    public void fiftyJoker(){
        if(fiftyAvailable==true){
            fiftyAvailable=false;
            fiftyButton.setVisible(fiftyAvailable);
            int numberDeleted = 0;
            int previouslyDeleted = 5;
            while(numberDeleted < 2){
                int deletedAnswer =(int)(Math.random()*4);
                if(deletedAnswer!= previouslyDeleted){
                if(!temp.getChoices()[deletedAnswer].equals(temp.getAnswer())){
                    if(deletedAnswer == 0){
                        choice1.setVisible(false);
                        numberDeleted++;
                        previouslyDeleted = 0;
                    }else if(deletedAnswer == 1){
                        choice2.setVisible(false);
                        numberDeleted++;
                        previouslyDeleted = 1;
                    }else if(deletedAnswer == 2){
                        choice3.setVisible(false);
                        numberDeleted++;
                        previouslyDeleted = 2;
                    }else if(deletedAnswer == 3){
                        choice4.setVisible(false);
                        numberDeleted++;
                        previouslyDeleted = 3;
                    }
                }
                }
            }
            this.revalidate();
            this.repaint();
        }
    }
    //extra time joker
    public void extraTime(){
        if(extraAvailable==true){
            extraAvailable =false;
            extraButton.setVisible(extraAvailable);
            timeLimit = timeLimit*2;
            pBar.setMaximum(timeLimit);
        }
    }
    //new question joker
    public void newQuestion(){
        if(renewAvailable == true){
            renewAvailable = false;
            renewButton.setVisible(renewAvailable);

            if(counter < 5){

            temp = easy.getRandom();
            
            questionArea.setText(temp.getQuestion());
            choice1.setText(temp.getChoices()[0]);
            choice2.setText(temp.getChoices()[1]);
            choice3.setText(temp.getChoices()[2]);
            choice4.setText(temp.getChoices()[3]); 

            }else if(counter>=5 && counter<10){

                temp = medium.getRandom();
            
            questionArea.setText(temp.getQuestion());
            choice1.setText(temp.getChoices()[0]);
            choice2.setText(temp.getChoices()[1]);
            choice3.setText(temp.getChoices()[2]);
            choice4.setText(temp.getChoices()[3]); 

            }else if(counter >=10 && counter<15){

                temp = hard.getRandom();
            
            questionArea.setText(temp.getQuestion());
            choice1.setText(temp.getChoices()[0]);
            choice2.setText(temp.getChoices()[1]);
            choice3.setText(temp.getChoices()[2]);
            choice4.setText(temp.getChoices()[3]); 

            }
        }
    }
    //double answer joker
    public void doubleAnswer(){
        if(doubleAvailable==true){
            doubleAvailable = false;
            doubleButton.setVisible(doubleAvailable);
            availableAnswer++;
        }
    }
    //end of the game message
    public void finalOfGame(){
        
        timer.cancel();
        player.setPoint(150);
        if (JOptionPane.showConfirmDialog(null, "Game is finished! You win.  \n Your Score : "+player.getPoint()+"\n Would you like to play again?", "Congrulations !",
            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                reStart();
            } else {
                System.exit(0);
            }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        questionArea = new javax.swing.JLabel();
        choice4 = new javax.swing.JButton();
        choice1 = new javax.swing.JButton();
        choice2 = new javax.swing.JButton();
        choice3 = new javax.swing.JButton();
        pBar = new javax.swing.JProgressBar();
        fiftyButton = new javax.swing.JButton();
        renewButton = new javax.swing.JButton();
        extraButton = new javax.swing.JButton();
        doubleButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(questionArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 50, 490, 68));

        choice4.setText("jButton1");
        choice4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choice4ActionPerformed(evt);
            }
        });
        add(choice4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 190, -1, -1));

        choice1.setText("jButton1");
        choice1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choice1ActionPerformed(evt);
            }
        });
        add(choice1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, -1, -1));

        choice2.setText("jButton1");
        choice2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choice2ActionPerformed(evt);
            }
        });
        add(choice2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, -1, -1));

        choice3.setText("jButton1");
        choice3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choice3ActionPerformed(evt);
            }
        });
        add(choice3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, -1, -1));

        pBar.setBackground(new java.awt.Color(51, 102, 255));
        add(pBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 316, 578, -1));

        fiftyButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quizapp/images/fifty.png"))); // NOI18N
        fiftyButton.setBorder(null);
        fiftyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fiftyButtonActionPerformed(evt);
            }
        });
        add(fiftyButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 350, -1, -1));

        renewButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quizapp/images/new.png"))); // NOI18N
        renewButton.setBorder(null);
        renewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                renewButtonActionPerformed(evt);
            }
        });
        add(renewButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 350, -1, -1));

        extraButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quizapp/images/icon-extratime.png"))); // NOI18N
        extraButton.setBorder(null);
        extraButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                extraButtonActionPerformed(evt);
            }
        });
        add(extraButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 350, -1, -1));

        doubleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quizapp/images/double.png"))); // NOI18N
        doubleButton.setBorder(null);
        doubleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doubleButtonActionPerformed(evt);
            }
        });
        add(doubleButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, -1, -1));
    }// </editor-fold>//GEN-END:initComponents
    //choices and jokers as buttons
    
    private void choice1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choice1ActionPerformed
        if(temp.getChoices()[0].equals(temp.getAnswer())){
                    correctAnswer();
        }else{
            wrongAnswer();
                  // JOptionPane.showMessageDialog(QuizApp.gm, "Wrong Answer!\n Your Score :" + player.getPoint());
        }
    }//GEN-LAST:event_choice1ActionPerformed

    private void choice2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choice2ActionPerformed
        if(temp.getChoices()[1].equals(temp.getAnswer())){
                    correctAnswer();
                }else{
            wrongAnswer();
                   // JOptionPane.showMessageDialog(QuizApp.gm, "Wrong Answer!\n Your Score :" + player.getPoint());
                }        // TODO add your handling code here:
    }//GEN-LAST:event_choice2ActionPerformed

    private void choice3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choice3ActionPerformed
        if(temp.getChoices()[2].equals(temp.getAnswer())){
                    correctAnswer();
                }else{
             wrongAnswer();
                  // JOptionPane.showMessageDialog(QuizApp.gm, "Wrong Answer!\n Your Score :" + player.getPoint());                
         }        // TODO add your handling code here:
    }//GEN-LAST:event_choice3ActionPerformed

    private void choice4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choice4ActionPerformed
        
        if(temp.getChoices()[3].equals(temp.getAnswer())){
                    correctAnswer();
                }
                else{
                    wrongAnswer();
                    //JOptionPane.showMessageDialog(QuizApp.gm, "Wrong Answer!\n Your Score :" + player.getPoint());
                }        // TODO add your handling code here:
    }//GEN-LAST:event_choice4ActionPerformed

    private void fiftyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fiftyButtonActionPerformed
        fiftyJoker();        // TODO add your handling code here:
    }//GEN-LAST:event_fiftyButtonActionPerformed

    private void renewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renewButtonActionPerformed
        newQuestion();        // TODO add your handling code here:
    }//GEN-LAST:event_renewButtonActionPerformed

    private void extraButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_extraButtonActionPerformed
        extraTime();// TODO add your handling code here:
    }//GEN-LAST:event_extraButtonActionPerformed

    private void doubleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doubleButtonActionPerformed
        doubleAnswer();        // TODO add your handling code here:
    }//GEN-LAST:event_doubleButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton choice1;
    private javax.swing.JButton choice2;
    private javax.swing.JButton choice3;
    private javax.swing.JButton choice4;
    private javax.swing.JButton doubleButton;
    private javax.swing.JButton extraButton;
    private javax.swing.JButton fiftyButton;
    private javax.swing.JProgressBar pBar;
    private javax.swing.JLabel questionArea;
    private javax.swing.JButton renewButton;
    // End of variables declaration//GEN-END:variables
}
