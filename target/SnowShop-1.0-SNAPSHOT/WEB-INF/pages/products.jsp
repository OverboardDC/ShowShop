<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <section class="products">
            <div class="products_head">
                <div class="headline">
                    <p>Home/${currentCategory.name}<c:if test="${currentCategory==null && lastQuery!=null}">Search</c:if></p>
                    <h2>${currentCategory.name}<c:if test="${currentCategory==null && lastQuery!=null}">Result by '${lastQuery}'</c:if></h2>
                </div>
                <div class="separator"></div>
                <p class="res_qty">${products.size()} Results</p>
                <ul class="sorting_type">
                    <c:forEach items="${sortingTypes}" var="sortingType">
                        <c:url value="" var="sort">
                            <c:param name="sort" value="${sortingType.key}"/>
                            <c:if test="${lastBrand!=null}">
                                <c:param name="brand" value="${lastBrand}"/>
                            </c:if>
                            <c:if test="${lastQuery!=null}">
                                <c:param name="query" value="${lastQuery}"/>
                            </c:if>
                            <c:param name="page" value="${lastChosenPage}"/>
                        </c:url>


                        <c:if test="${sortingType.value.chosen}">
                            <li><a class="sorting_type_chosen" href="${sort}">${sortingType.value.name}</a></li>
                        </c:if>
                        <c:if test="${!sortingType.value.chosen}">
                            <li><a href="${sort}">${sortingType.value.name}</a></li>
                        </c:if>
                    </c:forEach>
                </ul>
            </div>

            <div class="products_content">
                <div class="products_options">
                    <h2>Filters</h2>

                    <c:if test="${brands.size() > 0}">
                        <div class="product_filters">
                            <h3>Brand</h3>
                            <ul>
                                <c:forEach items="${brands}" var="brand">
                                    <c:url value="" var="byBrandChosen">
                                        <c:param name="brand" value="${brand.id}"/>
                                        <c:param name="sort" value="${lastSort}"/>
                                        <c:if test="${lastQuery!=null}">
                                            <c:param name="query" value="${lastQuery}"/>
                                        </c:if>
                                    </c:url>
                                    <c:url value="" var="byBrandNotChosen">
                                        <c:param name="sort" value="${lastSort}"/>
                                        <c:if test="${lastQuery!=null}">
                                            <c:param name="query" value="${lastQuery}"/>
                                        </c:if>
                                    </c:url>
                                    <c:if test="${brand.chosen}">
                                        <li class=" product_filter_link_activated"><a href="${byBrandNotChosen}"
                                                                                      class="product_filter_link">${brand.name}</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${!brand.chosen}">
                                        <li><a href="${byBrandChosen}" class="product_filter_link">${brand.name}</a>
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:if>

                </div>
                <div class="products_list">
                    <c:forEach items="${products}" var="product">
                        <div class="item_1 product_list_item">
                            <a href="<c:url value="/product/${product.id}"/>">
                                <img src="<c:url value="/image/showMainImage?id=${product.id}"/>" alt="item">
                                <h5>${product.brand.name} ${product.name}</h5>
                                <p class="price_holder">${product.price} $</p>
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="clearfix"></div>

            <c:if test="${pages.size() > 1}">
                <div class="pagination">
                    <ul>
                        <c:url value="" var="prevPage">
                            <c:param name="page" value="${lastChosenPage - 1}"/>
                            <c:if test="${lastBrand != null}">
                                <c:param name="brand" value="${lastBrand}"/>
                            </c:if>
                            <c:if test="${lastQuery!=null}">
                                <c:param name="query" value="${lastQuery}"/>
                            </c:if>
                            <c:param name="sort" value="${lastSort}"/>
                        </c:url>
                        <c:if test="${lastChosenPage > 1}">
                            <li><a href="${prevPage}"><<</a></li>
                        </c:if>
                        <c:forEach items="${pages}" var="page">
                            <c:url value="" var="changePage">
                                <c:param name="page" value="${page}"/>
                                <c:if test="${lastBrand != null}">
                                    <c:param name="brand" value="${lastBrand}"/>
                                </c:if>
                                <c:if test="${lastQuery!=null}">
                                    <c:param name="query" value="${lastQuery}"/>
                                </c:if>
                                <c:param name="sort" value="${lastSort}"/>
                            </c:url>
                            <c:if test="${page == lastChosenPage}">
                                <li><a href="${changePage}" class="page_chosen">${page}</a></li>
                            </c:if>
                            <c:if test="${page != lastChosenPage}">
                                <li><a href="${changePage}">${page}</a></li>
                            </c:if>
                        </c:forEach>
                        <c:url value="" var="nextPage">
                            <c:param name="page" value="${lastChosenPage + 1}"/>
                            <c:if test="${lastBrand != null}">
                                <c:param name="brand" value="${lastBrand}"/>
                            </c:if>
                            <c:if test="${lastQuery!=null}">
                                <c:param name="query" value="${lastQuery}"/>
                            </c:if>
                            <c:param name="sort" value="${lastSort}"/>
                        </c:url>
                        <c:if test="${lastChosenPage < pages.getLast()}">
                            <li><a href="${nextPage}">>></a></li>
                        </c:if>

                    </ul>
                </div>
            </c:if>
        </section>
    </div>

</div><!-- .wrapper -->

<%@include file="../templates/footer.jsp" %>

</body>
</html>