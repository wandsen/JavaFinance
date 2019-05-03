package com.fdmgroup.StockAnalyzer;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "Stock")
public class Stock {

	@Override
	public String toString() {
		return "Stock [id=" + id + ", symbol=" + symbol + ", lastRefreshed=" + lastRefreshed + ", priceList="
				+ priceList + "]";
	}

	@Id
	@GeneratedValue
	long id;
	String symbol;
	String lastRefreshed;
	
	@Lob
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "Price")
	Map<String, String> priceList = new LinkedHashMap<String, String>();

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getLastRefreshed() {
		return lastRefreshed;
	}

	public void setLastRefreshed(String lastRefreshed) {
		this.lastRefreshed = lastRefreshed;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setPriceList(Map<String, String> priceList) {
		this.priceList = priceList;
	}

	public Map<String, String> getPriceList() {
		return priceList;
	}

}
