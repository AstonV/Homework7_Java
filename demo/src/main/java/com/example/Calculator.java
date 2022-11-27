package com.example;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Calculator implements ActionListener {
	static JTextField outputField;
	Calculator(){}

	//Main function
	public static void main(String[] args) {
		createCalculator();
	}

	//Create the frame
	private static void createCalculator() {
		JFrame frame = new JFrame();
		

		ImageIcon app_icon = new ImageIcon("Calculatorfavicon.png");
		frame.setIconImage(app_icon.getImage());

		createPanel(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Calculator");
		frame.setSize(400,400);
		frame.setVisible(true);
		
	}

	//Create the panels
	private static void createPanel(JFrame frame) {
		
		Calculator calculator = new Calculator();
		JPanel displayPanel  = new JPanel();

		//Create non-editable text field
		outputField = new JTextField(30);
		outputField.setEditable(false);

		JPanel buttonPanel = new JPanel(new GridLayout(1,2));

		//Create digit buttons and add action listeners
		JPanel digitButtonPanel = new JPanel(new GridLayout(4,3));
		ArrayList<JButton> digits = new ArrayList<JButton>();
		for (int i=0; i < 10; i++) {
			digits.add(new JButton(String.valueOf(i)));
			digitButtonPanel.add(digits.get(i));
			digits.get(i).addActionListener(calculator);
		}
		//create dot button
		ImageIcon dotImg = new ImageIcon(new ImageIcon("dot.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		digits.add(new JButton(".",dotImg)); digitButtonPanel.add(digits.get(10)); digits.get(10).addActionListener(calculator);

		//create clear button
		digits.add(new JButton("AC")); digitButtonPanel.add(digits.get(11)); digits.get(11).addActionListener(calculator);

		JPanel operatorButtonPanel = new JPanel(new GridLayout(5,1));

		//Set buttons to panel
		ImageIcon plusImg = new ImageIcon(new ImageIcon("Plus.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		ImageIcon minusImg = new ImageIcon(new ImageIcon("Minus.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		ImageIcon multImg = new ImageIcon(new ImageIcon("Mul.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		ImageIcon divImg = new ImageIcon(new ImageIcon("divide.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
		ImageIcon eqImg = new ImageIcon(new ImageIcon("equal.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));

		JButton plusSign = new JButton("+",plusImg); operatorButtonPanel.add(plusSign);
		JButton minusSign = new JButton("-",minusImg); operatorButtonPanel.add(minusSign);
		JButton mulSign = new JButton("x",multImg); operatorButtonPanel.add(mulSign);
		JButton divSign = new JButton("/",divImg); operatorButtonPanel.add(divSign);
		JButton eqSign = new JButton("=",eqImg); operatorButtonPanel.add(eqSign);

		//Add action listener
		plusSign.addActionListener(calculator);
		minusSign.addActionListener(calculator);
		mulSign.addActionListener(calculator);
		divSign.addActionListener(calculator);
		eqSign.addActionListener(calculator);

		//Add all panels to the display panel which is added to frame
		displayPanel.add(outputField);
		buttonPanel.add(digitButtonPanel);
		buttonPanel.add(operatorButtonPanel);
		displayPanel.add(buttonPanel);
		frame.add(displayPanel);
	}

	//Receive action events
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		String currentText = outputField.getText();

		if (command.charAt(0) == 'A') {
			outputField.setText("");
		}
		else if (!currentText.contains("=") && !currentText.contains("Cannot calculate! Press AC to reset"))
			if (command.charAt(0) == '=') {
				outputField.setText(computation(currentText));
			}else {
				outputField.setText(outputField.getText() + command);
			}
		else outputField.setText("");
		}

		//Take input string and return the correct computation
	   public static String computation(String currentText) {
		   String firstOperand  = "";
		   String secondOperand  = "";
		   String operator = "";
		   double result = 0;

		   for (int i = 0; i < currentText.length(); i++) {
			   if (currentText.charAt(i) >= '0' && currentText.charAt(i) <= '9' || (currentText.charAt(i) == '.')) {
				   if(operator.isEmpty()){
					   firstOperand += currentText.charAt(i);
				   }else{
					   secondOperand += currentText.charAt(i);
				   }
			   }

			   if(currentText.charAt(i) == '+' || currentText.charAt(i) == '-' || currentText.charAt(i) == '/' || currentText.charAt(i) == '*') {
				   operator += currentText.charAt(i);
			   }
		   }
		   if (firstOperand != "" && secondOperand != "" && secondOperand != "" && !firstOperand.equals(".") && !secondOperand.equals(".")
				   && countOccurrences(firstOperand, '.') < 2 && countOccurrences(secondOperand, '.') < 2 && operator.length() < 2) {
			   if (operator.equals("+"))
				   result = (Double.parseDouble(firstOperand) + Double.parseDouble(secondOperand));
			   else if (operator.equals("-"))
				   result = (Double.parseDouble(firstOperand) - Double.parseDouble(secondOperand));
			   else if (operator.equals("*"))
				   result = (Double.parseDouble(firstOperand) * Double.parseDouble(secondOperand));
			   else
				   result = (Double.parseDouble(firstOperand) / Double.parseDouble(secondOperand));
			   return firstOperand + operator + secondOperand + "=" +result;
		   }
		   else {
			   return "Cannot calculate! Press AC to reset";
		   }
	   }

	   //Count the occurrence of each character in a string
	   static int countOccurrences (String string, char Char) {
		   int count = 0;
		   for (int i = 0; i < string.length(); i++) {
			   if (string.charAt(i) == Char) {
				   count++;
			   }
		   }
		   return count;
	   }
}