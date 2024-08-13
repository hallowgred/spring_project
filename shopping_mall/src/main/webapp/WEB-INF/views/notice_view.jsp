<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="./top.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 내용 확인 페이지</title>
    <link rel="stylesheet" type="text/css" href="./css/basic.css">
    <link rel="stylesheet" type="text/css" href="./css/login.css?v=10">
    <link rel="stylesheet" type="text/css" href="./css/main.css?v=10">
    <link rel="stylesheet" type="text/css" href="./css/notice.css?v=10">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
</head>
<body>
<form id="notice_one_frm">
<main class="maincss">
<section>
    <p>공지사항 확인 페이지</p>
<div class="write_view">
<input type="hidden" value="${notice_one_list.get(0)}" name="idx">
<input type="hidden" value="${notice_one_list.get(0)}" name="nidx">
<ul>
    <li>공지사항 제목</li>
    <li><input type="text" value="${notice_one_list.get(1)}"  name="ntitle" style="border:none;" readonly="readonly"> 
    </li>
</ul>
<ul>
    <li>글쓴이</li>
    <li>
<input type="text" value="${notice_one_list.get(2)}" readonly="readonly" name="nwriter" style="border:none;">
    </li>
</ul>
<ul>
    <li>첨부파일</li>
    <li>
    <input type="text" value="${notice_one_list.get(3).split("-")[0]}" readonly="readonly" name="files" style="border:none;">
    </li>
</ul>
<ul class="ul_height">
    <li>공지내용</li>
    <li>
        <div class="notice_input3" style="overflow-y: auto;">${notice_one_list.get(4)}</div>
    </li>
</ul>
</div>
<div class="board_btn">
    <button class="border_del" id="notice_list">공지목록</button>
    <button class="border_add" id="notice_modify">공지수정</button>
    <button class="border_modify" style="margin-left: 8px;" id="notice_del">공지삭제</button>
</div>
</section>
</main>
</form>
<footer class="main_copyright">
    <div>
        Copyright ⓒ 2024 shopbag All rights reserved.
    </div>
</footer>
</body>
<% Date d=new Date(); %>
<script src="./js/notice_view.js?v=<%=d%>"></script>
</html>