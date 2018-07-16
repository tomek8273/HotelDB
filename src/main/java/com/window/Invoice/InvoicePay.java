package com.window.Invoice;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.persistence.Query;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dao.impl.Session_FactoryImpl;
import com.entity.Guest;


public class InvoicePay {
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JLabel label;
	private JButton payInvoiceButton;
	private JButton backButton;
	private ArrayList<Object> listOfInvoices;
	Guest guest;

	public InvoicePay(final JFrame ramka) {

		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		label = new JLabel("Invoice Pay");
		payInvoiceButton = new JButton("Pay Invoice");
		backButton = new JButton("Back");
		guestSelect();

		panel3.add(backButton);
		panel3.add(payInvoiceButton);

		ramka.add(panel1, BorderLayout.NORTH);
		ramka.add(panel2, BorderLayout.CENTER);
		ramka.add(panel3, BorderLayout.SOUTH);

		ramka.repaint();
		ramka.revalidate();

		backButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ramka.remove(panel1);
				ramka.remove(panel2);
				ramka.remove(panel3);
				new InvoiceMainWindow(ramka);
				ramka.repaint();
				ramka.revalidate();

			}
		});

	}

	public void guestSelect() {
		ApplicationContext context1 = new AnnotationConfigApplicationContext(Session_FactoryImpl.class);
		Session_FactoryImpl sessionFactory1 = context1.getBean(Session_FactoryImpl.class);
		SessionFactory sessionFactory = sessionFactory1.SessionFact();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query q1 = session.createQuery("from Invoice i JOIN Guest g ON i.guest.id = g.id Where i.invoicePaid = ''");
		listOfInvoices = (ArrayList<Object>) q1.getResultList();
		System.out.println("Oto liczba faktur - " + listOfInvoices.size());
		session.getTransaction().commit();
		session.close();
		for (Object invoice : listOfInvoices) {
			// String o = (String) invoice;
			System.out.println("Oto numer faktury - " +invoice);
		
		}

		
	}
}
