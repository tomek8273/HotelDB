package com.window.Guest;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GuestsList {
JPanel panel1;
JPanel panel2;

JLabel label;
JLabel name;
JLabel name1;
JTextField firstName;
JTextField lastName;
JButton delete;

	public GuestsList(JFrame ramka) {
		panel1 = new JPanel();
		panel2 = new JPanel();
		name = new JLabel("First Name");
		name1 = new JLabel("Last Name");
		firstName = new JTextField();
		lastName = new JTextField();
		delete = new JButton("DELETE");
		panel1.add(name);
		panel1.add(firstName);
		panel1.add(name1);
		panel1.add(lastName);
		panel2.add(delete);
		
		
		
		
	}

}
