<%-- 
    Document   : user
    Created on : May 22, 2023, 10:20:37 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
    prefix="decorator" %>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="<c:url value="/assets/user/font-awersome/font-awesome-ie7.min.css"/>" rel="stylesheet">
        <link rel="shortcut icon" href="<c:url value="/assets/favicon.ico"/>">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/solid.css" integrity="sha384-Tv5i09RULyHKMwX0E8wJUqSOaXlyu3SQxORObAI08iUwIalMmN5L6AvlPX2LMoSE" crossorigin="anonymous"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/fontawesome.css" integrity="sha384-jLKHWM3JRmfMU0A5x5AkjWkw/EYfGUAGagvnfryNV3F9VqM98XiIH7VBGVoxVSc7" crossorigin="anonymous"/>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
    </head>
    <body>
        <!-- 
                Upper Header Section 
        -->
        <div class="topNav">
            <div class="container">
                <div class="alignR">
                    <div class="pull-left socialNw">
                        <a href="#"><span class="icon-twitter"></span></a>
                        <a href="#"><span class="icon-facebook"></span></a>
                        <a href="#"><span class="icon-youtube"></span></a>
                        <a href="#"><span class="icon-tumblr"></span></a>
                    </div>
                    <a class="active" href="<c:url value="/"/>"> <span class="icon-home"></span> Home</a> 
                    <c:if test="${infLogin!=null}">
                        <a href="#"><span class="icon-user"></span>${infLogin.displayName}</a> 
                        <a href="<c:url value="/log_out"/>"><span class="icon-user"></span>Log out</a> 
                    </c:if>
                    <c:if test="${sessionScope.infLogin!=null&& sessionScope.infLogin.roles==true}">
                        <a href="<c:url value="/manager"/>"><span class="icon-edit"></span>Manager </a> 
                    </c:if>
                    <c:if test="${infLogin==null}">
                        <a href="<c:url value="/register"/>"><span class="icon-edit"></span> Free Register </a> 
                    </c:if>
                    <c:if test="${sessionScope.infLogin.roles!=true}">
                        <a href="<c:url value="/contact"/>"><span class="icon-envelope"></span> Contact us</a>
                    </c:if>
                    <a href="<c:url value="/cart"/>"><span class="icon-shopping-cart"></span> ${totalQuatity} Item(s) - <span class="badge badge-warning"><fmt:formatNumber type="number" groupingUsed="true" value="${totalPrice}" />đ</span></a>
                </div>
            </div>
        </div>

        <!--
        Lower Header Section 
        -->
        <div class="container">
            <div id="gototop"> 
                <%@include file="/WEB-INF/views/layouts/user/header.jsp" %>

                <div id="sidebar" class="span3">
                    <div class="well well-small">
                        <ul class="nav nav-list">
                            <c:forEach var="o" items="${categorys}">
                                <li><a href="<c:url value="/category/${o.idCategory}"/>"><span class="icon-chevron-right"></span>${o.name}</a></li>
                                    </c:forEach>
                            <li style="border:0"> &nbsp;</li>
                            <li> <a class="totalInCart" href="<c:url value="/cart"/>"><strong>Total Amount  <span class="badge badge-warning pull-right" style="line-height:18px;"><fmt:formatNumber type="number" groupingUsed="true" value="${totalPrice}" />đ</span></strong></a></li>
                        </ul>
                    </div>

                    <div class="well well-small alert alert-warning cntr">
                        <h2>50% Discount</h2>
                        <p> 
                            only valid for online order. <br><br><a class="defaultBtn" href="#">Click here </a>
                        </p>
                    </div>
                    <div class="well well-small" ><a href="#"><img src="<c:url value="/assets/user/img/paypal.jpg"/>" alt="payment method paypal"></a></div>

                    <a class="shopBtn btn-block" href="#">Upcoming products <br><small>Click to view</small></a>
                    <br>
                    <br>
                    <ul class="nav nav-list promowrapper">
                        <li style="border:0"> &nbsp;</li>
                        <li>
                            <div class="thumbnail">
                                <a class="zoomTool" href="<c:url value="/detail/${pLatest.idProducts}"/>" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
                                <img src="<c:url value="/assets/user/img/${pLatest.image}"/>" alt="shopping cart template">
                                <div class="caption">
                                    <h4><a class="defaultBtn" href="<c:url value="/detail/${pLatest.idProducts}"/>">VIEW</a> <span class="pull-right"><fmt:formatNumber value="${pLatest.price}" groupingUsed="true" type="number"/>đ</span></h4>
                                </div>
                            </div>
                        </li>
                        <li style="border:0"> &nbsp;</li>
                    </ul>

                </div>
                <%@include file="/WEB-INF/views/layouts/user/footer.jsp" %>
            </div>
        </div>

        <script src="<c:url value="/assets/user/js/jquery.js"/>"></script>
        <script src="<c:url value="/assets/user/js/bootstrap.min.js"/>"></script>
        <script src="<c:url value="/assets/user/js/jquery.easing-1.3.min.js"/>"></script>
        <script src="<c:url value="/assets/user/js/jquery.scrollTo-1.4.3.1-min.js"/>"></script>
        <script src="<c:url value="/assets/user/js/shop.js"/>"></script>
    </body>
</html>
