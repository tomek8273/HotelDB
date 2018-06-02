package com.window.Invoice;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.window.Guest.GuestWindow;

public class CreateInvoice {

	JPanel panel;
	JPanel panel1;
	JPanel panel2;
	JButton okButton;
	JButton back;
	JLabel label1;
	JLabel label2;
	JLabel label3;
	JTextField value;
	JTextField invoiceNumber;
	JTextField paid;
	

	public CreateInvoice(final JFrame ramka) {
		panel = new JPanel();
		panel1 = new JPanel();
		panel2 = new JPanel();
		label1 = new JLabel("Invoice value");
		label2 = new JLabel("Invoice Number");
		label3 = new JLabel("Invoice paid");
		value = new JTextField ();
		okButton = new JButton("OK");
		back = new JButton("back");
		value.setPreferredSize(new Dimension(100,25));
		invoiceNumber = new JTextField();
		invoiceNumber.setPreferredSize(new Dimension(100,25));
		paid = new JTextField();
		paid.setPreferredSize(new Dimension(100,25));
		

		panel1.add(label1);
		panel1.add(value);
		panel1.add(label2);
		panel1.add(invoiceNumber);
		panel1.add(label3);
		panel1.add(paid);
		panel1.setLayout(new GridLayout(3,2));
		panel2.add(okButton);
		panel2.add(back);
		ramka.add(panel1, BorderLayout.CENTER);
		ramka.add(panel2, BorderLayout.SOUTH);
		ramka.repaint();
		ramka.validate();
		
		back.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				ramka.remove(panel1);
				ramka.remove(panel2);
				new GuestWindow(ramka);
				ramka.repaint();
				ramka.validate();
				
			}
		});
	}

}
