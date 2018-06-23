package com.window.Guest;

import java.awt.BorderLayout;
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

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.dao.impl.GuestDaoImpl;
import com.dao.impl.Session_FactoryImpl;
import com.entity.Guest;


public class DeleteGuest {
	private JPanel panel;
	private JPanel panel1;
	private JPanel panel2;
	private JLabel label;
	private DefaultListModel<String> guestsL;
	private ArrayList<Guest> guestsList;
	private JButton okButton;
	private JButton back;
	private JScrollPane scroll;
	private JList<String> guestsList1;
	private GuestDaoImpl guestDao;
	private Query q1;
	private static Logger log = Logger.getLogger(DeleteGuest.class);

	public DeleteGuest(final JFrame ramka) {
		
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
		
		ApplicationContext context1 = new AnnotationConfigApplicationContext(GuestDaoImpl.class);
		GuestDaoImpl guestDao= context1.getBean(GuestDaoImpl.class);
		((AnnotationConfigApplicationContext)context1).close();

		guestsList = (ArrayList<Guest>) guestDao.readAll();

		for (Guest g : guestsList) {
			guestsL.addElement(g.getPesel());
			log.info("Dodany element to listy gosci");
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
				log.info("Wcisniety przycisk BACK w DeleteGuest");
				ramka.remove(panel1);
				ramka.remove(panel2);
				new GuestWindow(ramka);
			}
		});
		
		okButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				log.info("Wcisniety przycisk OK w DELETE GUEST");
				ApplicationContext context1 = new AnnotationConfigApplicationContext(Session_FactoryImpl.class);
				Session_FactoryImpl sessionFactory1 = context1.getBean(Session_FactoryImpl.class);
				SessionFactory sessionFactory = sessionFactory1.SessionFact();
				try {
					log.info("Wykonywana kewredna DELETE w DELETE GUEST");
					Session session = sessionFactory.openSession();
					session.beginTransaction();
					q1 = session.createQuery("delete Guest g Where g.guestPesel =:pesel");
					q1.setParameter("pesel", guestsList1.getSelectedValue());
					int result = q1.executeUpdate();
					session.close();
				} catch (Exception e1) {
					log.info("Rzucony wyj¹tek w czasie wykonywania tranzakcji w DeleteGuest");
					e1.printStackTrace();
				}
				((AnnotationConfigApplicationContext)context1).close();
			}
		});

	}

}
