package com.window.Guest;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.dao.impl.GuestDaoImpl;
import com.entity.Guest;

public class GuestsList {
JPanel panel1;
JPanel panel2;
JPanel panel3;
JLabel label;
JButton backButton;
ArrayList<Guest> guestsList;
GuestDaoImpl guestDao;
JScrollPane scroll;
DefaultListModel<String> guestsL;
JList<String> guestsList1;
JList<String> guestList;

	public GuestsList(final JFrame ramka) {
		
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		label = new JLabel("Oto lista go�ci na liscie Hotelu:");
		guestDao = new GuestDaoImpl();
		guestsL = new DefaultListModel<String>();
		backButton = new JButton("Back");
		guestsList = (ArrayList<Guest>) guestDao.readAll();
		
		for (Guest g : guestsList) {
			guestsL.addElement(g.getPesel());
		}
		
		guestList = new JList<String>(guestsL);
		scroll = new JScrollPane(guestList);

		panel1.add(label);
		panel2.add(scroll);
		panel3.add(backButton);
		ramka.add(panel1, BorderLayout.NORTH);
		ramka.add(panel2, BorderLayout.CENTER);
		ramka.add(panel3, BorderLayout.SOUTH);
		backButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				ramka.remove(panel1);
				ramka.remove(panel2);
				ramka.remove(panel3);
				new GuestWindow(ramka);
				
			}
		});
	}

}
