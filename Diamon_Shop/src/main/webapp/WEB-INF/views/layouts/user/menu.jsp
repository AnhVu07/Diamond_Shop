<%-- 
    Document   : menu
    Created on : Jul 20, 2023, 11:14:43 AM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="topNav">
    <div class="container">
        <div class="alignR">
            <div class="pull-left socialNw">
                <a href="#"><span class="icon-twitter"></span></a>
                <a href="#"><span class="icon-facebook"></span></a>
                <a href="#"><span class="icon-youtube"></span></a>
                <a href="#"><span class="icon-tumblr"></span></a>
            </div>
            <a class="active" href="<c:url value="/"/>" <span class="icon-home"></span> Home</a> 
            <c:if test="${infLogin!=null}">
                <a href="#"><span class="icon-user"></span>${infLogin.displayName}</a> 
                <a href="<c:url value="/log_out"/>"><span class="icon-circle-arrow-right"></span>Log out</a> 
            </c:if>
            <c:if test="${sessionScope.infLogin!=null&& sessionScope.infLogin.roles==true}">
                <a href="<c:url value="/manager_products"/>"><span class="icon-book"></span>Manager Products</a> 
            </c:if>
            <c:if test="${sessionScope.infLogin!=null&& sessionScope.infLogin.roles==true}">
                <a href="<c:url value="/manager_accounts"/>"><span class="icon-user"></span>Manager Accounts</a> 
            </c:if>
            <c:if test="${sessionScope.infLogin!=null&& sessionScope.infLogin.roles==true}">
                <a href="<c:url value="/manager_orders"/>"><span class="icon-bar-chart"></span>Manager Orders</a> 
            </c:if>
            <c:if test="${sessionScope.infLogin!=null&& sessionScope.infLogin.roles==true}">
                <a href="<c:url value="/manager_contact"/>"><span class="icon-envelope"></span>Manager Contact</a> 
            </c:if>
            <c:if test="${infLogin==null}">
                <a href="<c:url value="/register"/>"><span class="icon-edit"></span>Register & Login</a> 
            </c:if>
            <c:if test="${sessionScope.infLogin.roles!=true}">
                <a href="<c:url value="/contact"/>"><span class="icon-envelope"></span> Contact us</a>
            </c:if>
            <a href="<c:url value="/cart"/>"><span class="icon-shopping-cart"></span> ${totalQuatity} Item(s) - <span class="badge badge-warning"><fmt:formatNumber type="number" groupingUsed="true" value="${totalPrice}" />đ</span></a>
        </div>
    </div>
</div>
