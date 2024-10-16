<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="./top.jsp" %>
    <%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 등록 페이지</title>
    <link rel="stylesheet" type="text/css" href="./css/basic.css">
    <link rel="stylesheet" type="text/css" href="./css/login.css?v=1">
    <link rel="stylesheet" type="text/css" href="./css/main.css">
    <link rel="stylesheet" type="text/css" href="./css/product.css?v=5">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
</head>
<body>
<main class="maincss">
<section>
<p>상품관리 페이지</p>
<div class="subpage_view">
  <span>등록된 상품 
    <cr:if test="${not empty product_list}">
        ${product_list.size()}건
    </cr:if>
    <cr:if test="${empty product_list}">
        0건
    </cr:if>
</span>
    <span>
        <form id="searchProduct">
        <select class="p_select1" name="type">
            <option value="name">상품명</option>
            <option value="code">상품코드</option>
        </select>
        <input type="text" class="p_input1" placeholder="검색어를 입력해 주세요" name="keyword">
        <input type="submit" value="검색" title="상품검색" class="p_submit">
        </form>
    </span>
</div>
    <form id="delete_frm">
<div class="subpage_view2">
    <ul>
        <li><input type="checkbox"></li>
        <li>코드</li>
        <li>이미지</li>
        <li>상품명</li>
        <li>카테고리 분류</li>
        <li>판매가격</li>
        <li>할인가격</li>
        <li>할인율</li>
        <li>재고현황</li>
        <li>판매유/무</li>
        <li>품절</li>
        <li>관리</li>
    </ul>
    <cr:if test="${not empty product_list}">
    <cr:forEach var="list_product" items="${product_list }">
    <ul>
        <li><input type="checkbox" value="${list_product.pidx}" name="delete_pidx" class="check_radio"></li>
        <li>${list_product.pcode }</li>
        <li><img  src="./upload/${list_product.thumbnail.split("-")[1] }" width="30" height="30"></li>
        <li>${list_product.pname }</li>
        <li>${list_product.cate_name }</li>
        <li>${list_product.price }</li>
        <li>${list_product.dis_price }</li>
        <li>${list_product.discount }%</li>
        <li>${list_product.pea }</li>
        <li>${list_product.psales }</li>
        <li>${list_product.early_sold_out }</li>
        <li>관리</li>
    </ul>
    </cr:forEach>
    </cr:if>
   <cr:if test="${empty product_list}">
    <ul>
        <li style="width: 100%;">등록된 상품이 없습니다.</li>
    </ul>
    </cr:if>
</div>
<div class="subpage_view3">
    <ul class="pageing">
        <li><img src="./ico/double_left.svg"></li>
        <li><img src="./ico/left.svg"></li>
        <li>1</li>
        <li><img src="./ico/right.svg"></li>
        <li><img src="./ico/double_right.svg"></li>
    </ul>
</div>
    </form>
<div class="subpage_view4">
    <input type="button" value="선택상품 삭제" title="선택상품 삭제" class="p_button" id="delete_pro">
    <span style="float: right;">
    <input type="button" id="product_make" value="신규상품 등록" title="신규상품 등록" class="p_button p_button_color1">
    <input type="button" id="cate_list_go" value="카테고리 리스트" title="카테고리 리스트" class="p_button p_button_color2">
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
<script src="./js/product_list.js?v=3"></script>
</html>