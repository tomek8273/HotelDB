package com.window.Guest;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dao.impl.GuestDaoImpl;
import com.entity.Guest;

public class GuestAdd {

	private JPanel panel;
	private JPanel panel2;
	private JPanel panel3;
	private JButton back;
	private static JTextField firstName;
	private static JTextField lastName;
	private static JTextField dateOfBirth;
	private static JTextField pesel;
	private static ArrayList<Guest> guestsList;
	private static DefaultListModel<String> guestsL = new DefaultListModel<String>();

	private static Logger log = Logger.getLogger(GuestCheckIn.class);

	public GuestAdd(final JFrame ramka) {
		panel = new JPanel();
		panel2 = new JPanel(new GridLayout(4, 2));
		panel3 = new JPanel();
		back = new JButton("Back");

		ramka.add(panel, BorderLayout.NORTH);
		ramka.add(panel2, BorderLayout.CENTER);
		ramka.add(panel3, BorderLayout.SOUTH);
		ramka.setVisible(true);
		ramka.setSize(500, 500);

		JLabel label = new JLabel("Guest Data Input");
		panel.add(label);

		JLabel label1 = new JLabel("First name");
		firstName = new JTextField();

		JLabel label2 = new JLabel("Last name");
		lastName = new JTextField("Last name");

		JLabel label3 = new JLabel("Date of birth");
		dateOfBirth = new JTextField("Date of birth");

		JLabel label4 = new JLabel("PESEL");
		pesel = new JTextField("PESEL");

		panel.add(label);

		panel2.add(label1);
		panel2.add(firstName);

		panel2.add(label2);
		panel2.add(lastName);
		panel2.add(label3);
		panel2.add(dateOfBirth);
		panel2.add(label4);
		panel2.add(pesel);

		JButton button = new JButton("OK");
		panel3.add(button);
		panel3.add(back);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				log.info("Wcisniety przycisk OK w GuestAdd");
				Guest guest = new Guest();
				guest.setFirst_name(firstName.getText());
				guest.setLast_name(lastName.getText());
				guest.setDate_of_birth(dateOfBirth.getText());
				guest.setPesel(pesel.getText());

				log.info("Wynik sprawdzenia pustych pol - " + GuestAdd.CheckEmptyCells(firstName, lastName, dateOfBirth, pesel));
				if (GuestAdd.CheckEmptyCells(firstName, lastName, dateOfBirth, pesel)) {
					System.out.println("Nie wszystkie pola wypelnione");
				}
				
				if (GuestAdd.CheckUniquePesel()) {
					System.out.println("taki gosc juz istnieje w bazie");
				}

				ApplicationContext context1 = new AnnotationConfigApplicationContext(GuestDaoImpl.class);
				GuestDaoImpl guestDao = context1.getBean(GuestDaoImpl.class);
				guestDao.add(guest);
				((AnnotationConfigApplicationContext) context1).close();
			}
		});

		back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				log.info("Wcisniety przycisk BACK w GuestAdd");
				System.out.println("przycisniety przycisk back");
				ramka.remove(panel);
				ramka.remove(panel2);
				ramka.remove(panel3);
				new GuestWindow(ramka);
			}
		});
		ramka.validate();
		ramka.repaint();
	}

	public static boolean CheckEmptyCells(JTextField firstName, JTextField lastName, JTextField dateOfBirth, JTextField pesel) {
		log.info("Sprawdzam puste pola");
		return (firstName.getText().equals("") || lastName.getText().equals("") || dateOfBirth.getText().equals("")
				|| pesel.getText().equals(""));
	}

	public static boolean CheckUniquePesel() {

		log.info("Sprawdzam czy PESEL wystepuje w bazie");
		ApplicationContext context1 = new AnnotationConfigApplicationContext(GuestDaoImpl.class);
		GuestDaoImpl guestDao = context1.getBean(GuestDaoImpl.class);
		((AnnotationConfigApplicationContext) context1).close();

		guestsList = (ArrayList<Guest>) guestDao.readAll();

		for (Guest g : guestsList) {
			if (g.getPesel().equals(pesel.getText())) {
				guestsL.addElement(g.getPesel());
				log.info("Dodany element to listy gosci");
			}

		}
		log.info("Liczba rekordow w bazie"+guestsL.size());
		log.info("Wynik sprawdzenia unikalnych gosci - "+(guestsL.size()<1));
		return guestsL.size() < 1;
		
	}

}
