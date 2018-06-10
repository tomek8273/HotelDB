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

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dao.impl.GuestDaoImpl;
import com.dao.impl.Session_FactoryImpl;
import com.entity.Guest;
import com.entity.Room;
import com.window.Invoice.CreateInvoice;

public class GuestCheckOut {
	JPanel panel;
	JPanel panel1;
	JPanel panel2;
	JLabel chosenData;
	JLabel label;
	DefaultListModel<String> guestsL;
	ArrayList<Guest> guestsInHotel;
	// ArrayList<GuestInHotel> result;
	ArrayList<Room> listOfRooms = new ArrayList<Room>();
	int roomWithGuest;
	String guestPesel;

	JButton okButton;
	JButton back;

	JScrollPane scroll;

	JList<String> guestsListInHotel;
	GuestDaoImpl guestDao;

	public GuestCheckOut(final JFrame ramka) {

		guestDao = new GuestDaoImpl();
		guestsL = new DefaultListModel<String>();
		okButton = new JButton("OK");
		back = new JButton("Back");
		panel = new JPanel();
		panel1 = new JPanel();
		panel2 = new JPanel();
		guestsL = new DefaultListModel<String>();
		guestsInHotel = new ArrayList<Guest>();
		label = new JLabel("List of Guests in Hotel");
		scroll = new JScrollPane();
		panel.add(label);

		guestsInHotel = (ArrayList<Guest>) guestDao.readAllinHotel();

		for (Guest g : guestsInHotel) {
			guestsL.addElement(g.getPesel());
			System.out.println("Oto gosc w Hotelu - " + g.getPesel());

		}

		guestsListInHotel = new JList<String>(guestsL);
		panel2.add(okButton);
		panel2.add(back);
		scroll = new JScrollPane(guestsListInHotel);
		panel1.add(scroll);

		ramka.add(panel, BorderLayout.NORTH);
		ramka.add(panel1, BorderLayout.CENTER);
		ramka.add(panel2, BorderLayout.SOUTH);
		ramka.repaint();
		ramka.validate();

		back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ramka.remove(panel);
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
					Session session = sessionFactory.openSession();
					session.beginTransaction();
					Query q1 = session.createQuery("from Room");
					listOfRooms = (ArrayList<Room>) q1.getResultList();
					session.getTransaction().commit();
					session.close();

					for (Room r : listOfRooms) {
						for (Guest g : r.getGuests()) {
							if (g.getPesel().equals(guestsListInHotel.getSelectedValue())) {
								System.out.println("Znaleziono goscia do usuniecia");
								roomWithGuest = r.getId();
								Session session1 = sessionFactory.openSession();
								session1.beginTransaction();
								r.getGuests().remove(g);
								session1.update(r);
								session1.getTransaction().commit();
								session1.close();
							}
						}
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}

				ramka.remove(panel);
				ramka.remove(panel1);
				ramka.remove(panel2);
				new CreateInvoice(ramka);
			}
		});

	}

}
