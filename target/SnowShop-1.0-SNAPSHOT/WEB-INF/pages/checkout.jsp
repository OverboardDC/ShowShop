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
        <section class="checkout">
            <div class="products_head">
                <div class="headline">
                    <p>Home/Cart/Checkout</p>
                    <h2>Checkout</h2>
                </div>
                <div class="separator"></div>
            </div>

            <div class="checkout_main">
                <c:url value="/formOrder" var="formOrder"/>
                <form:form modelAttribute="order" action="formOrder">
                    <div class="checkout_left">
                        <h3>Enter your data</h3>
                        <p class="checkout_input_data_row ">
                            <label>First name*</label>
                            <form:input path="firstName" type="text"/>
                            <form:errors path="firstName" cssStyle="color: red"/>
                        </p>
                        <p class="checkout_input_data_row">
                            <label>Last name*</label>
                            <form:input path="lastName" type="text"/>
                            <form:errors path="lastName" cssStyle="color: red"/>
                        </p>
                        <div class="clearfix"></div>
                        <p class="checkout_input_data">
                            <label>City*</label>
                            <form:input path="city" type="text"/>
                            <form:errors path="city" cssStyle="color: red"/>
                        </p>
                        <p class="checkout_input_data">
                            <label>Address*</label>
                            <form:input path="address" type="text"/>
                            <form:errors path="address" cssStyle="color: red"/>
                        </p>
                        <p class="checkout_input_data">
                            <label>Email</label>
                            <form:input path="email" type="text"/>
                            <form:errors path="email" cssStyle="color: red"/>
                        </p>
                        <p class="checkout_input_data">
                            <label>Contact phone number*</label>
                            <form:input path="phone" type="text"/>
                            <form:errors path="phone" cssStyle="color: red"/>
                        </p>
                        <div class="clearfix"></div>
                        <p class="checkout_input_data">
                            <label>Comment to the order</label>
                            <form:textarea path="comment" placeholder="Additional wishes"/>
                            <form:errors path="comment" cssStyle="color: red"/>
                        </p>
                        <div class="clearfix"></div>
                    </div>
                    <div class="checkout_right">
                        <div class="checkout_preview">
                            <h3>Your order</h3>
                            <form:errors path="orderedProducts" cssStyle="color: red"/>
                            <table>
                                <thead>
                                <tr>
                                    <th>Product</th>
                                    <th>Total</th>
                                </tr>

                                </thead>

                                <tbody>
                                <c:forEach items="${cart.getCart()}" var="cartProduct">
                                <tr>
                                    <td><a href="<c:url value="/product/${cartProduct.product.id}"/>">
                                            ${cartProduct.product.brand.name} ${cartProduct.product.name}</a>
                                        <strong>${cartProduct.quantity}x</strong></td>
                                    <td>${cartProduct.fullPrice} $</td>

                                </tr>
                                </c:forEach>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <th>Total price</th>
                                    <td class="total_column"><strong>${cart.getTotalPrice()} $</strong></td>

                                </tr>
                                <tr>
                                    <th>Shipping*</th>
                                    <td class="total_column">
                                        <ul>
                                            <li>
                                                <input name="shipping" type="radio" checked/>
                                                <label>By post</label>
                                            </li>
                                            <li>
                                                <input name="shipping" type="radio"/>
                                                <label>Pickup</label>
                                            </li>
                                        </ul>
                                    </td>
                                </tr>
                                </tfoot>
                            </table>
                            <button type="submit"class="button_1">Confirm</button>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                </form:form>
            </div>
        </section>
    </div>
</div><!-- .wrapper -->

<%@include file="../templates/footer.jsp" %>

</body>
</html>