<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>
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
       	<p>${list.get(2)} 관리자 <a href="#">[개인정보 수정]</a> <a id="logout">[로그아웃]</a></p> 
    </div>
</header>
<nav class="navcss">
    <div class="nav_div">
        <ol>
        <cr:if test="${list.get(3)==2}">
            <li title="쇼핑몰 상품관리">쇼핑몰 관리자 리스트</li>
            </cr:if>
            <li title="쇼핑몰 회원관리">쇼핑몰 회원관리</li>
            <li title="쇼핑몰 상품관리">쇼핑몰 상품관리</li>
            <li title="쇼핑몰 기본설정">쇼핑몰 기본설정</li>
        </ol>
    </div>
</nav>
</body>
<script src="./js/top.js?v=1"></script>
</html>