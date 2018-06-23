package com;

import javax.swing.JFrame;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.window.Guest.DatabaaseMainWindow;

public class DataBaseManager {
	SessionFactory sessionFactory;
	static private JFrame ramka = new JFrame();
	
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(DatabaaseMainWindow.class);
		DatabaaseMainWindow window = context.getBean(DatabaaseMainWindow.class);
		window.WindowDisplay(ramka);
		((AnnotationConfigApplicationContext)context).close();
		
	}
}
