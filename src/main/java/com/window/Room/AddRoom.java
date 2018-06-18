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

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.dao.impl.RoomDaoImpl;
import com.entity.Room;

public class AddRoom {
	private JPanel panel1;
	private JPanel panel2;
	private JLabel label;
	private JTextField roomNumber;
	private JButton okButton;
	private JTextField numberOfBeds;
	private JLabel label2;
	private JButton back;

	public AddRoom(final JFrame ramka) {
		panel1 = new JPanel();
		panel2 = new JPanel();
		label = new JLabel("Room number");
		label2 = new JLabel("Number of Beds");
		roomNumber = new JTextField();
		numberOfBeds = new JTextField();
		okButton = new JButton("OK");
		back = new JButton("Back");
		roomNumber.setPreferredSize(new Dimension(100, 25));
		numberOfBeds.setPreferredSize(new Dimension(100, 25));

		panel1.add(label);
		panel1.add(roomNumber);
		panel1.add(label2);
		panel1.add(numberOfBeds);
		panel2.add(okButton);
		panel2.add(back);
		ramka.add(panel1, BorderLayout.CENTER);
		ramka.add(panel2, BorderLayout.SOUTH);
		ramka.repaint();
		ramka.validate();

		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				Room room = new Room();
				room.setNumber_of_beds(numberOfBeds.getText());
				room.setRoomNumber(roomNumber.getText());
				ApplicationContext context1 = new AnnotationConfigApplicationContext(RoomDaoImpl.class);
				RoomDaoImpl roomDao = context1.getBean(RoomDaoImpl.class);
				roomDao.addRoom(room);
				((AnnotationConfigApplicationContext)context1).close();
			}
		});

		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ramka.remove(panel1);
				ramka.remove(panel2);
				new RoomMainWindow(ramka);
				ramka.validate();
				ramka.repaint();
			}
		});
	}

}
