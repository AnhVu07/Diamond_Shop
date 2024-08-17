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
            
            .well {
            margin: 20px 200px;
            }
        </style>
    </head>
    <body>
        
        <div class="container">
            
                      
                      <div class="well">
                            <form class="form-horizontal" id="myForm" action="/forgot_password" method="post">  
                               <h3>${pageTitle}</h3>	
                               <c:if test="${message!=null}">
                               <p style="color: orange;">${message}</p>
                               </c:if>
                                <div class="control-group">
                                    <label class="control-label" for="inputEmail">Email <sup>*</sup></label>
                                    <input placeholder="Your Email" name="email" id="email" type="email" required /> 
                                    <span id="emailError" class="error" style="color: red;"></span>
                                </div>
                                <div class="control-group">
                                    <div class="control-group">
                                        <div class="controls">
                                            <input type="submit" name="Send" value="Send" class="exclusive shopBtn">
                                        </div>
                                    </div>
                                </div>

                            </form>
                        </div>
                     

                    </div>

        <!-- /container -->

       
       
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="<c:url value="/assets/user/js/jquery.js"/>"></script>
        <script src="<c:url value="/assets/user/js/bootstrap.min.js"/>"></script>
        <script src="<c:url value="/assets/user/js/jquery.easing-1.3.min.js"/>"></script>
        <script src="<c:url value="/assets/user/js/jquery.scrollTo-1.4.3.1-min.js"/>"></script>
        <script src="<c:url value="/assets/user/js/shop.js"/>"></script>
	        <script type="text/javascript">
	
			$(document).ready(function (){
				$("#myForm").submit(function (e){
					$(".error").text("");
					
					var email = $("#email").val().trim();
					 var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
					 
				        if (!emailPattern.test(email)) {
				            $("#emailError").text("Email Invalid!");
				            e.preventDefault();
				        }
	
					});
				});
				
				
				
	        </script>
    </body>
</html>
