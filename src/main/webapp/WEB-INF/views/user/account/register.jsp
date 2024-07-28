<%-- 
    Document   : index
    Created on : May 24, 2023, 8:41:23 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
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
        <script src="https://kit.fontawesome.com/7baff7d167.js" crossorigin="anonymous"></script>

        <link href="<c:url value="/assets/user/font-awersome/font-awesome-ie7.min.css"/>" rel="stylesheet">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <!--[if lt IE 9]>
                <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

        <!-- Favicons -->
        <link rel="shortcut icon" href="<c:url value="/assets/favicon.ico"/>">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/solid.css" integrity="sha384-Tv5i09RULyHKMwX0E8wJUqSOaXlyu3SQxORObAI08iUwIalMmN5L6AvlPX2LMoSE" crossorigin="anonymous"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/fontawesome.css" integrity="sha384-jLKHWM3JRmfMU0A5x5AkjWkw/EYfGUAGagvnfryNV3F9VqM98XiIH7VBGVoxVSc7" crossorigin="anonymous"/>
        <style>
            .form-horizontal .control-label{
                margin-right: 20px;
            }

            .shopBtn {width: 100px;
            }
        </style>
    </head>
    <body>
        <!-- 
                Upper Header Section 
        -->
        <%@include file="/WEB-INF/views/layouts/user/menu.jsp" %>

        <!--
        Lower Header Section 
        -->
        <div class="container">
            <div id="gototop"> 

                <%@include file="/WEB-INF/views/layouts/user/header.jsp" %>

                <div class="row">

                    <%@include file="/WEB-INF/views/layouts/user/category.jsp" %>

                    <div class="span9">
                        <ul class="breadcrumb">
                            <li><a href="/home">Home</a> <span class="divider">/</span></li>
                            <li class="active">Registration</li>
                        </ul>
                        <h3> Registration</h3>	
                        <hr class="soft"/>
                        <div class="well">
                            <form:form class="form-horizontal" id="myForm" action="dang-ky" method="post" modelAttribute="user">  
                                <h3>Your Personal Details</h3>
                                <p style="color: red"class="">${sessionScope.status1}</p>
                                <div class="control-group">
                                    <label class="control-label" for="inputname">Họ và tên<sup>*</sup></label>
                                    <div class="controls">
                                        <form:input id="inputname" class="name" placeholder="Mời nhập họ và tên" type="text" path="displayName"/>
                                        <span id="nameError" class="error" style="color: red;"></span>  
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="inputEmail">Email <sup>*</sup></label>
                                    <div class="controls">
                                        <form:input id="inputEmail" class="email" type="email" placeholder="Mời nhập email" path="email"  /> 
                                        <span id="emailError" class="error" style="color: red;"></span>
                                    </div>
                                </div>	  
                                <div class="control-group">
                                    <label class="control-label">Password <sup>*</sup></label>
                                    <div class="controls">
                                        <form:input placeholder="Mời nhập password" class="password" type="password" path="password" /> 
                                        <span id="passwordError" class="error" style="color: red;"></span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">Address <sup>*</sup></label>
                                    <div class="controls">
                                        <form:input placeholder="Mời nhập địa chỉ" class="address" type="text" path="address" /> 
                                        <span id="addressError" class="error" style="color: red;"></span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <div class="controls">
                                        <input type="submit" name="submitAccount" value="Register" class="exclusive shopBtn">
                                    </div>
                                </div>
                            </form:form>
                        </div>
                     <!--   <div class="well">
                            <form:form class="form-horizontal" action="/login" method="post" modelAttribute="user">  
                                <h3>Login</h3>
                                <div class="control-group">
                                    <label class="control-label" for="inputEmail">Email <sup>*</sup></label>
                                    <form:input placeholder="Email"  type="email" path="email" /> 
                                </div>
                                <div class="control-group">
                                    <label class="control-label">Password <sup>*</sup></label>
                                    <form:input placeholder="Password"  type="text" path="password" /> 
                                </div>
                                <div class="control-group">
                                    <div class="control-group">
                                        <div class="controls">
                                            <input type="submit" name="submitAccount" value="Login" class="exclusive shopBtn">
                                        </div>
                                    </div>
                                </div>

                            </form:form>
                        </div>
                        -->

                    </div>


                </div>


            </div>        





            <%@include file="/WEB-INF/views/layouts/user/footer.jsp" %>
        </div>
        <!-- /container -->

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
        
        $(document).ready(function() {
            $("#myForm").submit(function(event) {
                var isValid = true;
                $(".error").text("");  

                var name = $(".name").val();
                var email = $(".email").val();
                var password = $(".password").val();
                var adress = $(".address").val();

                if (name.trim() === "") {
                    $("#nameError").text("Name is required.");
                    isValid = false;
                }
                
                if (password.trim() === "") {
                    $("#passwordError").text("Password is required.");
                    isValid = false;
                }
                
                if (adress.trim() === "") {
                    $("#addressError").text("Address is required.");
                    isValid = false;
                }

                var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
                if (!emailPattern.test(email)) {
                    $("#emailError").text("Invalid email address.");
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
