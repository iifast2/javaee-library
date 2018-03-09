<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, data.Book" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>All books in Library</title>
</head>
<body>
<h1>Books:</h1>
<table style="width:50%" border="1">
<tr>
	<th>Title</th>
	<th>Author</th>
	<th>Year</th>
	<th>Pages</th>
</tr>
<%
	@SuppressWarnings("unchecked")
	List<Book> booksList = (List<Book>)request.getAttribute("booksList");
	if(booksList != null)
		for(Book book: booksList){			
%>
<tr>
            <td><%= book.getTitle() %></td>
            <td><%= book.getAuthor() %></td>
            <td><%= book.getYear() %></td>
            <td><%= book.getPages() %></td>
        </tr>
    <%
            }
    %>


</table>
</body>
</html>