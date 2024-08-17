<%-- 
    Document   : index
    Created on : May 24, 2023, 8:41:23 AM
    Author     : Admin
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
           prefix="decorator" %>--%>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Twitter Bootstrap shopping cart</title>
        <title><decorator:title default="Master-layout"/></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- Bootstrap styles -->
        <link href="<c:url value="/assets/user/css/bootstrap.css"/>" rel="stylesheet"/>
        <!-- Customize styles -->
        <link href="<c:url value="/assets/style.css"/>" rel="stylesheet"/>
        <!-- font awesome styles -->
        <link href="<c:url value="/assets/user/font-awersome/font-awesome.css"/>" rel="stylesheet">

        <link href="<c:url value="/assets/user/font-awersome/font-awesome-ie7.min.css"/>" rel="stylesheet">
        <script src="https://kit.fontawesome.com/7baff7d167.js" crossorigin="anonymous"></script> 
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

        <!--[if lt IE 9]>
                <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

        <!-- Favicons -->
        <link rel="shortcut icon" href="<c:url value="/assets/favicon.ico"/>">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/solid.css" integrity="sha384-Tv5i09RULyHKMwX0E8wJUqSOaXlyu3SQxORObAI08iUwIalMmN5L6AvlPX2LMoSE" crossorigin="anonymous"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/fontawesome.css" integrity="sha384-jLKHWM3JRmfMU0A5x5AkjWkw/EYfGUAGagvnfryNV3F9VqM98XiIH7VBGVoxVSc7" crossorigin="anonymous"/>

        <style>
            .pagination {
                display: flex;
                justify-content: center;
            }

            .pagination a {
                color: black;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
                transition: background-color .3s;
                border: 1px solid #ddd;
            }

            .pagination a.active {
                background-color: #4CAF50;
                color: white;
                border: 1px solid #4CAF50;
            }

            .pagination a:hover:not(.active) {background-color: #ddd;}
        </style>
    </head>
    <body>
        <%@include file="/WEB-INF/views/layouts/user/menu.jsp" %>

    <!--
    Lower Header Section 
    -->
    <div class="container">
        <div id="gototop"> </div>

        <%@include file="/WEB-INF/views/layouts/user/header.jsp" %>



        <div class="row">
            <%@include file="/WEB-INF/views/layouts/user/category.jsp" %>
            <div class="span9">



                <div class="well well-small">
                    <h3><a class="btn btn-mini pull-right" href="products.html" title="View more">VIew More<span class="icon-plus"></span></a> Featured Products  </h3>
                    <h4 style="color: red;">${status}</h4>
                    <hr class="soften"/>
                    <div class="row-fluid">
                        <c:if test="${listPByPagination.size()>0}">
                            <ul class="thumbnails">
                                <c:forEach var="o" items="${listPByPagination}" varStatus="loop">
                                    <li class="span4">
                                        <div class="thumbnail">
                                            <a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
                                            <a href="<c:url value="/detail/${o.id_productt}"/>"><img src="<c:url value="/assets/user/img/${o.image}"/>" alt=""></a>
                                            <div class="caption cntr">
                                                <p>${o.title}</p>
                                                <p><strong><fmt:formatNumber type="number" value="${o.price}" groupingUsed="true"/>đ</strong></p>
                                                <h4><a class="shopBtn" href="<c:url value="/cart/${o.id_productt}/1"/>" onclick="redirect();" title="add to cart"> Add to cart </a></h4>
                                                <div class="actionList">
                                                    <a class="pull-left" href="#">Add to Wish List </a> 
                                                    <a class="pull-left" href="#"> Add to Compare </a>
                                                </div> 
                                                <br class="clr">
                                            </div>
                                        </div>
                                    </li>
                                    <c:if test="${(loop.index +1)%3==0||(loop.index +1)==listPByPagination.size()}">
                                    </ul>
                                    <c:if test="${(loop.index +1) < listPByPagination.size()}">
                                        <ul class="thumbnails">
                                        </c:if>
                                    </c:if>
                                </c:forEach>

                            </c:if>
                    </div>
                </div>
                <!--
                Featured Products
                -->


                <div class="well well-small">
                    <a class="btn btn-mini pull-right" href="#">View more <span class="icon-plus"></span></a>
                    Popular Products 
                </div>
                <hr>
            </div>
        </div>
        <div class="pagination">
            <c:forEach var="o" begin="1" end="${pagination.totalPage}" varStatus="loop">
                <c:if test="${loop.index ==pagination.currentPage}">
                    <a href="<c:url value="/category/${idCategory}/${loop.index}"/>" class="active">${loop.index}</a>
                </c:if>
                <c:if test="${loop.index !=pagination.currentPage}">
                    <a href="<c:url value="/category/${idCategory}/${loop.index}"/>">${loop.index}</a>
                </c:if>
            </c:forEach>
        </div>
        <%@include file="/WEB-INF/views/layouts/user/footer.jsp" %>
    </div><!-- /container -->

    <div class="copyright">
        <div class="container">
            <p class="pull-right">
                <a href="#"><img src="<c:url value="/assets/user/img/maestro.png"/>" alt="payment"></a>
                <a href="#"><img src="<c:url value="/assets/user/img/mc.png"/>" alt="payment"></a>
                <a href="#"><img src="<c:url value="/assets/user/img/pp.png"/>" alt="payment"></a>
                <a href="#"><img src="<c:url value="/assets/user/img/visa.png"/>" alt="payment"></a>
                <a href="#"><img src="<c:url value="/assets/user/img/disc.png"/>" alt="payment"></a>
            </p>
            <span>Copyright &copy; 2013<br> bootstrap ecommerce shopping template</span>
        </div>
    </div>
    <a href="#" class="gotop"><i class="icon-double-angle-up"></i></a>
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<c:url value="/assets/user/js/jquery.js"/>"></script>
    <script src="<c:url value="/assets/user/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/assets/user/js/jquery.easing-1.3.min.js"/>"></script>
    <script src="<c:url value="/assets/user/js/jquery.scrollTo-1.4.3.1-min.js"/>"></script>
    <script src="<c:url value="/assets/user/js/shop.js"/>"></script>
    <script type="text/javascript">
    function redirect(){
    	window.location.href = document.referrer;
    }
    </script>
</body>
</html>
