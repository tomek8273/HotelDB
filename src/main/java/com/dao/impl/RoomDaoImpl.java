package com.dao.impl;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;

import com.dao.RoomDao;
import com.entity.Room;

@Repository
public class RoomDaoImpl implements RoomDao{

	List<Room> result;
	
	public void addRoom(Room room) {
		
			ApplicationContext context1 = new AnnotationConfigApplicationContext(Session_FactoryImpl.class);
			Session_FactoryImpl sessionFactory1 = context1.getBean(Session_FactoryImpl.class);
			SessionFactory sessionFactory = sessionFactory1.SessionFact();

			System.out.println("Wywolana metoda addRoom");
			try {
				Session session = sessionFactory.openSession();
				session.save(room);
				session.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}

	public void addEquipment() {
		// TODO Auto-generated method stub
		
	}

	public void remove(Room room) {
		// TODO Auto-generated method stub
		
	}

	public void update(Room room) {
		// TODO Auto-generated method stub
		
	}

	public void read(Room room) {
		// TODO Auto-generated method stub
		
	}

	public List <Room> readAll() {
		
		ApplicationContext context1 = new AnnotationConfigApplicationContext(Session_FactoryImpl.class);
		Session_FactoryImpl sessionFactory1 = context1.getBean(Session_FactoryImpl.class);
		SessionFactory sessionFactory = sessionFactory1.SessionFact();
		try {
			System.out.println("wykonujeb metode readAllRooms");
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query q1 = session.createQuery("from Room");
			result = q1.getResultList();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
