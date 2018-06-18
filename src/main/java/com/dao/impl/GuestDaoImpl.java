package com.dao.impl;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;
import com.dao.GuestDao;
import com.entity.Guest;
import com.entity.Room;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

@Repository
public class GuestDaoImpl implements GuestDao {

	public void add(Guest guest) {
		System.out.println("Wywolana metoda addGuest");
		//Session_FactoryImpl q = new Session_FactoryImpl();
		ApplicationContext context1 = new AnnotationConfigApplicationContext(Session_FactoryImpl.class);
		Session_FactoryImpl sessionFactory1 = context1.getBean(Session_FactoryImpl.class);
		SessionFactory sessionFactory = sessionFactory1.SessionFact();
		System.out.println("Wywolana metoda addGuest");
		try {
			Session session = sessionFactory.openSession();
			session.save(guest);
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		((AnnotationConfigApplicationContext)context1).close();
	}

	public void remove(Guest guest) {
		ApplicationContext context1 = new AnnotationConfigApplicationContext(Session_FactoryImpl.class);
		Session_FactoryImpl sessionFactory1 = context1.getBean(Session_FactoryImpl.class);
		SessionFactory sessionFactory = sessionFactory1.SessionFact();
		try {
			System.out.println("wykonujeb metode delete");
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.delete(guest);
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		((AnnotationConfigApplicationContext)context1).close();
	}

	public void update(Guest guest) {
		// TODO Auto-generated method stub
	}

	public void read(Guest guest) {
		ApplicationContext context1 = new AnnotationConfigApplicationContext(Session_FactoryImpl.class);
		Session_FactoryImpl sessionFactory1 = context1.getBean(Session_FactoryImpl.class);
		SessionFactory sessionFactory = sessionFactory1.SessionFact();
		try {
			System.out.println("wykonujeb metode read");
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		((AnnotationConfigApplicationContext)context1).close();
	}

	public List<Guest> readAll() {
		
		List<Guest> result = new ArrayList<Guest>();
		ApplicationContext context1 = new AnnotationConfigApplicationContext(Session_FactoryImpl.class);
		Session_FactoryImpl sessionFactory1 = context1.getBean(Session_FactoryImpl.class);
		SessionFactory sessionFactory = sessionFactory1.SessionFact();
		try {
			System.out.println("wykonujeb metode readAll");
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query q1 = session.createQuery("from Guest");
			result = q1.getResultList();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		((AnnotationConfigApplicationContext)context1).close();
		return result;
	
	}

	public ArrayList<Guest> readAllinHotel() {
		ArrayList<Guest> result = new ArrayList<Guest>();
		ArrayList<Room> listOfRooms = new ArrayList<Room>();
		
		ApplicationContext context1 = new AnnotationConfigApplicationContext(Session_FactoryImpl.class);
		Session_FactoryImpl sessionFactory1 = context1.getBean(Session_FactoryImpl.class);
		SessionFactory sessionFactory = sessionFactory1.SessionFact();
		try {
			System.out.println("wykonujeb metode readAll");
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query q1 = session.createQuery("from Room");
			listOfRooms = (ArrayList<Room>) q1.getResultList();
			session.getTransaction().commit();
			session.close();
			System.out.println("Oto wczytana lista pokoji");
			for (Room r : listOfRooms) {
				System.out.println("oto pokoj - " + r.getRoomNumber());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			System.out.println("wykonujeb metode readAll");
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			System.out.println("Oto lista gosci");
			for (Room r : listOfRooms) {
				System.out.println("Oto pokoj - " + r.getRoomNumber());
				for (Guest g:r.getGuests()) {
					System.out.println("Oto lista gosci - " + g.getPesel());
					result.add(g);
				}
				session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Wychodze z pentli readAll");
		((AnnotationConfigApplicationContext)context1).close();
		return result;
	}
}
