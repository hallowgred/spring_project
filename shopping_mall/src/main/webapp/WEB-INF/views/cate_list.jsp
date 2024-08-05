<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="./top.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>상품등록 페이지</title>
    <link rel="stylesheet" type="text/css" href="./css/basic.css">
    <link rel="stylesheet" type="text/css" href="./css/login.css?v=1">
    <link rel="stylesheet" type="text/css" href="./css/main.css">
    <link rel="stylesheet" type="text/css" href="./css/category.css?v=6">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
</head>
<body>
<main class="maincss">
    <section>    
<p>카테고리관리 페이지</p>
<div class="subpage_view">
    <span>등록된 카테고리 0건</span>
    <span>
        <select class="p_select1">
            <option>카테고리명</option>
	        <cr:forEach var="list_cate" items="${cate_list}">
            <option>${list_cate.lname}</option>
            </cr:forEach>
        </select>
        <input type="text" class="p_input1" placeholder="검색어를 입력해 주세요">
        <input type="submit" value="검색" title="카테고리 검색" class="p_submit">
        </form>
    </span>
</div>
    <form id="delete_cate_frm">
<div class="subpage_view2">
    <ul>
        <li><input type="checkbox"></li>
        <li>분류코드</li>
        <li>대메뉴 코드</li>
        <li>대메뉴명</li>
        <li>소메뉴 코드(사용안함)</li>
        <li>소메뉴명(사용안함)</li>
        <li>사용 유/무</li>
        <li>관리</li>
    </ul>
    <cr:forEach var="category_list" items="${cate_list }">
    <ul>
        <li><input type="checkbox" value="${catagory_list.cidx }" class="delete_categorys"></li>
        <li style="text-align: left; text-indent: 5px;">${category_list.classcode}</li>
        <li>${category_list.lcode}</li>
        <li style="text-align: left; text-indent: 5px;">${category_list.lname}</li>
        <li>-</li>
        <li style="text-align: left; text-indent: 5px;">-</li>
        <li>${category_list.cuse}</li>
        <li>[수정]</li>
    </ul>
    </cr:forEach>
    <cr:if test="${empty cate_list}">
    <ul>
        <li style="width: 100%;">등록된 카테고리가 없습니다.</li>
    </ul>
    </cr:if>
</div></form>
<div class="subpage_view3">
    <ul class="pageing" style="text-align: center;">
        <li><img src="./ico/double_left.svg"></li>
        <li><img src="./ico/left.svg"></li>
        <li>1</li>
        <li><img src="./ico/right.svg"></li>
        <li><img src="./ico/double_right.svg"></li>
    </ul>
</div>
<div class="subpage_view4">
    <input type="button" value="카테고리 삭제" title="카테고리 삭제" class="p_button" id="delete_cate">
    <span style="float: right;">
    <input type="button" value="신규상품 등록" title="신규상품 등록" class="p_button p_button_color1" id="pro_list">
    <input type="button" value="카테고리 등록" title="카테고리 등록" class="p_button p_button_color2" id="cate_list">
    </span>
</div>
</section>
</main>
<footer class="main_copyright">
    <div>
        Copyright ⓒ 2024 shopbag All rights reserved.
    </div>
</footer>
</body>
<script src="./js/cate_list.js?v=2"></script>
</html>