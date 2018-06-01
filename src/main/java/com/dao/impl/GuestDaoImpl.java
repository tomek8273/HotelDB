package com.dao.impl;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;

import com.dao.GuestDao;
import com.dao.Session_Factory;
import com.entity.Guest;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

@Repository
public class GuestDaoImpl implements GuestDao {
	List<Guest> result;
	
	public void add(Guest guest) {
		
		System.out.println("Wywolana metoda addGuest");
		Session_FactoryImpl q = new Session_FactoryImpl();

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
			//Query q1 = session.createQuery("delete from Guest Where id='10'");
			//int result = q1.executeUpdate();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

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
			Query q1 = session.createQuery("from Guest Where id='10'");
			result = q1.getResultList();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	public List<Guest> readAll() {
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
		return result;
	}

	public List<Guest> readAllinHotel() {
		ApplicationContext context1 = new AnnotationConfigApplicationContext(Session_FactoryImpl.class);
		Session_FactoryImpl sessionFactory1 = context1.getBean(Session_FactoryImpl.class);
		SessionFactory sessionFactory = sessionFactory1.SessionFact();
		try {
			System.out.println("wykonujeb metode readAll");
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query q1 = session.createQuery("from Guest g where room !=null");
			result = q1.getResultList();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
