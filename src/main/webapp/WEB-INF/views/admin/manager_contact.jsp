<%-- 
    Document   : index
    Created on : May 24, 2023, 8:41:23 AM
    Author     : Admin
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%--<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
           prefix="decorator" %>--%>

<html lang="en">
<head>
<meta charset="utf-8">
<title>Twitter Bootstrap shopping cart</title>
<title><decorator:title default="Master-layout" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Bootstrap styles -->
<link href="<c:url value="/assets/user/css/bootstrap.css"/>"
	rel="stylesheet" />
<!-- Customize styles -->
<link href="<c:url value="/assets/style.css"/>" rel="stylesheet" />
<!-- font awesome styles -->
<link
	href="<c:url value="/assets/user/font-awersome/font-awesome.css"/>"
	rel="stylesheet">

<link
	href="<c:url value="/assets/user/font-awersome/font-awesome-ie7.min.css"/>"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://kit.fontawesome.com/7baff7d167.js"
	crossorigin="anonymous"></script>
<link rel="shortcut icon" href="<c:url value="/assets/favicon.ico"/>">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/solid.css"
	integrity="sha384-Tv5i09RULyHKMwX0E8wJUqSOaXlyu3SQxORObAI08iUwIalMmN5L6AvlPX2LMoSE"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/fontawesome.css"
	integrity="sha384-jLKHWM3JRmfMU0A5x5AkjWkw/EYfGUAGagvnfryNV3F9VqM98XiIH7VBGVoxVSc7"
	crossorigin="anonymous" />
	
</head>
<body>
	<!-- 
                Upper Header Section 
        -->
	<%@include file="/WEB-INF/views/layouts/user/menu.jsp"%>

	<!--
        Lower Header Section 
        -->
	<div class="container">
		<div id="gototop"></div>

		<%@include file="/WEB-INF/views/layouts/user/header.jsp"%>

		<div class="row">
			<div class="span9">
				<div class="row">
					<div class="span12">
						<ul class="breadcrumb">
							<li><a href="<c:url value="/home"/>">Home</a> <span
								class="divider">/</span></li>
							<li class="active">Manager Contact</li>
						</ul>

						<div class="well well-small">
							<h1>
								Manager Contact<small class="pull-right"></small>
							</h1>
							<h4 style="color: red;">${status}</h4>
							<hr class="soften" />

							<table class="table table-bordered table-condensed">
								<thead>
									<tr>
										<th>ID</th>
										<th>Name</th>
										<th>Email</th>
										<th>Subject</th>
										<th>Content</th>
										<th>Delete</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="o" items="${listContact}">
										<tr>
											<td>${o.id}</td>
											<td>${o.name}</td>
											<td>${o.email}</td>
											<td>${o.subject}</td>
											<td>${o.content}</td>
											<td><a
												href="#deleteEmployeeModal" onclick="confirmDelete(${o.id});" data-toggle="modal"
												class="btn btn-mini btn-danger"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i>
											</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<br />
							<div class="span4">
								<h4>Email Us</h4>
								<form:form class="form-horizontal" id="form-email"
									action="sendFeedback" method="post" modelAttribute="contact">
									<fieldset>
										<div class="control-group">

											<form:input type="text" placeholder="name"
												class="input-xlarge" path="name" />

										</div>
										<div class="control-group">

											<form:input type="email" placeholder="email"
												class="input-xlarge" path="email" />

										</div>
										<div class="control-group">

											<form:input type="text" placeholder="subject"
												class="input-xlarge" path="subject" />

										</div>
										<div class="control-group">
											<form:textarea rows="3" id="textarea" class="input-xlarge"
												path="content"></form:textarea>

										</div>

										<button class="shopBtn" id="btnSenDMail" type="submit">Send
											email</button>

									</fieldset>
								</form:form>
							</div>
						</div>
						</div>
						</div>
						</div>
						</div>
						
						
					

		
		<%@include file="/WEB-INF/views/layouts/user/footer.jsp"%>
	</div>
	<!-- /container -->
	<div id="deleteEmployeeModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="deleteContact" method="post">
					<div class="modal-header">
						<h4 class="modal-title">Delete User</h4>
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
					</div>
					<input type="hidden" name="contactId" id="contactId">
					<div class="modal-body">
						<p>Are you sure you want to delete these Record?</p>
						<p class="text-warning">
							<small>This action cannot be undone!</small>
						</p>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal"
							value="Cancel"> <input type="submit"
							class="btn btn-danger" onclick="notification('Xóa liên hệ thành công!');" value="Delete">
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<div class="copyright">
		<div class="container">
			<p class="pull-right">
				<a href="#"><img
					src="<c:url value="/assets/user/img/maestro.png"/>" alt="payment"></a>
				<a href="#"><img src="<c:url value="/assets/user/img/mc.png"/>"
					alt="payment"></a> <a href="#"><img
					src="<c:url value="/assets/user/img/pp.png"/>" alt="payment"></a>
				<a href="#"><img
					src="<c:url value="/assets/user/img/visa.png"/>" alt="payment"></a>
				<a href="#"><img
					src="<c:url value="/assets/user/img/disc.png"/>" alt="payment"></a>
			</p>
			<span>Copyright &copy; 2013<br> bootstrap ecommerce
				shopping template
			</span>
		</div>
	</div>
	<a href="#" class="gotop"><i class="icon-double-angle-up"></i></a>
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<c:url value="/assets/user/js/jquery.js"/>"></script>
	<script src="<c:url value="/assets/user/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/assets/user/js/jquery.easing-1.3.min.js"/>"></script>
	<script
		src="<c:url value="/assets/user/js/jquery.scrollTo-1.4.3.1-min.js"/>"></script>
	<script src="<c:url value="/assets/user/js/shop.js"/>"></script>
	<script type="text/javascript">
		function confirmDelete(contactId){
				$("#contactId").val(contactId);
			}

		function notification(message){
			alert(message);
	    }
	</script>

</body>
</html>
