<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Library</title>
</head>
<body>
<h1>Welcome to Library!</h1>
    <form action="ControllerServlet" method="post">
        <input type="text" placeholder="Title" name="title"><br>
        <input type="text" placeholder="Author" name="author"><br>
        <input type="number" placeholder="Year" name="year"><br>
        <input type="number" placeholder="Pages" name="pages"><br>
        Add <input type="radio" name="option" value="add"> Update  <input type="radio" name="option" value="update">
        Delete <input type="radio" name="option" value="delete"> Show all books <input type="radio" name="option" value="show">
        <br>
        <input type="submit" value="Send">
    </form>
</body>
</html>