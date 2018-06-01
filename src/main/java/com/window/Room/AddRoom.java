package com.window.Room;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dao.impl.GuestDaoImpl;
import com.dao.impl.RoomDaoImpl;
import com.dao.impl.Session_FactoryImpl;
import com.entity.Room;

public class AddRoom {
	JPanel panel1;
	JPanel panel2;
	JLabel label;
	JTextField roomNumber;
	JButton okButton;
	JTextField numberOfBeds;
	JLabel label2;

	public AddRoom(JFrame ramka) {
		panel1 = new JPanel();
		panel2 = new JPanel();
		label = new JLabel("Room number");
		label2 = new JLabel("Number of Beds");
		roomNumber = new JTextField();
		numberOfBeds = new JTextField();
		okButton = new JButton("OK");
		roomNumber.setPreferredSize(new Dimension(100,25));
		numberOfBeds.setPreferredSize(new Dimension(100,25));
		
		panel1.add(label);
		panel1.add(roomNumber);
		panel1.add(label2);
		panel1.add(numberOfBeds);
		panel2.add(okButton);
		ramka.add(panel1,BorderLayout.CENTER);
		ramka.add(panel2,BorderLayout.SOUTH);
		
		okButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Room room = new Room();
				room.setNumber_of_beds(numberOfBeds.getText());
				room.setRoomNumber(roomNumber.getText());
				
				ApplicationContext context1 = new AnnotationConfigApplicationContext(RoomDaoImpl.class);
				RoomDaoImpl roomDao = context1.getBean(RoomDaoImpl.class);
				
				roomDao.addRoom(room);
				
			}
			
		});

	}

}