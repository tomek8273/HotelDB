package com.window.Guest;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.springframework.stereotype.Component;

import com.window.Invoice.InvoiceMainWindow;
import com.window.Room.RoomMainWindow;

@Component
public class DatabaaseMainWindow {

	static JFrame ramka;
	private JPanel panel;
	private JPanel panel2;

	public void WindowDisplay() {
		panel2 = new JPanel();
		panel = new JPanel();
		ramka = new JFrame();

		panel2.setBounds(100, 100, 200, 200);
		ramka.add(panel2, BorderLayout.CENTER);
		ramka.add(panel, BorderLayout.NORTH);
		ramka.setVisible(true);
		ramka.setSize(500, 500);

		JLabel label = new JLabel("Hotel DataBase - Main Page");
		label.setFont(new Font("Arial", 20, 20));
		panel.add(label);

		JButton Guest = new JButton("Guest");
		JButton Rooms = new JButton("Room");
		JButton Invoice = new JButton("Invoice");

		Dimension dim = new Dimension(100, 50);
		Guest.setPreferredSize(dim);
		Rooms.setPreferredSize(new Dimension(100, 50));
		Invoice.setPreferredSize(new Dimension(100, 50));

		panel2.setLayout(new FlowLayout());
		panel2.add(Guest);
		panel2.add(Rooms);
		panel2.add(Invoice);

		ramka.validate();
		ramka.repaint();

		Guest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ramka.remove(panel);
				ramka.remove(panel2);
				ramka.repaint();
				ramka.validate();
				new GuestWindow(ramka);
			}
		});

		Rooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ramka.remove(panel);
				ramka.remove(panel2);
				ramka.repaint();
				ramka.validate();
				new RoomMainWindow(ramka);

			}

		});

		Invoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ramka.remove(panel);
				ramka.remove(panel2);
				new InvoiceMainWindow(ramka);
			}
		});
	}
}
