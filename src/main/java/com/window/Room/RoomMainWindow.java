package com.window.Room;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.window.Guest.DatabaaseMainWindow;

public class RoomMainWindow {

	JPanel panel;
	JPanel panel1;
	JButton addRoom;
	JButton equipmentChange;
	JButton back;

	public RoomMainWindow(final JFrame ramka) {
		addRoom = new JButton("ADD Room");
		equipmentChange = new JButton("Equipment Change");
		panel = new JPanel(new FlowLayout());
		panel1 = new JPanel();
		back = new JButton("Back");

		panel.add(addRoom);
		panel.add(equipmentChange);
		panel1.add(back);
		ramka.add(panel,BorderLayout.NORTH);
		ramka.add(panel1, BorderLayout.SOUTH);
		ramka.repaint();
		ramka.revalidate();
		
		addRoom.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				ramka.remove(panel);
				ramka.remove(panel1);
				new AddRoom(ramka);
				ramka.repaint();
				ramka.validate();
			}
		});
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ramka.remove(panel);
				ApplicationContext context = new AnnotationConfigApplicationContext(DatabaaseMainWindow.class);
				DatabaaseMainWindow window = context.getBean(DatabaaseMainWindow.class);
				window.WindowDisplay();
			}
		});
	}
}
