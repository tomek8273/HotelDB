package com.dao.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dao.InvoiceDao;
import com.entity.Guest;
import com.entity.Invoice;

public class InvoiceDaoImpl implements InvoiceDao{

	public void AddInvoice(Invoice invoice, Guest guest) {
		
		ApplicationContext context1 = new AnnotationConfigApplicationContext(Session_FactoryImpl.class);
		Session_FactoryImpl sessionFactory1 = context1.getBean(Session_FactoryImpl.class);
		SessionFactory sessionFactory = sessionFactory1.SessionFact();
		
		try {
			System.out.println("wykonujeb metode AddInvoice");
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			invoice.setGuest(guest);
			session.save(invoice);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		((AnnotationConfigApplicationContext)context1).close();
		
	}

	public void RemoveInvoice() {
		// TODO Auto-generated method stub
		
	}

	public void UodateInvoice() {
		// TODO Auto-generated method stub
		
	}

	public void ReadInvoice() {
		// TODO Auto-generated method stub
		
	}

}
