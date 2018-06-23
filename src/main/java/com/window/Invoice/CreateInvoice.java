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

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dao.impl.InvoiceDaoImpl;
import com.entity.Guest;
import com.entity.Invoice;
import com.window.Guest.GuestWindow;

public class CreateInvoice {
	private JPanel panel1;
	private JPanel panel2;
	private JButton okButton;
	private JButton back;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JTextField invoiceValue;
	private JTextField invoiceNumber;
	private JTextField paid;
	private static Logger log = Logger.getLogger(CreateInvoice.class);

	public CreateInvoice(final JFrame ramka, final Guest guest) {
		panel1 = new JPanel();
		panel2 = new JPanel();
		label1 = new JLabel("Invoice value");
		label2 = new JLabel("Invoice Number");
		label3 = new JLabel("Invoice paid");
		invoiceValue = new JTextField();
		okButton = new JButton("OK");
		back = new JButton("back");
		invoiceValue.setPreferredSize(new Dimension(100, 25));
		invoiceNumber = new JTextField();
		invoiceNumber.setPreferredSize(new Dimension(100, 25));
		paid = new JTextField();
		paid.setPreferredSize(new Dimension(100, 25));

		panel1.add(label1);
		panel1.add(invoiceValue);
		panel1.add(label2);
		panel1.add(invoiceNumber);
		panel1.add(label3);
		panel1.add(paid);
		panel1.setLayout(new GridLayout(3, 2));
		panel2.add(okButton);
		panel2.add(back);
		ramka.add(panel1, BorderLayout.CENTER);
		ramka.add(panel2, BorderLayout.SOUTH);
		ramka.repaint();
		ramka.validate();

		back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				log.info("Wcisniety przycisk BACK");
				ramka.remove(panel1);
				ramka.remove(panel2);
				new GuestWindow(ramka);
				ramka.repaint();
				ramka.validate();

			}
		});

		okButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				log.info("Wcisniety przycisk OK");
				Invoice invoice = new Invoice();
				invoice.setInvoiceValue(invoiceValue.getText());
				invoice.setInvoiceNumber(invoiceNumber.getText());
				invoice.setInvoicePaid(paid.getText());

				ApplicationContext context1 = new AnnotationConfigApplicationContext(InvoiceDaoImpl.class);
				InvoiceDaoImpl invoiceDao = context1.getBean(InvoiceDaoImpl.class);
				invoiceDao.AddInvoice(invoice, guest);
				((AnnotationConfigApplicationContext)context1).close();
			}
		});
	}

}
