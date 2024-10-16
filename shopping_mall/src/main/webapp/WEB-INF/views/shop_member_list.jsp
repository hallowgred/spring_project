<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@include file="./top.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>쇼핑몰 회원관리</title>
    <link rel="stylesheet" type="text/css" href="./css/basic.css">
    <link rel="stylesheet" type="text/css" href="./css/login.css?v=1">
    <link rel="stylesheet" type="text/css" href="./css/main.css">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
</head>
<body>
<form id="member_list_frm">
<main class="maincss">
<section>
    <p>회원 리스트</p>
    <ol class="new_admin_title">
        <li>NO</li>
        <li>고객명</li>
        <li>아이디</li>
        <li>전화번호</li>
        <li>이메일</li>
        <li>이메일 수신</li>
        <li>SMS 수신</li>
        <li>가입일자</li>
        <li>상태</li>
        <li>정지여부</li>
    </ol>
    <cr:if test="${empty member_list}">
    <ol class="new_admin_none">
        <li>가입된 회원이 없습니다.</li>
    </ol>
    </cr:if>
    <cr:forEach var="list_member" items="${member_list}" varStatus="member_stat">
    <ol class="new_admin_lists">
        <li>${fn:length(member_list) - member_stat.index}</li>
        <li>${list_member.mname}</li>
        <li>${list_member.mid}</li>
        <li>${list_member.mtel}</li>
        <li>${list_member.memail}</li>
        <li>${list_member.emailuse}</li>
        <li>${list_member.smsuse}</li>
        <li>${list_member.mdate.substring(0,10)}</li>
        <li>${list_member.mstat}</li>
        <li>
        	<input type="hidden" name="midx" value="${list_member.midx}">
        	<input type="hidden" name="mstat1" value="" id="mstat">
            <input type="button" value="정지"  class="new_addbtn1" title="정지" onclick="stat_change(this.value, '${list_member.mname}', '${list_member.midx}')">
            <input type="button" value="해제"  class="new_addbtn2" title="해제" onclick="stat_change(this.value, '${list_member.mname}', '${list_member.midx}')">
        </li>
    </ol>
    </cr:forEach>
</section>
<section style="width: 1100px; height: auto; margin: 0 auto; margin-top: 30px;">
    <p style="font-size: 15px;font-weight: bolder; margin-bottom: 10px;">■ 이용 약관</p>
    <textarea name="terms" placeholder="이용약관에 대한 내용을 입력하세요" style="width: 100%; height: 100px; resize: none;"></textarea>
    <input type="button" onclick="update_terms(1)"  value="이용약관 수정" title="이용약관 수정" class="btn_button" style="position: relative; left: 100%; margin-left: -100px;">
</section>
<input type="hidden" name="tidx" value="">
<section style="width: 1100px; height: auto; margin: 0 auto; margin-top: 30px;">
    <p style="font-size: 15px;font-weight: bolder; margin-bottom: 10px;">■ 개인정보 수집 및 이용</p>
    <textarea placeholder="개인정보 수집 및 이용" style="width: 100%; height: 100px; resize: none;" name="personal_information"></textarea>
    <input type="button" onclick="update_terms(0)" value="개인정보 약관 수정" title="개인정보 약관 수정" class="btn_button" style="position: relative; left: 100%; margin-left: -100px;">
</section>
<input type="hidden" name="hidden_no" value="">
</main>
</form>
<footer class="main_copyright">
    <div>
        Copyright ⓒ 2024 shopbag All rights reserved.
    </div>
</footer>
</body>
<%Date d = new Date(); %>
<script src="./js/shop_member_list.js?v=<%=d%>"></script>
</html>
