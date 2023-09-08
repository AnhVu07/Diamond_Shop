<%-- 
    Document   : index
    Created on : May 24, 2023, 8:41:23 AM
    Author     : Admin
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    </head>
    <body>
        <%@include file="/WEB-INF/views/layouts/user/menu.jsp" %>

        <!--
        Lower Header Section 
        -->
        <div class="container">
            <div id="gototop"> </div>

            <%@include file="/WEB-INF/views/layouts/user/header.jsp" %>

            <div class="row">
                <div class="span9">
                    <div class="row">
                        <div class="span12">
                            <ul class="breadcrumb">
                                <li><a href="<c:url value="/home"/>">Home</a> <span class="divider">/</span></li>
                                <li class="active">Check Out</li>
                            </ul>
                            <div class="well well-small">
                                <h1>Check Out <small class="pull-right"> ${totalQuatity} Items are in the cart </small></h1>
                                <hr class="soften"/>	

                                <table class="table table-bordered table-condensed">
                                    <thead>
                                        <tr>
                                            <th>Product</th>
                                            <th>Name</th>
                                            <th>Description</th>
                                            <th>Quantity </th>
                                            <th>Edit </th>
                                            <th>Delete </th>
                                            <th>Price</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="o" items="${cart}">

                                            <tr>
                                                <td><img width="100" src="<c:url value="/assets/user/img/${o.value.product.image}"/>" alt=""></td>
                                                <td>${o.value.product.name}</td>
                                                <td>${o.value.product.title}<br>Carate : 22<br>Model : n/a</td>
                                                <td>
                                                    <input class="span1" style="max-width:34px" placeholder="" id="quatity${o.key}" name="quatity" min="1" size="16" type="number" value="${o.value.totalQuatity}">
                                                </td>
                                                <td>
                                                    <button data-id = "${o.key}" type="button" class="shopBtn editCart icon-edit"></button>
                                                    <span class=""></span>
                                                </td>
                                                <td>
                                                    <a href="<c:url value="/deleteCart/${o.value.product.id_productt}"/>" class="btn btn-mini btn-danger" type="button">
                                                        <span class="icon-remove"></span>
                                                    </a>
                                                </td>
                                                <td><fmt:formatNumber type="number" groupingUsed="true" value="${o.value.product.price}" />đ</td>
                                            </tr>
                                        </c:forEach>
                                        <tr>
                                            <td colspan="6" class="alignR" style="font-weight: 700;">Total products:</td>
                                            <td class="label label-primary"> <fmt:formatNumber type="number" groupingUsed="true" value="${totalPrice}" />đ</td>
                                        </tr>
                                    </tbody>
                                </table><br/>

                                <table class="table table-bordered">
                                    <tbody>
                                        <tr>
                                            <td> 
                                                <form class="form-inline">
                                                    <label style="min-width:159px"> VOUCHERS Code: </label> 
                                                    <input type="text" class="input-medium" placeholder="CODE">
                                                    <button type="submit" class="shopBtn"> ADD</button>
                                                </form>
                                            </td>
                                        </tr>

                                    </tbody>
                                </table>
                                <table class="table table-bordered">
                                    <tbody>
                                        <tr><td>ESTIMATE YOUR SHIPPING & TAXES</td></tr>
                                        <tr> 
                                            <td>
                                                <form class="form-horizontal">
                                                    <div class="control-group">
                                                        <label class="span2 control-label" for="inputEmail">Country</label>
                                                        <div class="controls">
                                                            <input type="text" placeholder="Country">
                                                        </div>
                                                    </div>
                                                    <div class="control-group">
                                                        <label class="span2 control-label" for="inputPassword">Post Code/ Zipcode</label>
                                                        <div class="controls">
                                                            <input type="password" placeholder="Password">
                                                        </div>
                                                    </div>
                                                    <div class="control-group">
                                                        <div class="controls">
                                                            <button type="submit" class="shopBtn">Click to check the price</button>
                                                        </div>
                                                    </div>
                                                </form> 
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>		
                                <a href="<c:url value="/home"/>" class="shopBtn btn-large"><span class="icon-arrow-left"></span> Continue Shopping </a>
                                <a href="<c:url value="/checkout"/>" class="shopBtn btn-large pull-right">Thanh toán <span class="icon-arrow-right"></span></a>

                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <%@include file="/WEB-INF/views/layouts/user/footer.jsp" %>
        </div><!-- /container -->

        <div class="copyright">
            <div class="container">
                <p class="pull-right">
                    <a href="#"><img src="<c:url value="/assets/user/img/maestro.png"/>" alt="payment"></a>
                    <a href="#"><img src="<c:url value="/assets/user/img/mc.png"/>" alt="payment"></a>
                    <a href="#"><img src="<c:url value="/assets/user/img/pp.png"/>" alt="payment"></a>
                    <a href="#"><img src="<c:url value="/assets/user/img/visa.png"/>" alt="payment"></a>
                    <a href="#"><img src="<c:url value="/assets/user/img/disc.png"/>" alt="payment"></a>
                </p>
                <span>BaoDom &copy; 2013<br> Team BaoDom</span>
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
            $(".editCart").on("click", function () {
                var id = $(this).data("id");
                var quaty = $("#quatity" + id).val();
                window.location = "editCart/" + id + "/" + quaty;
            });
        </script>
    </body>
</html>
