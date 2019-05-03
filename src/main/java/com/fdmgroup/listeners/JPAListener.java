package com.fdmgroup.listeners;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class JPAListener implements ServletContextListener {

	private static EntityManagerFactory myEmf;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		myEmf = Persistence.createEntityManagerFactory("stock");
		sce.getServletContext().setAttribute("emf", myEmf);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		if (myEmf != null)
			myEmf.close();

	}

}
