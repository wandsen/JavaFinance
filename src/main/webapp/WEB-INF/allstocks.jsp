<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.fdmgroup.StockAnalyzer.Stock, java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.js"></script>
</head>
<body>
	<h1>All Stocks</h1>

	<table class="ui celled table">
		<thead>
			<tr>
				<th>Symbol</th>
				<th>Date</th>
				<th>Price</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${stocks}" var="stock">
			<tr>
				<td data-label="Symbol"><c:out value="${stock.symbol}" /></td>
				<td data-label="Date"><c:out value="${stock.lastRefreshed}" /></td>
				      <c:set var = "latestDate" scope = "session" value = "${stock.lastRefreshed}"/>
 
				<td data-label="Price"><c:out value="${stock.priceList[latestDate]}" /></td>
			</tr>
		</c:forEach>

		</tbody>
	</table>

</body>
</html>