package com.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Component;
import com.dao.Session_Factory;

@Component
public class Session_FactoryImpl implements Session_Factory {
	
	SessionFactory sessionFactory;
	
	public SessionFactory SessionFact() {
		System.out.println("Wywoluje metode Session Facotry w Bean");
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		try {
			sessionFactory = (SessionFactory) new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy(registry);
		}
		return (SessionFactory) sessionFactory;	
	}
}
