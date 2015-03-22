<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.springapp.mvc.Tweet" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <title>Hello Twitter</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
</head>
<body>

<h3>HashTag Women Tweets</h3>
<%
    List<Tweet> generalTweets =(ArrayList) request.getAttribute("women");
    for (int i = 0 ; i < generalTweets.size(); i++){
%>
<br><br>
 <%=generalTweets.get(i).getText()%>
<% } %>
<script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
</body>
</html>