package com.window.Invoice;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.window.Guest.GuestWindow;

public class InvoiceMainWindow {

	private JPanel panel;
	private JPanel panel1;
	private JButton invoiceAdd;
	private JButton invoicePay;
	private JButton invoiceList;
	private JButton back;
	
	public InvoiceMainWindow(final JFrame ramka) {
		panel = new JPanel();
		panel1 = new JPanel();
		invoiceAdd = new JButton("New Invoice");
		invoicePay = new JButton("Invoice Pay");
		invoiceList = new JButton("Invoice List");
		back = new JButton("back");
		
		panel.add(invoiceAdd);
		panel.add(invoicePay);
		panel.add(invoiceList);
		panel1.add(back);
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ramka.remove(panel);
				ramka.remove(panel1);
				new GuestWindow(ramka);
			}
		});
		
		ramka.add(panel, BorderLayout.CENTER);
		ramka.add(panel1, BorderLayout.SOUTH);
		ramka.repaint();
		ramka.validate();
	}
}
