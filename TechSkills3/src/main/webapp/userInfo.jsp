<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Info</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	
	<h1>
	<% 
	String data=(String)request.getAttribute("result");
	out.println(data);
	%> 
	</h1>
</body>
</html>