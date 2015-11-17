<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HabPgCon</title>
</head>
<h1>HitAndBlow Progamme Contest</h1>
<body>
	<form action="../StartViewController" method="post" >
		チーム名を入力してください： <input type="text" name="teamName"/>
		<br />
		<br />
		桁数を選択してください：<input type="text" name="numberOfDigit" size="5" />桁
		<br />
		<br />
		<input type="submit" name="startButton" value="スタート"/>
	</form>
</body>
</html>