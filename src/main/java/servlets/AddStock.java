package servlets;

import java.io.IOException;

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

public class AddStock extends HttpServlet {

	Logger logger = LogManager.getLogger();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StockDao sd = new StockDao((EntityManagerFactory) this.getServletContext().getAttribute("emf"));
		
		//Adding stock to database
		ObjectMapperDaily obj = new ObjectMapperDaily(req.getParameter("name"), "TIME_SERIES_DAILY");
		Stock stock = obj.mapJsonTreeToObject();
		
		logger.trace("adding stock" + stock);
		sd.addItem(stock);
	
		resp.sendRedirect(req.getContextPath() + "/getstocks");
	}
}
