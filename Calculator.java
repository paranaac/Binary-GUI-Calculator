/*
Programmer: Alejandro Parana
Date: 11/4/2014
CSCI300 Project #1
Calculator.java
*/
import javax.swing.*;

public class Calculator {
   public static void main (String[] args) {
      JFrame frame = new JFrame ("Binary Calculator");
  	  frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(new CalculatorPanel());
      frame.pack();
      frame.setVisible(true);
	  frame.setResizable(false);
	  frame.setLocationRelativeTo(null);
	  frame.setSize(450,350);
   }
}
