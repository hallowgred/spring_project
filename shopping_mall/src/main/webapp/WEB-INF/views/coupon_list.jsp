<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jstl 페이징 사용법</title>
</head>
<body>
<p>쿠폰리스트</p>
<table border="1" selpadding="0" >
<thead>
	<tr>
		<th>번호</th>
		<th>쿠폰명</th>
		<th>할인율</th>
		<th>사용 유/무</th>
		<th>만료기한</th>
	</tr>
</thead>
<tbody>
<cr:set var="ino" value="${total -startpg}"/>
<cr:forEach var="data" items="${all }" varStatus="idx">
	<tr>
		<td>${ino-idx.index}</td>
		<td>${data.get(1)}</td>
		<td>${data.get(2)}</td>
		<td>${data.get(3)}</td>
		<td>${data.get(4)}</td>
	</tr>
	</cr:forEach>
</tbody>
</table>

<table>
<tr>
<cr:set var="pg" value="${total/2+(1-((total/2)%1))%1 }"/>
<cr:forEach var="no" begin="1" end="${pg}" step="1">
<td><a href="./coupon_list.do?page=${no}">${no}</a></td>
</cr:forEach>
</tr>
</table>
</body>
</html>