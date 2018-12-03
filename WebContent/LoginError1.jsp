<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>

<form action="login" method="POST">

	<h1>ログイン画面</h1>

	<table>
		<tr>
		<td>名前</td>
		<td><input type="text" name="user" size="20" maxlength="20"></td>
		</tr>

		<tr>
		<td>パスワード</td>
		<td><input type="text" name="pass" size="20" maxlength="20"></td>
		</tr>
	</table>

<%= request.getAttribute("err1")  %>


<br>
<input type="submit" value="LOGIN">
</form>

</center>
</body>
</html>