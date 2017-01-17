/*
Programmer: Alejandro Parana
Date: 11/4/2014
Project
CalculatorPanel.java
*/
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class CalculatorPanel extends JPanel {
   private JTextField result;
   private String text = "", answer = "";
   private String[] op = {"",""};
   private boolean[] operation = new boolean[6]; // [+ - * / AND OR]
   private JButton zeroBTN, oneBTN, clearBTN, calculateBTN, addBTN, subtractBTN, 
		   multiplyBTN, divideBTN, andBTN, orBTN, signBTN;
   
   // 
   // Constructor
   //
   public CalculatorPanel() {
      // Layout
	  setBackground (new Color(40,40,40));
	  setLayout (new GridLayout (6,2));
	  setBorder (BorderFactory.createEmptyBorder (5, 5, 5, 5));
	  
	  // Text Field
      result = new JTextField();
	  Font font1 = new Font("SansSerif", Font.BOLD, 20);
	  result.setFont(font1);
	  result.setEditable(false);
	  result.setBackground(new Color(230,230,230));
	  add(result);
	 
	  // Buttons
	  zeroBTN = new JButton ("0");
      oneBTN = new JButton ("1");
	  calculateBTN = new JButton ("=");
	  calculateBTN.setFont(font1);
      clearBTN = new JButton ("Clear");
      addBTN = new JButton ("+");
      subtractBTN = new JButton ("-");
	  multiplyBTN = new JButton ("*");
      divideBTN = new JButton ("/");
	  andBTN = new JButton ("AND");
	  orBTN = new JButton ("OR");
	  signBTN = new JButton ("±");
      
	  // Actions
	  zeroBTN.addActionListener(new ButtonListener());
	  oneBTN.addActionListener(new ButtonListener());
	  calculateBTN.addActionListener(new ButtonListener());
	  clearBTN.addActionListener(new ButtonListener());
	  addBTN.addActionListener(new ButtonListener());
	  subtractBTN.addActionListener(new ButtonListener());
	  multiplyBTN.addActionListener(new ButtonListener());
	  divideBTN.addActionListener(new ButtonListener());
	  andBTN.addActionListener(new ButtonListener());
	  orBTN.addActionListener(new ButtonListener());
	  signBTN.addActionListener(new ButtonListener());
	 
	  // Add to grid
	  add (calculateBTN);
      add (zeroBTN);
      add (oneBTN);
      add (addBTN);
      add (subtractBTN);  
	  add (multiplyBTN);
      add (divideBTN);
	  add (andBTN);
	  add (orBTN);
	  add (clearBTN);
	  add (signBTN);
	  
	  // Operations
	  for(int i = 0; i < 6; i++)
            operation[i] = false;
   }
   
   // 
   // Button Events
   //
   private class ButtonListener implements ActionListener {
	   public void actionPerformed(ActionEvent event) {
			if (event.getSource() == zeroBTN) // 0
			{
			 text += 0;
			 result.setText("");
			 result.setText(text);
			}
			if (event.getSource() == oneBTN) // 1
			{
			 text += 1;
			 result.setText("");
			 result.setText(text);
			}
			if (event.getSource() == clearBTN) //Clear
			{
			 clear();
			 op[0] = "";
			 op[1] = "";
			}
			if (event.getSource() == addBTN) // Addition
			{
			 op[0] = result.getText();
			 clear();
			 resetOperations();
			 operation[0] = true;
			}
			if (event.getSource() == subtractBTN) // Subtraction
			{
			 op[0] = result.getText();
			 clear();
			 resetOperations();
			 operation[1] = true;
			}
			if (event.getSource() == multiplyBTN) // Multiplication
			{
			 op[0] = result.getText();
			 clear();
			 resetOperations();
			 operation[2] = true;
			}
			if (event.getSource() == divideBTN) // Division
			{
			 op[0] = result.getText();
			 clear();
			 resetOperations();
			 operation[3] = true;
			}
			if (event.getSource() == andBTN) // AND
			{
			 op[0] = result.getText();
			 clear();
			 resetOperations();
			 operation[4] = true;
			}
			if (event.getSource() == orBTN) // OR
			{
			 op[0] = result.getText();
			 clear();
			 resetOperations();
			 operation[5] = true;
			}
			if (event.getSource() == calculateBTN) // Calculate
			{
			  Calculations();
			}
			if (event.getSource() == signBTN) // Sign toggle
			{
			  changeSign();
			}
		}
	}
	
	// 
	// Perform Calculations
	//
	public void Calculations()
	{
		 int decimalResult = 0, op1 = 0, op2 = 0;
		 op[1] = result.getText();
		
		if (op[1].length() > 0 && op[0].length() > 0)
		{
			// Convert negative decimals
			if (op[0].contains("-"))
			 {
				String opTemp1 = op[0].substring(1);
				op[0] = opTemp1;
				op1 = BinaryDecimal.BinaryToDecimal(op[0]);
				op1 = op1 *(-1);
			}
			else
			{
				op1 = BinaryDecimal.BinaryToDecimal(op[0]);
			}
			if (op[1].contains("-"))
			 {
				String opTemp2 = op[1].substring(1);
				op[1] = opTemp2;
				op2 = BinaryDecimal.BinaryToDecimal(op[1]);
				op2 = op2 *(-1);
			}
			else
			{
				op2 = BinaryDecimal.BinaryToDecimal(op[1]);
			}
			
			 
			 // Addition
			 if(operation[0] == true)
			  {
			   decimalResult = op1 + op2;
			   answer = BinaryDecimal.DecimalToBinary(decimalResult);
			  }
			 
			 // Subtraction 
			 if(operation[1] == true)
			  {
			   decimalResult = op1 - op2;
			   answer = BinaryDecimal.DecimalToBinary(decimalResult);
			  }
			  
			 // Multiplication 
			 if(operation[2] == true)
			  {
			   decimalResult = op1 * op2;
			   answer = BinaryDecimal.DecimalToBinary(decimalResult);
			  }
			  
			 // Division 
			 if (op2 != 0 && operation[3] == true)
			 {		 
				decimalResult = op1 / op2;
				answer = BinaryDecimal.DecimalToBinary(decimalResult);
			 }
			 else if (op2 == 0 && operation[3] == true)
			 {
				answer = "Can't divide by zero";
			 }
			 
			 // AND
			 if(operation[4] == true)
			  {
			   answer = getANDResult();
			  }
			  
			 // OR 
			 if(operation[5] == true)
			  {
			   answer = getORResult();
			  } 
			  
			 // Display answer, reset operations
			 result.setText(answer);
			 text = answer;
			 for(int i = 0; i < 6; i++)
			   operation[i] = false;
		}
	}

	/* 
	AND
	0 && 0 = 0
	0 && 1 = 0
	1 && 0 = 0
	1 && 1 = 1
	*/
	
	public String getANDResult()
	{
	 String ans = "";
	 if (op[0].length() != op[1].length())
		makeSameSize();
	 makePositive();
	 for (int i = 0; i < op[0].length(); i++)
		{
	     if (op[0].charAt(i) == op[1].charAt(i))
			ans += op[0].charAt(i);
		 else
		    ans += 0;
		}
	return ans;
	}
	
    /* 
	OR
	0 || 0 = 0
	0 || 1 = 1
	1 || 0 = 1
	1 || 1 = 1
	*/
	
	public String getORResult()
	{
	 String ans = "";
	 if (op[0].length() != op[1].length())
		makeSameSize();
	 makePositive();
	 for (int i = 0; i < op[0].length(); i++)
		{
		 if( (op[0].charAt(i) == '1') || (op[1].charAt(i)== '1'))
			ans += "1";
		 else
			ans += "0";
		}
	 return ans;
	}
	
	// Check sizes before comparing
	public void makeSameSize()
	{
		int str1 = op[0].length();
		int str2 = op[1].length();
		if (str1 < str2)
		{
			for (int i = 0 ; i < (str2-str1); i++)
				op[0] = "0" + op[0];
		}
		else if (str1 > str2)
		{
			for (int i = 0 ; i < (str1-str2); i++)
				op[1] = "0" + op[1];
		}
	}

	public void changeSign() 
	{
		if (result.getText().length() > 0)
		{
			int n = BinaryDecimal.BinaryToDecimal(result.getText());
			if (n != 0)
			{
				n = n*(-1);
				result.setText(BinaryDecimal.DecimalToBinary(n));
			}
		}
	}
	
	public void makePositive()
	{
	 if (op[0].contains("-"))
	 {
		int n = BinaryDecimal.BinaryToDecimal(result.getText());
		n = n*(-1);
		op[0] = BinaryDecimal.DecimalToBinary(n);
	 }
	  if (op[1].contains("-"))
	 {
		int n = BinaryDecimal.BinaryToDecimal(result.getText());
		n = n*(-1);
		op[1] = BinaryDecimal.DecimalToBinary(n);
	 }
	}
	
	public void resetOperations()
	{
	  for(int i = 0; i < 6; i++)
         operation[i] = false;
	}
	
	public void clear()
	{
	  text = "";
	  result.setText("");
	  for(int i = 0; i < 6; i++)
        operation[i] = false;
	}
}
