<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.springapp.mvc.Tweet" %>
<html>
<head>
    <title>Hello Twitter</title>
</head>
<body>
<h3>Women Related Tweets</h3>

<%
    List<Tweet> generalTweets =(ArrayList) request.getAttribute("tweet");
    for (int i = 0 ; i < generalTweets.size(); i++){
%>

 <%=generalTweets.get(i).getText()%>
</br>
</br>
<%
    }
%>

</body>
</html>