<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.fdmgroup.StockAnalyzer.Stock, java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">
	<script src="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.js"></script>
</head>
<body>


<h1><c:out value="${stock.symbol}"/></h1>

<canvas id="myChart" width="400" height="200"></canvas>
<table class="ui celled table">
		<thead>
			<tr>
				<th>Date</th>
				<th>Price</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${formattedPriceList}" var="price">
			<tr>
			
				<td data-label="Date"><fmt:formatDate value="${price.key}" pattern="yyyy-MM-dd" /></td>	
				<td data-label="Price"><c:out value="${price.value}"/></td>
			</tr>
			</c:forEach>

		</tbody>
	</table>


<script>
var ctx = document.getElementById('myChart').getContext('2d');
	var stockData = [];
	var stockLabel = [];
	<c:forEach items="${formattedPriceList}" var="price">
	stockData.push('<c:out value="${price.value}"/>');
	stockLabel.push('<fmt:formatDate value="${price.key}" pattern="yyyy-MM-dd" />');
	</c:forEach>
	var stockData = stockData.reverse();
	var stockLabel = stockLabel.reverse();
	console.log(stockLabel);
var chart = new Chart(ctx, {
    // The type of chart we want to create
    type: 'line',

    // The data for our dataset
    data: {
        labels: stockLabel,
        datasets: [{
            label: 'Daily Prices',
            backgroundColor: 'rgb(255, 99, 132)',
            borderColor: 'rgb(255, 99, 132)',
            data: stockData
        }]
    },

    // Configuration options go here
    options: {}
});
</script>
		







<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.js"></script>
</body>
</html>