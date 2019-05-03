package com.fdmgroup.StockAnalyzer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StockDao {

	EntityManagerFactory emf;
	Logger logger = LogManager.getLogger();

	public StockDao(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public void addItem(Stock item) {
		EntityManager em = emf.createEntityManager();
		logger.trace("adding item");
		em.getTransaction().begin();
		em.persist(item);
		em.getTransaction().commit();
		em.close();
	}

	public Stock getStockByName(String symbol) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Stock> query = em.createQuery("FROM Stock s WHERE s.symbol=:symbol", Stock.class);
		query.setParameter("symbol", symbol);

		Stock returnedStock = null;

		try {
			returnedStock = query.getSingleResult();
		} catch (NoResultException nre) {

		}
		em.close();
		if (returnedStock != null) {
			return returnedStock;
		} else
			return null;
	}

	public List<Stock> getAllStocks() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Stock> query = em.createQuery("FROM Stock", Stock.class);
		System.out.println("calling db");
		List<Stock> allStockData = new ArrayList<Stock>();
		allStockData = query.getResultList();
		em.close();
		return allStockData;
	}

	public void updateItem(Stock stock) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(stock);
		em.getTransaction().commit();
		em.close();

	}

	public void removeItem(Stock stock) {
		EntityManager em = emf.createEntityManager();
		Stock foundStock = em.find(Stock.class, stock.getId());
		em.getTransaction().begin();
		em.remove(foundStock);
		em.getTransaction().commit();
		em.close();
	}

}
