package servlets;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.StockAnalyzer.Stock;
import com.fdmgroup.StockAnalyzer.StockDao;

public class GetStocks extends HttpServlet {

	Logger logger = LogManager.getLogger();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StockDao sd = new StockDao((EntityManagerFactory) this.getServletContext().getAttribute("emf"));
		
		List<Stock> stocks = sd.getAllStocks();
		for (Stock s : stocks) {
			logger.trace(s);
		}
		
		req.setAttribute("stocks", stocks);
		req.getRequestDispatcher("./WEB-INF/allstocks.jsp").forward(req, resp);
		
	}

}
