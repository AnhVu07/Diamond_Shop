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
        <div id="gototop"> </div>

        <%@include file="/WEB-INF/views/layouts/user/header.jsp" %>



        <div class="row">

            <hr class="soften">
            <div>
                <h1>About us</h1>
            </div>
            <hr class="soften">
            <div class="row">
                <div class="span8">
                    <h6>
                        I'm a paragraph. Click here to add your own text and edit me. I’m a great place for you to tell a story and let your users know a little more about you.
                    </h6>
                    <p>
                        It’s easy. Just click “Edit Text” or double click me and you can start adding your own content and make changes to the font. Feel free to drag and drop me anywhere you like on your page. I’m a great place for you to tell a story and let your users know a little more about you.<br>
                        <br>
                        This is a great space to write long text about your company and your services. You can use this space to go into a little more detail about your company. Talk about your team and what services you provide. Tell your visitors the story of how you came up with the idea for your business and what makes you different from your competitors. Make your company stand out and show your visitors who you are.
                        <br><br>
                        Sometimes I'm right and I can be wrong. My own beliefs are in my song. The butcher, the banker, the drummer and then, makes no difference what group I'm in. I am everyday people! Yeah. Yeah.<br>
                    </p>
                </div>
                <div class="span4">
                    Monday - Friday<br/>
                    09:00am - 09:00pm<br/>
                    Saturday<br/>
                    09:00am - 07:00pm<br/>
                    Sunday<br/>
                    12:30pm - 06:00pm<br/>
                </div>
            </div>

            <%@include file="/WEB-INF/views/layouts/user/footer.jsp" %>
        </div>

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
</body>
</html>
