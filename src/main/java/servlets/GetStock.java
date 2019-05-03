package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.StockAnalyzer.ObjectMapperDaily;
import com.fdmgroup.StockAnalyzer.Stock;
import com.fdmgroup.StockAnalyzer.StockDao;

public class GetStock extends HttpServlet {

	Logger logger = LogManager.getLogger();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		EntityManagerFactory emf = (EntityManagerFactory) this.getServletContext().getAttribute("emf");
		StockDao sd = new StockDao(emf);

		// Importing stock from Api
		logger.trace("User searched for stock symbol : " + req.getParameter("name"));
		ObjectMapperDaily obj = new ObjectMapperDaily(req.getParameter("name"), "TIME_SERIES_DAILY");
		Stock stock = obj.mapJsonTreeToObject();

		// Retrieve from database. If null, means its was not imported from API.
		Stock returnedStock = sd.getStockByName(req.getParameter("name"));
		logger.trace("Retrieve from database by stockname: " + returnedStock);

		if (returnedStock == null) {
			logger.trace("Database has no such stock. Attempt to add : " + stock + " into database");
			if (stock != null) {
				sd.addItem(stock);
				logger.trace("Added stock to database: " + stock);

				// Stock has been added into database and retrieving from database
				returnedStock = sd.getStockByName(req.getParameter("name"));

				logger.trace("Found entry in database: " + returnedStock);
				Map<Date, String> formattedPriceList = new TreeMap<Date, String>(Collections.reverseOrder());

				for (Map.Entry<String, String> entry : returnedStock.getPriceList().entrySet()) {

					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					try {

						Date date = formatter.parse(entry.getKey());
						formattedPriceList.put(date, entry.getValue());
						logger.trace("Format: " + date);
						logger.trace("Value: " + entry.getValue());
					} catch (ParseException e) {
						e.printStackTrace();
					}

				}
				
				for (Map.Entry<Date, String> f : formattedPriceList.entrySet()) {
					logger.trace("FormattedDate: " + f.getKey());
				}

				req.setAttribute("noSuchStockError", false);
				req.setAttribute("stock", returnedStock);
				req.setAttribute("formattedPriceList", formattedPriceList);
				req.setAttribute("latestPrice", returnedStock.getPriceList().get(returnedStock.getLastRefreshed()));

				req.getRequestDispatcher("./WEB-INF/getstockbyname.jsp").forward(req, resp);

			} else {
				logger.trace("Unable to retrieve data from api");

				req.setAttribute("noSuchStockError", true);
				req.getRequestDispatcher("./index.jsp").forward(req, resp);
			}

		} else {
			logger.trace("Found entry in database: " + returnedStock);
			Map<Date, String> formattedPriceList = new TreeMap<Date, String>(Collections.reverseOrder());

			for (Map.Entry<String, String> entry : returnedStock.getPriceList().entrySet()) {

				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				try {

					Date date = formatter.parse(entry.getKey());
					formattedPriceList.put(date, entry.getValue());
					logger.trace("Format: " + date);
					logger.trace("Value: " + entry.getValue());
				} catch (ParseException e) {
					e.printStackTrace();
				}

			}
			
			for (Map.Entry<Date, String> f : formattedPriceList.entrySet()) {
				logger.trace("FormattedDate: " + f.getKey());
			}

			req.setAttribute("noSuchStockError", false);
			req.setAttribute("stock", returnedStock);
			req.setAttribute("formattedPriceList", formattedPriceList);
			req.setAttribute("latestPrice", returnedStock.getPriceList().get(returnedStock.getLastRefreshed()));
			req.getRequestDispatcher("./WEB-INF/getstockbyname.jsp").forward(req, resp);
		}

	}

}
