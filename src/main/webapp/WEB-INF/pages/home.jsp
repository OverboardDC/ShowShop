<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <!--[if lt IE 9]>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script><![endif]-->
    <title>SnowboardShop</title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">
</head>

<body>

<div class="wrapper">

    <%@include file="../templates/header.jsp" %>

    <div class="content">
        <section class="showcase">
            <h1>Welcome to the Snow shop!</h1>
            <p>Mus elit Morbi mus enim lacus at quis Nam eget morbi. Et semper urna urna non at cursus dolor vestibulum
                neque enim.</p>
            <a href="<c:url value="/products/6"/>" class="button_1">Into the shop</a>
        </section>
    </div><!-- .showcase -->

    <div class="content">
        <section class="brands">
            <ul>
                <c:forEach items="${brands}" var="brand">
                    <li>
                        <a href="<c:url value="/brand/products/${brand.id}"/>">
                            <img alt="${brand.name}" src="<c:url value="/image/showBrandLogo?id=${brand.id}"/>">
                        </a></li>
                </c:forEach>
            </ul>
        </section>
    </div>

    <div class="content">
        <section class="new_arrivals">
            <h2>Our new products</h2>
            <div class="separator"></div>
            <div class="items_list">
                <c:forEach items="${latestProducts}" var="latestProduct">
                    <div class="item_1">
                        <a href="<c:url value="/product/${latestProduct.id}"/>">
                            <img src="<c:url value="/image/showMainImage?id=${latestProduct.id}"/>" alt="${latestProduct.name}">
                            <h5>${latestProduct.brand.name} ${latestProduct.name}</h5>
                            <p class="price_holder">${latestProduct.price} $</p>
                        </a>
                    </div>
                </c:forEach>
            </div>
            <div class="clearfix"></div>
        </section>
    </div><!-- .new products -->


    <div class="content">
        <section class="bestsellers">

            <h2>Best Sellers</h2>
            <p>Mus elit Morbi mus enim lacus at quis</p>
            <div class="separator"></div>
            <div class="items_list">
                <c:forEach items="${bestsellers}" var="bestseller">
                    <div class="item_1">
                        <a href="<c:url value="/product/${bestseller.id}"/>">
                            <img src="<c:url value="/image/showMainImage?id=${bestseller.id}"/>" alt="${bestseller.name}">
                            <h5>${bestseller.brand.name} ${bestseller.name}</h5>
                            <p class="price_holder">${bestseller.price} $</p>
                        </a>
                    </div>
                </c:forEach>
            </div>
            <div class="clearfix"></div>
        </section>
    </div><!-- .best sellers -->


</div><!-- .wrapper -->

<%@include file="../templates/footer.jsp" %>

</body>
</html>