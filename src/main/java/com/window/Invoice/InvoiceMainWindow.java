package com.window.Invoice;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class InvoiceMainWindow {

	JPanel panel;
	JButton invoiceAdd;
	JButton invoicePay;
	JButton invoiceList;
	
	public InvoiceMainWindow(final JFrame ramka) {
		panel = new JPanel();
		invoiceAdd = new JButton("New Invoice");
		invoicePay = new JButton("Invoice Pay");
		invoiceList = new JButton("Invoice List");
		panel.add(invoiceAdd);
		panel.add(invoicePay);
		panel.add(invoiceList);
		
		ramka.add(panel);
		ramka.repaint();
		ramka.validate();
	}

}
