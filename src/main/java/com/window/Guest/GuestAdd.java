package com.window.Guest;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dao.impl.GuestDaoImpl;
import com.entity.Guest;

public class GuestAdd {

	JPanel panel;
	JPanel panel2;
	JPanel panel3;
	JButton back;

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
		final JTextField text = new JTextField();

		JLabel label2 = new JLabel("Last name");
		final JTextField text1 = new JTextField("Last name");

		JLabel label3 = new JLabel("Date of birth");
		final JTextField text2 = new JTextField("Date of birth");

		JLabel label4 = new JLabel("PESEL");
		final JTextField text3 = new JTextField("PESEL");

		panel.add(label);

		panel2.add(label1);
		panel2.add(text);

		panel2.add(label2);
		panel2.add(text1);
		panel2.add(label3);
		panel2.add(text2);
		panel2.add(label4);
		panel2.add(text3);

		JButton button = new JButton("OK");
		panel3.add(button);
		panel3.add(back);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Guest guest = new Guest();
				guest.setFirst_name(text.getText());
				guest.setLast_name(text1.getText());
				guest.setDate_of_birth(text2.getText());
				guest.setPesel(text3.getText());

				ApplicationContext context1 = new AnnotationConfigApplicationContext(GuestDaoImpl.class);
				GuestDaoImpl guestDao = context1.getBean(GuestDaoImpl.class);
				guestDao.add(guest);
			}
		});

		back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
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

}
