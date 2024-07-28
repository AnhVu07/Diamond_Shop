<%-- 
    Document   : index
    Created on : May 24, 2023, 8:41:23 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%--<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
           prefix="decorator" %>--%>

<html lang="en">
<head>
<meta charset="utf-8">
<title>Please sign in</title>
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

<script src="https://www.google.com/recaptcha/api.js" async defer></script>

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

.imgCaptcha {
    margin-left: 46px;
}
</style>
</head>
<body>

	<div class="container">


		<div class="well">
			<form class="form-horizontal" id="myForm" action="/login" method="post">
				<h2>Please sign in</h2>
				<c:if test="${error != null}">
					<h4 style="color: red;">${error}</h4>
				</c:if>

				<div class="control-group">
					<label class="control-label" for="inputEmail">Email <sup>*</sup></label>
					<input placeholder="Username" id="username" name="username" type="email"  />
						<span id="usernameError" class="error" style="color: red;"></span>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputPassword">Password <sup>*</sup></label>
					<input placeholder="Password" id="password" name="password" type="password" />
					 <span id="passwordError" class="error" style="color: red;"></span>
						
				</div>

				<div class="control-group">
					<img src="<c:url value="/captcha"/>" class="imgCaptcha" alt="Captcha Imagee" /> 
					<input type="text" name="captcha" id="captcha" placeholder="Nhập mã"  />
					 <span id="captchaError" class="error" style="color: red;"></span>
				</div>
				<div class="control-group">
					<div class="control-group">
						<div class="controls">
							<input type="submit" name="Send" id="loginButton" value="Login"
								class="exclusive shopBtn">
						</div>
					</div>
				</div>

			</form>

			<h2 class="form-signin-heading">Login with OAuth2 Google</h2>
			<a href="/oauth2/authorization/google">Google</a>
			<br>
			<a href="/home"><h2 class="form-signin-heading">Home</h2></a>
		</div>


	</div>

	<!-- /container -->



	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<c:url value="/assets/user/js/jquery.js"/>"></script>
	<script src="<c:url value="/assets/user/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/assets/user/js/jquery.easing-1.3.min.js"/>"></script>
	<script
		src="<c:url value="/assets/user/js/jquery.scrollTo-1.4.3.1-min.js"/>"></script>
	<script src="<c:url value="/assets/user/js/shop.js"/>"></script>
	<script type="text/javascript">
	$(document).ready(function() {
	    $('#myForm').submit(function(event) {
	       

	        var isValid = true;
	        $(".error").text("");  

	        var username = $("#username").val().trim();
	        var password = $("#password").val().trim();
	        var captcha = $("#captcha").val().trim();
	        
	        // Kiểm tra hợp lệ dữ liệu
	        if (password === "") {
	            $("#passwordError").text("required");
	            isValid = false;
	        }
	        
	        if (captcha === "") {
	            $("#captchaError").text("required");
	            isValid = false;
	        }

	        var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
	        if (!emailPattern.test(username)) {
	            $("#usernameError").text("Invalid");
	            isValid = false;
	        }

	       
	      if (!isValid) {
	    	  event.preventDefault();
	       }

	    });
	});

					    
	</script>
</body>
</html>
