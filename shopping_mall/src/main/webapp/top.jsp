<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    HttpSession hs = request.getSession();
     ArrayList<Object> ar =(ArrayList<Object>)hs.getAttribute("list");
     int count =0;
     if (ar == null || ar.isEmpty()) {
         // 리스트가 null이거나 비어있을 때의 처리
         %>
         <script>
         	<%count++;%>
             alert("잘못된 접근방식입니다!");
             location.href = "./index.jsp";
         </script>
         <%
     } else if (ar.get(0) == null || ar.get(0).equals(null)) {
         // 리스트가 비어있지 않지만 첫 번째 요소가 null일 때의 처리
         %>
         <script>
         <%count++;%>
             alert("잘못된 접근방식입니다!");
             location.href = "./index.jsp";
         </script>
         <%
     }
 %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 등록 페이지</title>
    <link rel="stylesheet" type="text/css" href="./css/basic.css">
    <link rel="stylesheet" type="text/css" href="./css/login.css?v=1">
    <link rel="stylesheet" type="text/css" href="./css/main.css?v=1">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
</head>
<body>
<header class="headercss">
    <div class="header_div">
        <p class="top_to_home"><img src="./img/logo.png" class="logo_sm"> ADMINISTRATOR</p>
        <%
        if(count==0){%>
        	<p><%=ar.get(2) %>관리자 <a href="#">[개인정보 수정]</a> <a id="logout">[로그아웃]</a></p>
       <% } %> 
    </div>
</header>
</body>
<script src="./js/top.js?v=1"></script>
</html>