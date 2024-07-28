<%-- 
    Document   : index
    Created on : May 24, 2023, 8:41:23 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

        <link href="<c:url value="/assets/user/font-awersome/font-awesome-ie7.min.css"/>" rel="stylesheet">
        <script src="https://kit.fontawesome.com/7baff7d167.js" crossorigin="anonymous"></script> 

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <!--[if lt IE 9]>
                <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

        <!-- Favicons -->
        <link rel="shortcut icon" href="<c:url value="/assets/favicon.ico"/>">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/solid.css" integrity="sha384-Tv5i09RULyHKMwX0E8wJUqSOaXlyu3SQxORObAI08iUwIalMmN5L6AvlPX2LMoSE" crossorigin="anonymous"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/fontawesome.css" integrity="sha384-jLKHWM3JRmfMU0A5x5AkjWkw/EYfGUAGagvnfryNV3F9VqM98XiIH7VBGVoxVSc7" crossorigin="anonymous"/>
        <style>
           
        </style>
    </head>
    <body>

        <%@include file="/WEB-INF/views/layouts/user/menu.jsp" %>

        <!--
        Lower Header Section 
        -->
        <div class="container">
            <div id="gototop"> </div>

            <%@include file="/WEB-INF/views/layouts/user/header.jsp" %>

            <hr class="soften">
            <div class="well well-small">
                <h1>Visit us</h1>
                <hr class="soften"/>	
                <div class="row-fluid">
                    <div class="span8 relative">
                        <iframe style="width:100%; height:350px" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="http://maps.google.co.uk/maps?f=q&amp;source=s_q&amp;hl=en&amp;geocode=&amp;q=Newbury+Street,+Boston,+MA,+United+States&amp;aq=1&amp;oq=NewBoston,+MA,+United+States&amp;sll=42.347238,-71.084011&amp;sspn=0.014099,0.033023&amp;ie=UTF8&amp;hq=Newbury+Street,+Boston,+MA,+United+States&amp;t=m&amp;ll=42.348994,-71.088248&amp;spn=0.001388,0.006276&amp;z=18&amp;iwloc=A&amp;output=embed"></iframe>

                        <div class="absoluteBlk">
                            <div class="well wellsmall">
                                <h4>Contact Details</h4>
                                <h5>
                                    2601 Mission St.<br/>
                                    San Francisco, CA 94110<br/><br/>

                                    info@mysite.com<br/>
                                    ﻿Tel 123-456-6780<br/>
                                    Fax 123-456-5679<br/>
                                    web:www.mysitedomain.com
                                </h5>
                            </div>
                        </div>
                    </div>
                    <p style="color: red;text-align: center">${mess}</p>
                    <div class="span4">
                        <h4>Email Us</h4>
                        <form:form class="form-horizontal" id="myForm" action="/sendMail" method="post" modelAttribute="contact">
                            <fieldset>
                                <div class="control-group">

                                    <form:input type="text" placeholder="name" id="name" class="input-xlarge" path="name" />
									<span id="nameError" class="error" style="color: red;"></span>
                                </div>
                                <div class="control-group">

                                    <form:input type="email" placeholder="email" id="email" class="input-xlarge" path="email"/>
									<span id="emailError" class="error" style="color: red;"></span>
                                </div>
                                <div class="control-group">

                                    <form:input type="text" placeholder="subject" id="subject" class="input-xlarge" path="subject"/>
									<span id="subjectError" class="error" style="color: red;"></span>
                                </div>
                                <div class="control-group">
                                    <form:textarea rows="3" id="content" class="input-xlarge" path="content"></form:textarea>
									<span id="contentError" class="error" style="color: red;"></span>
                                    </div>

                                    <button class="shopBtn" type="submit">Send email</button>

                                </fieldset>
                        </form:form>
                    </div>
                </div>


            </div>
            <%@include file="/WEB-INF/views/layouts/user/footer.jsp" %>
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

                var name = $("#name").val();
                var email = $("#email").val();
                var subject = $("#subject").val();
                var content = $("#content").val();

                if (name.trim() === "") {
                    $("#nameError").text("Name is required.");
                    isValid = false;
                }
                
                if (email.trim() === "") {
                    $("#emailError").text("Email is required.");
                    isValid = false;
                }
                if (subject.trim() === "") {
                    $("#subjectError").text("Subject is required.");
                    isValid = false;
                }
                if (content.trim() === "") {
                    $("#contentError").text("Content is required.");
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
