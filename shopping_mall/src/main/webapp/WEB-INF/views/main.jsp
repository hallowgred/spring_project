<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>Shop Bag</title>
    <meta charset="utf-8" />
    <link href="./css/index.css" rel="stylesheet" />
    <link href="./css/menu.css?v=1" rel="stylesheet"/>
    <style>
        /* 상품 리스트 스타일 */
        .product-list {
            display: flex; /* 플렉스 박스를 사용하여 상품 정렬 */
            flex-wrap: wrap; /* 줄바꿈 허용 */
            justify-content: space-between; /* 공간을 고르게 배분 */
        }

        .product {
            width: calc(33.333% - 20px); /* 각 상품의 너비 설정 (3개씩) */
            margin-bottom: 20px; /* 하단 여백 설정 */
            box-sizing: border-box; /* 패딩과 테두리를 포함하여 계산 */
        }

        .product img {
            width: 100%; /* 이미지 너비 100%로 설정 */
            height: 200px; /* 고정 높이 설정 */
            object-fit: cover; /* 이미지 비율 유지하면서 크기 맞추기 */
        }

        .clearfix {
            clear: both;
        }
    </style>
</head>

<body>
<header>
    <div class="navbar">
        <a href="#" id="logo">
            <img src="./images/logo.jpg" width="149">
        </a>
        <ul id="menu">
            <li><a href="#">LOGIN</a></li>
            <li><a href="#">MEMBER SHIP</a></li>
            <li><a href="#">CART</a></li>
            <li><a href="#">CUSTOMER CENTER</a></li>
        </ul>
    </div>
</header>
<nav>
    <div class="menu-list">
        <ul>
            <cr:forEach var="category" items="${getCategory}">
                <li>${category}</li>
            </cr:forEach>
        </ul>
    </div>
</nav>
<main>
    <img src="./images/hero_header.jpg" style="width: 100%; height: auto;"> <!-- 반응형 이미지 -->
    <div class="products">
        <h3>NEW PRODUCTS</h3>
        <div class="product-list">
            <cr:forEach var="product" items="${getProduct}">
                <a href="#" class="product">
                    <img src="./upload/${product.thumbnail.split("-")[1]}">
                    <div class="product-name">${product.pname}</div>
                    <div class="product-price">${product.price}</div>
                </a>
            </cr:forEach>
        </div>
    </div>
</main>
<footer>
    <div class="footer">
        <a href="http://facebook.com">
            <img src="images/facebook.png" height="20">
        </a>
        <a href="http://instagram.com">
            <img src="images/instagram.png" height="20">
        </a>
        <a href="http://twitter.com">
            <img src="images/twitter.png" height="20">
        </a>
    </div>
    <section class="foot_section"></section>
    <aside class="aside_footer">
        <div class="div_footer">
            <ul>
                <li><img src="./images/foot_logo.png"></li>
                <li>
                    회사명 :  ${getFooter.corname}&nbsp;&nbsp;&nbsp;
                    대표자 : ${getFooter.repname}&nbsp;&nbsp;&nbsp;
                    주소 : ${getFooter.workplace}<br>
                    고객센터 : 032-1234-5678&nbsp;&nbsp;&nbsp;
                    상담시간 : 09:00~16:00&nbsp;&nbsp;&nbsp;
                    E-Mail : ${getFooter.hemail}<br>
                    사업자등록번호 : ${getFooter.business_reg}&nbsp;&nbsp;&nbsp;
                    통신판매업신고번호 : ${getFooter.business_mail_order}&nbsp;&nbsp;&nbsp;
                    개인정보보호책임자 : ${getFooter.info_mng}<br>
                    Copyright © 도메인명 All Rights Reserved.
                </li>
            </ul>
        </div>
    </aside>
</footer>
</body>
</html>