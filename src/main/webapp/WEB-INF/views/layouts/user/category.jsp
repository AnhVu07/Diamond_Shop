<%-- 
    Document   : category
    Created on : Jul 20, 2023, 11:19:03 AM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="sidebar" class="span3">
    <div class="well well-small">
        <ul class="nav nav-list">
            <c:forEach var="o" items="${categorys}">
                <li class="${idCategory==o.idCategory? "active" : " "}"><a href="<c:url value="/category/${o.idCategory}"/>"><span class="icon-caret-right"></span>${o.name}</a></li>
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
