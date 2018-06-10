package com.window.Guest;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.persistence.Query;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.dao.impl.GuestDaoImpl;
import com.dao.impl.RoomDaoImpl;
import com.dao.impl.Session_FactoryImpl;
import com.entity.Guest;
import com.entity.Room;

public class GuestCheckIn {
	JPanel panel1;
	JPanel panel2;
	JLabel chosenData;
	JLabel data;
	JLabel data1;
	Guest resultGuest = new Guest();
	Room resultRoom = new Room();
	Room room = new Room();

	JList<String> guestList;
	JList<String> roomsList;
	JButton okButton;
	DefaultListModel<String> guestL;
	DefaultListModel<String> roomsL;
	List<Guest> guestsList;
	List<Room> roomsList1;
	JScrollPane scroll;
	JButton backButton;
	Query q1;
	Query r1;

	GuestDaoImpl guestDao = new GuestDaoImpl();
	RoomDaoImpl roomDao = new RoomDaoImpl();

	public GuestCheckIn(final JFrame ramka) {

		guestL = new DefaultListModel<String>();
		roomsL = new DefaultListModel<String>();
		chosenData = new JLabel("Chosen Data - ");
		data = new JLabel();
		data1 = new JLabel();
		backButton = new JButton("Back");

		guestsList = guestDao.readAll();
		roomsList1 = roomDao.readAll();
		System.out.println("Oto lista roomsList1 - " + roomsList1);

		for (Guest g : guestsList) {
			guestL.addElement(g.getFirst_name() + " " + g.getLast_name() + " " + g.getPesel());
		}
		System.out.println(guestL);

		System.out.println("przed petla tworzaca liste pokoi");
		for (Room rr : roomsList1) {
			roomsL.addElement(rr.getRoomNumber());
			System.out.println(rr.getRoomNumber());
		}

		System.out.println("Jestem za petla");
		System.out.println(roomsL);

		guestList = new JList<String>(guestL);
		scroll = new JScrollPane(guestList);
		roomsList = new JList<String>(roomsL);
		okButton = new JButton("OK");
		panel1 = new JPanel();
		panel2 = new JPanel();
		
		panel1.add(scroll);
		panel1.add(roomsList);
		panel1.add(chosenData);
		panel1.add(data);
		panel1.add(data1);
		panel2.add(okButton);
		panel2.add(backButton);
		ramka.add(panel1, BorderLayout.CENTER);
		ramka.add(panel2, BorderLayout.SOUTH);
		ramka.repaint();
		ramka.validate();

		guestList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				System.out.println("oto wybrant element - " + guestList.getSelectedValue());
				data.setText(guestList.getSelectedValue());
			}
		});

		roomsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				data1.setText(roomsList.getSelectedValue());
				System.out.println("oto wybrany element z listy pokoji - " + roomsList.getSelectedValue());
			}

		});

		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ApplicationContext context1 = new AnnotationConfigApplicationContext(Session_FactoryImpl.class);
				Session_FactoryImpl sessionFactory1 = context1.getBean(Session_FactoryImpl.class);
				SessionFactory sessionFactory = sessionFactory1.SessionFact();
				String[] splited = guestList.getSelectedValue().split(" ");
				try {
					System.out.println("wykonujeb metode select - wyszukuje obiekt z tabeli Guests");
					Session session = sessionFactory.openSession();
					session.beginTransaction();
					System.out.println("Oto pesel wybranego goscia - "+splited[2]);
					q1 = session.createQuery("from Guest g Where g.guestPesel =:pesel");
					q1.setParameter("pesel", splited[2]);
					resultGuest = (Guest) q1.getSingleResult();
					session.close();
				} catch (NonUniqueResultException e) {
					e.printStackTrace();
					System.out.println("Operacja niemozliwa, wiele rekordow w bazie");
				}
				try {
					System.out.println("wykonujeb metode select - wyszukuje obiekt z tabeli Room");
					Session session = sessionFactory.openSession();
					session.beginTransaction();
					r1 = session.createQuery("from Room r Where r.roomNumber =:number");
					r1.setParameter("number", roomsList.getSelectedValue());
					resultRoom = (Room) r1.getSingleResult();
					System.out.println("oto wybrany gosc do check in - "+resultGuest.getPesel());
					room.getGuests().add(resultGuest);
					session.save(room);
					session.save(resultRoom);
					session.getTransaction().commit();
					session.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		});

		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ramka.remove(panel1);
				ramka.remove(panel2);
				new GuestWindow(ramka);
				ramka.repaint();
				ramka.validate();
			}
		});
	}

}
