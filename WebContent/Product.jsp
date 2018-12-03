<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.lang.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品紹介</title>
</head>
<body>

<center>
<h1>商品紹介</h1>
 <table border="1" cellspacing="0" bordercolor="#000000">
    <tr>
     <td width="150">商品名</td>
     <td width="300"><%= session.getAttribute("name") %></td>
    </tr>

    <tr>
     <td>カテゴリ</td>
     <td><%= session.getAttribute("category") %></td>
    </tr>

    <tr>
     <td>価格</td>
     <td><%= session.getAttribute("price") %></td>
    </tr>

    <tr>
     <td>在庫</td>
     <td><%= session.getAttribute("stock") %></td>
    </tr>

    <tr>
     <td>商品紹介</td>
     <td><%= session.getAttribute("msg") %></td>
    </tr>
</table>

<table>
<tr>
<td align="left">個数
<form action="order" name="no" method="POST">
<select name="no">
<% int a=((Integer) session.getAttribute("stock")).intValue();
for(int i=1;i<=a;i++) { %>
<option value="<%= i %>"><%= i %></option>
<% } %>
</select>
<input type="submit" value="カートへ">
</form>

<button type="button" onclick="history.back()">戻る</button>

</td>
</tr>
</table>
</center>

</body>
</html>