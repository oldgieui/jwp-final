<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ include file="/include/tags.jspf"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/header.jspf"%>
</head>

<body>
    <div id="header">
        <div id="title">
            <h2><a href="/list.next">Java Web Programming 실습</a></h2>
        </div>
    </div>
     
    <div id="main">
	<c:forEach items="${jsonDatas}" var="each">
		${each}<br>
	</c:forEach>

    </div>
</body>
</html>