<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<h1>${ sessionScope.teamName }チーム結果</h1>
<body>
あなたのチームは以下の成績です。
<br/>
<br/>
トータル経過秒数：${ sessionScope.elapsedTime }秒
<br/>
<br/>
トータル回答数：${ sessionScope.totalAnswerCnt }回
</body>
</html>