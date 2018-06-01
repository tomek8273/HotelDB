package com;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dao.impl.GuestDaoImpl;
import com.dao.impl.RoomDaoImpl;
import com.dao.impl.Session_FactoryImpl;
import com.entity.Guest;
import com.window.Guest.DatabaaseMainWindow;
import com.window.Guest.GuestCheckIn;

public class DataBaseManager {

	SessionFactory sessionFactory;
	static List<Guest> guestsList;

	protected void setUp() {

		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

	protected void exit() {
		sessionFactory.close();
	}

	public static void main(String[] args) {
		DataBaseManager window1 = new DataBaseManager();
		// window1.setUp();

		ApplicationContext context = new AnnotationConfigApplicationContext(DatabaaseMainWindow.class);
		DatabaaseMainWindow window = context.getBean(DatabaaseMainWindow.class);

		window.WindowDisplay();

		Guest guest = new Guest();
		guest.setFirst_name("Adam");
		guest.setDate_of_birth("20.2.2000");
		guest.setLast_name("Kowalski");
		guest.setPesel("08098423423");

		ApplicationContext context1 = new AnnotationConfigApplicationContext(GuestDaoImpl.class);

		GuestDaoImpl guestDao = context1.getBean(GuestDaoImpl.class);
		
		guestDao.add(guest);
		//guestDao.remove(guest);
		
		System.out.println("Jestem przed wykonaniem readAll");
		guestsList = guestDao.readAll();
		guestDao.read(guest);
		guestDao.readAllinHotel();

	}

}
