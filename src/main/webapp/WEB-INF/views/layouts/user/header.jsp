<%-- 
    Document   : header
    Created on : May 22, 2023, 9:56:24 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  

<!DOCTYPE html>
<header id="header">
    <div class="row">
        <div class="span4">
            <h1>
                <a class="logo" href="<c:url value="/home"/>"><span>Twitter Bootstrap ecommerce template</span> 
                    <img src="<c:url value="/assets/user/img/logo-bootstrap-shoping-cart.png"/>" alt="bootstrap sexy shop">
                </a>
            </h1>
        </div>
        <div class="span4">
            <div class="offerNoteWrapper">
                <h1 class="dotmark" style="line-height:1.5em; margin-right: -100px;text-align: center">
                    DIAMON SHOP  <i class="fa-regular fa-gem"></i>VỚI HÀNG ĐẸP VÀ CHẤT LƯỢNG SỐ 1 VIỆT NAM
                </h1>
            </div>
        </div>
        <div class="span4 alignR">
            <p><br> <strong> Support (24/7) :  086 8183 396 </strong><br><br></p>
<!--            <span class="btn btn-mini">[ 2 ] <span class="icon-shopping-cart"></span></span>
            <span class="btn btn-warning btn-mini">$</span>
            <span class="btn btn-mini">&pound;</span>
            <span class="btn btn-mini">&euro;</span>-->
        </div>
    </div>
                <p style="color: red;text-align: end">${sessionScope.status}</p>
                
</header>

<!--
Navigation Bar Section 
-->
<div class="navbar">
    <div class="navbar-inner">
        <div class="container">
            <a data-target=".nav-collapse" data-toggle="collapse" class="btn btn-navbar">
                <span class="icon-bar"></span>
            </a>
            <div class="nav-collapse">
                <ul class="nav">
                    <c:forEach var="o" items="${menus}">
                        <li class="active"><a id="ok" href="<c:url value="/${o.url}"/>">${o.name}</a></li>
                        </c:forEach>
                </ul>
                <form action="<c:url value="/search/"/>" class="navbar-search pull-left">
                    <input type="text" name="name" value="${key}" placeholder="Search" class="search-query span2">
                    <!--                    <button type="submit" class="shopBtn">Search</button>-->
                </form>
                <ul class="nav pull-right">
                    <li class="dropdown">

                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<!-- 
Body Section 
-->

