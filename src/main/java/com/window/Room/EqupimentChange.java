package com.window.Room;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.persistence.Query;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dao.impl.RoomDaoImpl;
import com.dao.impl.Session_FactoryImpl;
import com.entity.Equipment;
import com.entity.Room;

public class EqupimentChange {
	JPanel panel1;
	JPanel panel2;
	JButton okButton;
	JButton backButton;
	JButton addEquipment;
	JButton removeEquipment;
	
	ArrayList<Room> listOfRooms;
	ArrayList<Equipment> listOfEquipment;
	JScrollPane scroll;
	JScrollPane scrollEquipmentList;
	RoomDaoImpl roomDao;
	DefaultListModel<String> roomsL;
	DefaultListModel<String> equipmentL;
	JList<String> roomsList;
	JList<String> equipmentList;
	

	JList<String> equpimentList;

	public EqupimentChange(final JFrame ramka) {
		listOfRooms = new ArrayList<Room>();
		listOfEquipment = new ArrayList<Equipment>();
		roomsList = new JList<String>();
		roomsL = new DefaultListModel<String>();
		equipmentL = new DefaultListModel<String>();
		
		/*
		equipmentL.addElement("Stol");
		equipmentL.addElement("Krzeslo");
		equipmentL.addElement("Lampa");
		*/
		
		equipmentList = new JList<String>(equipmentL);
		
		scrollEquipmentList = new JScrollPane(equipmentList);
		
		backButton = new JButton("back");
		addEquipment = new JButton("add Equipment");
		removeEquipment = new JButton("remove Equipment");
		
	

		ApplicationContext context1 = new AnnotationConfigApplicationContext(Session_FactoryImpl.class);
		Session_FactoryImpl sessionFactory1 = context1.getBean(Session_FactoryImpl.class);
		SessionFactory sessionFactory = sessionFactory1.SessionFact();
		try {
			Session session = sessionFactory.openSession();
			Equipment equipmentEl = new Equipment();
			
			session.beginTransaction();
			equipmentEl.setEquipmentElement("Stol");
			session.save(equipmentEl);
			session.getTransaction().commit();
			
			session.beginTransaction();
			equipmentEl.setEquipmentElement("Krzeslo");
			session.save(equipmentEl);
			session.getTransaction().commit();
			
			session.beginTransaction();
			equipmentEl.setEquipmentElement("Lampa");
			session.save(equipmentEl);
			session.getTransaction().commit();
			
			session.beginTransaction();
			Query q1 = session.createQuery("from Room");
			Query q2 = session.createQuery("from Equipment");
			listOfRooms = (ArrayList<Room>) q1.getResultList();
			listOfEquipment = (ArrayList<Equipment>) q2.getResultList();
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		for (Equipment eq : listOfEquipment ) {
			equipmentL.addElement(eq.getEquipmentElement());
			System.out.println(eq.getEquipmentElement());
		}
		
		for (Room rr : listOfRooms) {
			roomsL.addElement(rr.getRoomNumber());
			System.out.println(rr.getRoomNumber());
		}
		roomsList = new JList<String>(roomsL);
		panel1 = new JPanel();
		panel2 = new JPanel();
		scroll = new JScrollPane(roomsList);
		
		
		panel1.add(scroll);
		panel2.add(addEquipment);
		panel2.add(removeEquipment);
		panel2.add(backButton);
		
		ramka.add(panel1,BorderLayout.CENTER);
		ramka.add(panel2, BorderLayout.SOUTH);
		
		addEquipment.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				panel1.add(scrollEquipmentList);
				panel2.remove(removeEquipment);
				ramka.repaint();
				ramka.validate();
				
			}
		});
		
		removeEquipment.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		backButton.addActionListener(new ActionListener() {
			
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
