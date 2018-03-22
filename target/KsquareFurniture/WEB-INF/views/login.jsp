<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<form action="LoginController" method="get">
<table cellspacing="10" >
<tr>
 <td height=60px>
    UserId:</td><td><input type="text" style="height:30px; font-weight:bold" name="userId"  placeholder="userId" /></div>
 </td>
</tr>
<tr>
<td height=60px>
    Password:</td><td><input type="text" style="height:30px; font-weight:bold" name="password"  placeholder="password" /></div>
 </td>
 </tr>
</table>

<input type="submit" value="Login" class = "btn btn-md btn-success" style="left: 1%">
</form>
</div>

</body>
</html>