<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<div align="center">
<h1>検索</h1>
</div>

</br>

<div align="center">
<input type="text" size="50" name="word">
</div>
</br>


<div align="center">
<tr>
<td>カテゴリ</tr>
<td><select name="category">
	<option value="sample1">サンプル1</option>
</select></td>
</tr>
</div>
</br>

<div align="center">
<input type="submit" value="検索">
</div>
</br>


<form action="search" method="POST">

<div align="center">
<table border=1 width="450">
<tr>
<th>商品名</th>
<th>価格</th>
<th>詳細</th>
</tr>

<tr>
<td>炊飯器</td>
<td>￥14,800</td>
<td>
<form method="post" action="search">
<input type="hidden" name="order" value="炊飯器">
<input type="submit" value="詳細">
</form>

</tr>

<tr>
<td>GOD</td>
<td>￥10</td>
<td><form method="post" action="search">
<input type="hidden" name="order" value="GOD">
<input type="submit" value="詳細">
</tr>

<tr>
<td>パソコン</td>
<td>￥99,800</td>
<td><form method="post" action="search">
<input type="hidden" name="order" value="パソコン">
<input type="submit" value="詳細">
</tr>
</table>
</div>

</form>

</body>
</html>
</body>
</html>