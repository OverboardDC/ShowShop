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
        <section class="product">

            <div class="products_head">
                <div class="headline">
                    <p>Home/${product.category.name}</p>
                    <h2>${product.brand.name} ${product.name}</h2>
                </div>
                <div class="separator"></div>
            </div>
            <div class="product_main">
                <div class="product_left">
                    <div class="product_top">
                        <div class="product_main_image">
                            <img src="<c:url value="/image/showMainImage?id=${product.id}"/>">
                        </div>

                        <div class="product_description">
                            <p>Product code: ${product.id}</p>
                            <h2>Overview</h2>
                            <p class="price_holder">${product.price}$</p>
                            <h3>Size</h3>
                            <select class="product_select" title="size">
                                <option>155</option>
                                <option>153</option>
                            </select>
                            <c:if test="${!cart.isInCart(product)}">
                                <a class="button_1" href="<c:url value="/addToCart/${product.id}"/>">Add to cart</a>
                            </c:if>
                            <c:if test="${cart.isInCart(product)}">
                                <a href="<c:url value="/cart"/>">
                                    <div class="product_in_the_cart">The product is in the cart</div>
                                </a>
                            </c:if>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <div class="product_bottom">
                        <div class="product_info_links">
                            <ul>
                                <li><a href="#" class="product_info_option_chosen">Description</a></li>
                                <li><a href="#">Details</a></li>
                                <li><a href="#">Reviews</a></li>
                            </ul>
                        </div>
                        <div class="product_info">
                            <h2>Description of the product</h2>
                            <p>As am hastily invited settled at limited civilly fortune me. Really spring in extent an
                                by.
                                Judge
                                but built gay party world. Of so am he remember although required. Bachelor unpacked be
                                advanced
                                at. Confined in declared marianne is vicinity.

                                Attended no do thoughts me on dissuade scarcely. Own are pretty spring suffer old denote
                                his. By
                                proposal speedily mr striking am. But attention sex questions applauded how happiness.
                                To
                                travelling occasional at oh sympathize prosperous. His merit end means widow songs linen
                                known.
                                Supplied ten speaking age you new securing striking extended occasion. Sang put paid
                                away
                                joy
                                into six her.
                            </p>
                        </div>
                    </div>
                </div>
                <div class="product_right">
                    <div class="product_brand">
                        <h2>Brand</h2>
                        <a href="<c:url value="/brand/products/${product.brand.id}"/>"><img
                                src="<c:url value="/image/showBrandLogo?id=${product.brand.id}"/>"></a>
                    </div>
                    <div class="delivery_and_payment">
                        <div class="delivery_and_payment_item">
                            <h2>Delivery</h2>
                            <p>As am hastily invited settled at limited civilly fortune me. Really spring in extent</p>
                        </div>
                        <div class="delivery_and_payment_item">
                            <h2>Payment</h2>
                            <p>As am hastily invited settled at limited civilly fortune me. Really spring in extent</p>
                        </div>
                        <div class="delivery_and_payment_item">
                            <h2>Guarantee</h2>
                            <p>As am hastily invited settled at limited civilly fortune me. Really spring in extent</p>
                        </div>
                        <div class="delivery_and_payment_item">
                            <h2>Return</h2>
                            <p>As am hastily invited settled at limited civilly fortune me. Really spring in extent</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <div class="clearfix"></div>
    </div>

</div><!-- .wrapper -->

<%@include file="../templates/footer.jsp" %>

</body>
</html>