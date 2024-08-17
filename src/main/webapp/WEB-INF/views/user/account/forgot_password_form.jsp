<%-- 
    Document   : index
    Created on : May 24, 2023, 8:41:23 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<script src="https://kit.fontawesome.com/7baff7d167.js"
	crossorigin="anonymous"></script>

<link
	href="<c:url value="/assets/user/font-awersome/font-awesome-ie7.min.css"/>"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!--[if lt IE 9]>
                <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

<!-- Favicons -->
<link rel="shortcut icon" href="<c:url value="/assets/favicon.ico"/>">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/solid.css"
	integrity="sha384-Tv5i09RULyHKMwX0E8wJUqSOaXlyu3SQxORObAI08iUwIalMmN5L6AvlPX2LMoSE"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/fontawesome.css"
	integrity="sha384-jLKHWM3JRmfMU0A5x5AkjWkw/EYfGUAGagvnfryNV3F9VqM98XiIH7VBGVoxVSc7"
	crossorigin="anonymous" />
<style>
.form-horizontal .control-label {
	margin-right: 20px;
}

.shopBtn {
	width: 100px;
}

.well {
            margin: 20px 200px;
            }
</style>
</head>
<body>

	<div class="container">
		
					<div class="well">
						<form class="form-horizontal" id="myForm" action="/form_forgot_password"
							method="post">
							<h3>${Title}</h3>
							<c:if test="${pageTitle != null}">
								<p style="color: orange;text-align:center;">${pageTitle}</p>
							</c:if>
							<c:if test="${message != null}">
								<p style="color: red;text-align:center;">${message}</p>
							</c:if>

							<div class="control-group">
								<input name="token" type="hidden" value="${token}" /> <label
									class="control-label" for="inputEmail">Password <sup>*</sup></label>
								<div class="controls">
									<input name="password" id="password" type="password"
										placeholder="Mời nhập Password" />
								<span id="passwordError" class="error" style="color: red;"></span>
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">Confirm Password <sup>*</sup></label>
								<div class="controls">
									<input required autofocus placeholder="Mời nhập lại Password"
										id="confirmPassword" oninput="confirmPass(this);"
										type="password" />
										 <span id="confirmPasswordError" class="error" style="color: red;"></span>
								</div>
							</div>

							<div class="control-group">
								<div class="controls">
									<input type="submit" name="submitAccount" value="Change"
										class="exclusive shopBtn">
								</div>
							
							<div style="margin: 20px auto 0px auto;"><a style="right:0px;" href="/home">Về Trang Chủ đăng nhập</a></div>
						</form>

					</div>


				</div>


	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<c:url value="/assets/user/js/jquery.js"/>"></script>
	<script src="<c:url value="/assets/user/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/assets/user/js/jquery.easing-1.3.min.js"/>"></script>
	<script
		src="<c:url value="/assets/user/js/jquery.scrollTo-1.4.3.1-min.js"/>"></script>
	<script src="<c:url value="/assets/user/js/shop.js"/>"></script>

	<script type="text/javascript">
		$(document).ready(function() {

			$('#myForm').on('submit', function(e){
			$('.error').text('');
			var check = false;

			var password = $('#password').val();
			var confirmPassword = $('#confirmPassword').val();

			if(password===""){
				$('#passwordError').text('Enter required password');
				check = true;
			}

			if(confirmPassword===""){
				$('#confirmPasswordError').text('ConfirmPassword request');
				check = true;
			}

			if(check){
				e.preventDefault();
			}

			});
			
			$('#confirmPassword').on('input', function() {
				var confirmPassword = $(this).val();
				var password = $('#password').val();
				if (confirmPassword !== password) {
					this.setCustomValidity("Password does not match");
				} else {
					this.setCustomValidity("");
				}
			});
		});
	</script>

</body>
</html>
