<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
	Customer Page under construction
	<c:forEach items="${furnitureDetails}" var="f">
		<form action="/KsquareFurniture/users/furnitureId/quantity"
			method=post>
			<table width=550px style="margin-top: 20px; margin-left: 30px">
				<th>Item</th>
				<th>Quantity</th>
				<tr>
					<td><br>
						<li style="list-style-type: none;"><input type="radio"
							name="myradio" value="${f.type}" /> <c:out value="${f.type}" />
					</li>
					<br></td>
					<td><br> <input type="text"
						style="width: 50px; margin-left: 40px; margin-top: -15px;"
						name="quantity" /> <br></td>

					<input type="hidden" name="furnitureId" value="${f.furnitureId}" />

					<td><input type="submit" value="Place Order" /></td>
				</tr>
			</table>

		</form>
	</c:forEach>
</body>
</html>