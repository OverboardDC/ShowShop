<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <section class="cart">
            <div class="products_head">
                <div class="headline">
                    <p>Home/Cart</p>
                    <h2>Your cart</h2>
                </div>
                <div class="separator"></div>
            </div>
            <c:if test="${cart.getCart().isEmpty() == false}">

                <div class="cart_form">
                    <div class="cart_table">
                        <table>
                            <thead>
                            <tr>
                                <th></th>
                                <th>Product</th>
                                <th>Each</th>
                                <th>Quantity</th>
                                <th>Total</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${cart.getCart()}" var="cartProduct">

                                <tr>
                                    <td class="cart_product_remove">
                                        <a href="<c:url value="/removeFromCart/${cartProduct.product.id}"/>"
                                           class="cart_product_remove_link"></a>
                                    </td>
                                    <td class="cart_product_name">
                                        <div class="cart_product_img">
                                            <a href="<c:url value="/product/${cartProduct.product.id}"/>"><img
                                                    src="<c:url value="/image/showMainImage?id=${cartProduct.product.id}"/>"></a>
                                        </div>
                                        <div class="cart_product_descr">
                                            <a href="<c:url value="/product/${cartProduct.product.id}"/>">
                                                <h3>${cartProduct.product.brand.name} ${cartProduct.product.name}</h3>
                                            </a>
                                        </div>
                                    </td>
                                    <td class="cart_product_price">
                                        <p class="price_holder">${cartProduct.product.price} $</p>
                                    </td>
                                    <c:url value="/updateQuantity/${cartProduct.product.id}" var="updateQuantity">

                                    </c:url>
                                    <form action="${updateQuantity}" method="post">
                                        <td class="cart_product_quantity">
                                            <div class="cart_product_quantity_div">
                                                <input name="quantity" value="${cartProduct.quantity}" title="quantity" type="number" min="1"
                                                       step="1" size="4" onchange="this.form.submit()"/>
                                            </div>
                                        </td>
                                    </form>
                                    <td class="cart_product_total_price">
                                        <p class="price_holder">${cartProduct.fullPrice} $</p>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="cart_bottom">
                        <h3>Total price</h3>
                        <p>${cart.getTotalPrice() } $</p>
                        <div class="separator"></div>
                            <a href="<c:url value="/checkout"/>" class="button_1">Procced with the order</a>
                    </div>
                </div>
            </c:if>
            <c:if test="${cart.getCart().isEmpty() != false}">
                <div class="cart_is_empty"><h1>The cart is empty</h1></div>
            </c:if>
        </section>
    </div>

</div><!-- .wrapper -->

<%@include file="../templates/footer.jsp" %>

</body>
</html>