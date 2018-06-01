package com.window.Guest;

import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.persistence.Query;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dao.impl.GuestDaoImpl;
import com.dao.impl.Session_FactoryImpl;
import com.entity.Guest;

public class DeleteGuest {
	JPanel panel;
	JPanel panel1;
	JPanel panel2;
	JLabel chosenData;
	JLabel label;
	DefaultListModel<String> guestsL;
	ArrayList<Guest> guestsList;

	JButton okButton;
	JButton back;

	JScrollPane scroll;

	JList<String> guestsList1;
	GuestDaoImpl guestDao;
	
	Query q1;

	public DeleteGuest(final JFrame ramka) {

		guestDao = new GuestDaoImpl();
		guestsL = new DefaultListModel<String>();
		okButton = new JButton("OK");
		back = new JButton("Back");
		panel = new JPanel();
		panel1 = new JPanel();
		panel2 = new JPanel();
		guestsL = new DefaultListModel<String>();
		guestsList = new ArrayList<Guest>();
		label = new JLabel("List of Guests in Hotel");
		scroll = new JScrollPane();
		panel.add(label);

		guestsList = (ArrayList<Guest>) guestDao.readAll();

		for (Guest g : guestsList) {
			guestsL.addElement(g.getPesel());

		}

		guestsList1 = new JList(guestsL);
		panel2.add(okButton);
		panel2.add(back);
		scroll = new JScrollPane(guestsList1);
		panel1.add(scroll);

		ramka.add(panel, BorderLayout.NORTH);
		ramka.add(panel1, BorderLayout.CENTER);
		ramka.add(panel2, BorderLayout.SOUTH);
		ramka.repaint();
		ramka.validate();

		back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ramka.remove(panel1);
				ramka.remove(panel2);
				new GuestWindow(ramka);
			}
		});
		
		
		okButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				ApplicationContext context1 = new AnnotationConfigApplicationContext(Session_FactoryImpl.class);
				Session_FactoryImpl sessionFactory1 = context1.getBean(Session_FactoryImpl.class);
				SessionFactory sessionFactory = sessionFactory1.SessionFact();
				try {
					System.out.println("wykonujeb metode DELETE - usowam wybranego goscia");
					Session session = sessionFactory.openSession();
					session.beginTransaction();
					q1 = session.createQuery("delete Guest g Where g.Pesel =:pesel");
					q1.setParameter("pesel", guestsList1.getSelectedValue());
					int result = q1.executeUpdate();
					session.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});

	}

}
