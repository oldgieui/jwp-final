<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ include file="/include/tags.jspf"%>
<!DOCTYPE html>
<html>
	<c:forEach items="${jsonDatas}" var="each">
		${each}<br>
	</c:forEach>
</html>