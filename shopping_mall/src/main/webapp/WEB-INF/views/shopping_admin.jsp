<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./top.jsp"%>
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
<main class="maincss">
<cr:if test="${list.get(3)==2}">
<section>
    <p>신규등록 관리자</p>
    <ol class="new_admin_title2">
        <li>NO</li>
        <li>관리자명</li>
        <li>아이디</li>
        <li>전화번호</li>
        <li>이메일</li>
        <li>담당부서</li>
        <li>담당직책</li>
        <li>가입일자</li>
        <li>승인여부</li>
    </ol>
    <cr:if test="${lists==''}">
    <ol class="new_admin_none">
        <li>신규 등록된 관리자가 없습니다.</li>
    </ol>
    </cr:if>
    <cr:forEach var="li" items="${lists }" varStatus="stat">
    <ol class="new_admin_lists2">
        <li>${stat.index+1 }</li>
        <li>${li.sname}</li>
        <li>${li.sid}</li>
        <li>${li.shp.substring(0,3)}-${li.shp.substring(3,7)}-${li.shp.substring(7,11)}</li>
        <li>${li.semail }</li>
        <li>${li.spart.split("!")[0] }</li>
        <li>${li.spart.split("!")[1] }</li>
        <li>${li.sdate.substring(0,10)}</li>
        <li>
        <form id="approval_frm">
        <input type="hidden" value="${li.sidx}" name="sidx">
        <input type="hidden" value="${li.master}" name="master" id="approval_master">
        <cr:if test="${li.master==1 }">
            <input type="button" value="승인" class="new_addbtn1" title="승인" onclick="approval()" >
            </cr:if>
            <cr:if test="${li.master==0 }">
            <input type="button" value="미승인" class="new_addbtn2" title="미승인" onclick="approval()">
            </cr:if>
            </form>
        </li>
    </ol>
    </cr:forEach>
</section>
</cr:if>
<section></section>
<section></section>
</main>
<footer class="main_copyright">
    <div>
        Copyright ⓒ 2024 shopbag All rights reserved.
    </div>
</footer>
</body>
<script src="./js/shopping_admin.js"></script>
</html>