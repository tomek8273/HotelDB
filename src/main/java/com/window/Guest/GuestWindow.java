package com.window.Guest;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class GuestWindow {
	
	JPanel panel2;
	JPanel panel;
	JPanel panel3;
	JFrame ramka1;
	JButton guestCheckIn = new JButton("Check-in");
	JButton guestCheckOut = new JButton("Check-out");
	
	public GuestWindow(final JFrame ramka) {
		panel2 = new JPanel(new FlowLayout());
		panel = new JPanel();
		panel3 = new JPanel();
		guestCheckIn = new JButton("Check-IN");
		guestCheckOut = new JButton("Check-out");
	
		ramka.add(panel2, BorderLayout.CENTER);
		ramka.add(panel, BorderLayout.NORTH);
		ramka.add(panel3, BorderLayout.SOUTH);
		ramka.setVisible(true);
		ramka.setSize(500, 500);
		
		JButton Guestadd = new JButton("New Guest");
		JButton GuestDelete = new JButton("Guest Delete");
		JButton GuestUpdate = new JButton("Guet Update");
		JButton GuestsList = new JButton("Guests List");
		
		Guestadd.setPreferredSize(new Dimension(150,50));
		GuestDelete.setPreferredSize(new Dimension(150,50));
		GuestUpdate.setPreferredSize(new Dimension(150,50));
		GuestsList.setPreferredSize(new Dimension(150,50));
		guestCheckIn.setPreferredSize(new Dimension(150, 50));
		guestCheckOut.setPreferredSize(new Dimension(150, 50));
		
		JButton back = new JButton("Back");
		panel3.add(back);
		
		JLabel label = new JLabel("Guest Menu");
		panel.add(label);
		
		panel2.add(Guestadd);
		panel2.add(GuestDelete);
		panel2.add(GuestUpdate);
		panel2.add(GuestsList);
		panel2.add(guestCheckIn);
		panel2.add(guestCheckOut);
		ramka.validate();
		ramka.repaint();
		
		Guestadd.addActionListener (new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ramka.remove(panel);
				ramka.remove(panel2);
				ramka.remove(panel3);
				new GuestAdd(ramka);
				ramka.repaint();
				ramka.validate();
			}
		}); 
		
		GuestDelete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				ramka.remove(panel);
				ramka.remove(panel2);
				ramka.remove(panel3);
				new DeleteGuest(ramka);
			}
		});
		
		guestCheckIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ramka.remove(panel);
				ramka.remove(panel2);
				ramka.remove(panel3);
				new GuestCheckIn(ramka);
			}
			
		});
		
		guestCheckOut.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				ramka.remove(panel);
				ramka.remove(panel2);
				ramka.remove(panel3);
				new GuestCheckOut(ramka);
				ramka.repaint();
				ramka.validate();
			}
		});
		
		back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ramka.remove(panel);
				ramka.remove(panel2);
				ramka.remove(panel3);
				ApplicationContext context = new AnnotationConfigApplicationContext(DatabaaseMainWindow.class);
				DatabaaseMainWindow window = context.getBean(DatabaaseMainWindow.class);
				window.WindowDisplay();
			}
		});
	}
}
