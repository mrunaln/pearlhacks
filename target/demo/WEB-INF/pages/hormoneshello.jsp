<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.springapp.mvc.Tweet" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <title>Hello Twitter</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel='stylesheet' href='//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css'>


    <style type="text/css">
        body { background: #c73124; }
    </style>

</head>
<body>

<h3> #Women #HORMONES Tweets</h3>
<br><br>
<button type="button" class="btn btn-primary">Fashion tweets</button>

<button type="button" class="btn btn-primary"> General Women tweets</button>

<button type="button" class="btn btn-primary">Yoga Tweets</button>

<br><br>
<%
    List<Tweet> hormonesTweets =(ArrayList) request.getAttribute("hormones");
    for (int i = 0 ; i < hormonesTweets.size(); i++){
%>
<%=hormonesTweets.get(i).getText()%>
<br><br>
<% } %>
<script type="text/css" src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"></script>
<script type="text/javascript" src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</body>
</html>