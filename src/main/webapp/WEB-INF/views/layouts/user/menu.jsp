<%-- 
    Document   : menu
    Created on : Jul 20, 2023, 11:14:43 AM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<div class="topNav">
	<div class="container">
		<div class="alignR">
			<a class="active" href="<c:url value="/home"/>"><span class="icon-home"></span> Home</a>
			<sec:authorize access="isAuthenticated()">
					<a href="#"> ${displayName}</a>
				<c:if test="${displayName==null}">
				<a href="#"><span class="icon-user"></span> ${pageContext.request.userPrincipal.principal.fullName}</a>
				</c:if>
			</sec:authorize>
			<sec:authorize access="hasAuthority('ROLE_ADMIN')">
				<a href="<c:url value="/admin/manager_products"/>"><span
					class="icon-book"></span> Products</a>
			</sec:authorize>
			<sec:authorize access="hasAuthority('ROLE_ADMIN')">
				<a href="<c:url value="/admin/manager_category"/>"><span
					class="icon-apple"></span> Categories</a>
			</sec:authorize>
			<sec:authorize access="hasAuthority('ROLE_ADMIN')">
				<a href="<c:url value="/admin/manager_accounts"/>"><span
					class="icon-user"></span> Accounts</a>
			</sec:authorize>
			<sec:authorize access="hasAuthority('ROLE_ADMIN')">
				<a href="<c:url value="/admin/manager_orders"/>"><span
					class="icon-bar-chart"></span> Orders</a>
			</sec:authorize>
			<sec:authorize access="hasAuthority('ROLE_ADMIN')">
				<a href="<c:url value="/admin/manager_productReview"/>"><span
					class="icon-star"></span> Reviews</a>
			</sec:authorize>
			<sec:authorize access="hasAuthority('ROLE_ADMIN')">
				<a href="<c:url value="/admin/manager_contact"/>"><span
					class="icon-envelope"></span> Contacts</a>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<a href="<c:url value="/logout"/>"><span
					class="icon-circle-arrow-right"></span> Logout</a>
			</sec:authorize>
			<sec:authorize access="!isAuthenticated()">
				<a href="<c:url value="/register"/>"><span class="icon-edit"></span> Register</a>
			</sec:authorize>
			<sec:authorize access="!isAuthenticated()">
				<a href="<c:url value="/oauth2/authorization/google"/>"><span
					class="icon-google-plus"></span> Login with Google</a>
			</sec:authorize>
			<sec:authorize access="!isAuthenticated()">
				<a href="<c:url value="/forgot_password"/>"><span
					class="icon-key"></span> Forgot Password</a>
			</sec:authorize>
			<sec:authorize access="!isAuthenticated()">
				<a href="<c:url value="/login"/>"><span class="icon-signin"></span> Login</a>
			</sec:authorize>
			<sec:authorize access="hasAuthority('ROLE_USER')">
				<a href="<c:url value="/contact"/>"><span class="icon-envelope"></span> 
					Contact us</a>
			</sec:authorize>
			<a href="<c:url value="/cart"/>"><span class="icon-shopping-cart"></span>
				${totalQuatity} Item(s) - <span class="badge badge-warning"><fmt:formatNumber
						type="number" groupingUsed="true" value="${totalPrice}" />đ</span></a>
		</div>
	</div>
</div>
