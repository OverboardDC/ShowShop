<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 09.12.2017
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<div class="content">
    <header>
        <div class="header_top">
            <a href="<c:url value="/" />"><img src="<c:url value="/resources/images/logo.png"/>" alt="Snow shop" class="logo"></a>
            <c:url value="/search/products/" var="search">
                <c:param name="query" value="${query}"/>
            </c:url>
            <form action="${search}" method="get">
                <input value="${lastQuery}" name="query" type="text" placeholder="Search..." class="base_input input_search">
                <button class="base_button button_search" type="submit">Search</button>
            </form>
            <div class="header_top_right">
                <a href="#" class="account_link">
                    <img src="<c:url value="/resources/images/user_icon.png"/>" class="user_icon" alt="Your account">
                </a>
                <p class="phone_number">
                    <img src="<c:url value="/resources/images/phone_icon.png"/>" class="base_icon">111-231-23-43
                </p>
            </div>
        </div>
        <div class="clearfix"></div>
        <div class="header_bottom">
            <nav class="main_nav">
                <ul>
                    <c:forEach var="section" items="${sections}">
                        <li><a href="#" class="nav_item">${section.name}</a>
                            <div class="nav_content">
                                <div class="sub_list">
                                    <ul>
                                        <c:forEach var="category" items="${section.categories}">
                                            <li><a href="<c:url value="/products/${category.id}"/>">${category.name}</a></li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </nav>
            <a href="<c:url value="/cart"/>" class="cart_link"><img src="<c:url value="/resources/images/shopping_cart_icon.png"/>"></a>
        </div>
        <div class="clearfix"></div>
    </header>
</div><!-- .header -->
</body>
</html>
