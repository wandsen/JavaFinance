package com.fdmgroup.StockAnalyzer;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperDaily {

	Logger logger = LogManager.getLogger();

	String apiKey = "KKTLO8DUTHHVG5S1";
	String stockSymbol = "AAPL";
	String timeSeries = "TIME_SERIES_DAILY";

	public ObjectMapperDaily(String stockSymbol, String timeSeries) {
		logger.trace("The stock : " + stockSymbol + " timeSeries:  " + timeSeries
				+ "is passed into the ObjectMapperDailyConstructor");
		this.stockSymbol = stockSymbol;
		this.timeSeries = timeSeries;

	}

	public Stock mapJsonTreeToObject() {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = null;
		try {

			String url = "https://www.alphavantage.co/query?function=" + timeSeries + "&symbol=" + stockSymbol
					+ "&apikey=" + apiKey;
			logger.trace("Attempting to retrieve data from this address-----" + url);
			root = mapper.readTree(new URL(url));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Mapping to object
		Stock stockDataDaily = null;
		logger.trace("Is stock data found: " + (root.get("Error Message") == null));

		if (root.get("Error Message") == (null)) {
			stockDataDaily = new Stock();
			logger.trace("Mapping root to stockDataDaily Object");
			stockDataDaily.setSymbol(root.get("Meta Data").get("2. Symbol").textValue());
			stockDataDaily.setLastRefreshed(root.get("Meta Data").get("3. Last Refreshed").textValue());

			Iterator<Map.Entry<String, JsonNode>> fields = root.get("Time Series (Daily)").fields();

			Map<String, String> priceList = new LinkedHashMap<String, String>();
			while (fields.hasNext()) {
				
				Map.Entry<String, JsonNode> entry = fields.next();
				
				logger.trace("Date: " + entry.getKey() + " Price: " + entry.getValue().get("4. close").textValue());
				priceList.put(entry.getKey(), entry.getValue().get("4. close").textValue());

			}

			stockDataDaily.setPriceList(priceList);

		} else {
			logger.trace("root error message: " + root.get("Error Message"));
		}
		
		logger.trace("Return stock data daily object: " + stockDataDaily);
		return stockDataDaily;
	}

}
