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

import org.apache.log4j.Logger;
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
	private JPanel panel1;
	private JPanel panel2;
	private JLabel chosenData;
	private JLabel data;
	private JLabel data1;
	private Guest resultGuest = new Guest();
	private Room resultRoom = new Room();
	private Room room = new Room();
	private static Logger log = Logger.getLogger(GuestCheckIn.class);

	private JList<String> guestList;
	private JList<String> roomsList;
	private JButton okButton;
	private DefaultListModel<String> guestL;
	private DefaultListModel<String> roomsL;
	private List<Guest> guestsList;
	private List<Room> roomsList1;
	private JScrollPane scroll;
	private JButton backButton;
	private Query q1;
	private Query r1;

	private GuestDaoImpl guestDao;
	private RoomDaoImpl roomDao;

	public GuestCheckIn(final JFrame ramka) {

		guestL = new DefaultListModel<String>();
		roomsL = new DefaultListModel<String>();
		chosenData = new JLabel("Chosen Data - ");
		data = new JLabel();
		data1 = new JLabel();
		backButton = new JButton("Back");
		
		ApplicationContext context1 = new AnnotationConfigApplicationContext(GuestDaoImpl.class);
		guestDao= context1.getBean(GuestDaoImpl.class);
		((AnnotationConfigApplicationContext)context1).close();
		
		ApplicationContext context2 = new AnnotationConfigApplicationContext(RoomDaoImpl.class);
		roomDao= context2.getBean(RoomDaoImpl.class);
		((AnnotationConfigApplicationContext)context2).close();
		
		guestsList = guestDao.readAll();
		roomsList1 = roomDao.readAll();
		System.out.println("Oto lista roomsList1 - " + roomsList1);

		for (Guest g : guestsList) {
			guestL.addElement(g.getFirst_name() + " " + g.getLast_name() + " " + g.getPesel());

		}
				
		log.info("Przed petla tworzaca liste pokoji");
		for (Room rr : roomsList1) {
			roomsL.addElement(rr.getRoomNumber());
			log.info("Oto dodany element do listy pokoji - "+rr.getRoomNumber());
		}
		log.info("Za petla tworzaca liste pokoji");
		

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
				log.info("Oto wybrany elememnt z listy gosci - "+guestList.getSelectedValue());
				data.setText(guestList.getSelectedValue());
			}
		});

		roomsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				data1.setText(roomsList.getSelectedValue());
				log.info("Oto wybrany element z listy pokoji - "+roomsList.getSelectedValue());
			}

		});

		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				log.info("Wcisniety przysick OK w Guest Check-in");
				ApplicationContext context1 = new AnnotationConfigApplicationContext(Session_FactoryImpl.class);
				Session_FactoryImpl sessionFactory1 = context1.getBean(Session_FactoryImpl.class);
				SessionFactory sessionFactory = sessionFactory1.SessionFact();
				String[] splited = guestList.getSelectedValue().split(" ");
				try {
					log.info("Wykonywana metoda Select - wyszukanie obiektow w tabeli guest");
					Session session = sessionFactory.openSession();
					session.beginTransaction();
					log.info("Oto PESEL wybranego goœcia - "+splited[2]);
					q1 = session.createQuery("from Guest g Where g.guestPesel =:pesel");
					q1.setParameter("pesel", splited[2]);
					resultGuest = (Guest) q1.getSingleResult();
					session.close();
				} catch (NonUniqueResultException e) {
					e.printStackTrace();
					System.out.println("Operacja niemozliwa, wiele rekordow w bazie");
				}
				
				try {
					log.info("Wykonuje metode SELECT - wyszukuje obiektow w tabeli Room");
					Session session = sessionFactory.openSession();
					session.beginTransaction();
					r1 = session.createQuery("from Room r Where r.roomNumber =:number");
					r1.setParameter("number", roomsList.getSelectedValue());
					resultRoom = (Room) r1.getSingleResult();
					log.info("Oto wybramy gosc do check-in - "+resultGuest.getPesel());
					room.getGuests().add(resultGuest);
					session.save(room);
					session.save(resultRoom);
					session.getTransaction().commit();
					session.close();
					
				} catch (Exception e) {
					log.info("Rzucony wyjatek w tranzakcji w GuestCheckIn");
					e.printStackTrace();
				}
				((AnnotationConfigApplicationContext)context1).close();
			}

		});

		backButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				log.info("Wycisniety przycisk BACK w Guest Check-in");
				ramka.remove(panel1);
				ramka.remove(panel2);
				
				ApplicationContext context = new AnnotationConfigApplicationContext(DatabaaseMainWindow.class);
				DatabaaseMainWindow window = context.getBean(DatabaaseMainWindow.class);
				window.WindowDisplay(ramka);
				((AnnotationConfigApplicationContext)context).close();
				
				ramka.repaint();
				ramka.validate();
			}
		});
	}

}
