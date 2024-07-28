<%-- 
    Document   : index
    Created on : May 24, 2023, 8:41:23 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <link href='<c:url value="/assets/user/css/bootstrap.css"></c:url>' rel="stylesheet"/>
        <!-- Customize styles -->
        <link href="<c:url value="/assets/style.css"></c:url>" rel="stylesheet"/>
        <!-- font awesome styles -->
        <script src="https://kit.fontawesome.com/7baff7d167.js" crossorigin="anonymous"></script>
        <link href="<c:url value="/assets/user/font-awersome/font-awesome.css"></c:url>" rel="stylesheet"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
        <link href="<c:url value="/assets/user/font-awersome/font-awesome-ie7.min.css"></c:url>" rel="stylesheet"/>
        <link rel="shortcut icon" href="<c:url value="/assets/favicon.ico"></c:url>"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/solid.css" integrity="sha384-Tv5i09RULyHKMwX0E8wJUqSOaXlyu3SQxORObAI08iUwIalMmN5L6AvlPX2LMoSE" crossorigin="anonymous"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/fontawesome.css" integrity="sha384-jLKHWM3JRmfMU0A5x5AkjWkw/EYfGUAGagvnfryNV3F9VqM98XiIH7VBGVoxVSc7" crossorigin="anonymous"/>
        <style>
            .pagination {
                display: flex;
                justify-content: center;
            }

            .pagination a {
                color: black;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
                transition: background-color .3s;
                border: 1px solid #ddd;
            }

            .pagination a.active {
                background-color: #4CAF50;
                color: white;
                border: 1px solid #4CAF50;
            }

            .pagination a:hover:not(.active) {background-color: #ddd;}

            .strong{
                text-decoration: line-through;
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
                        <div class="well np">
                            <div id="myCarousel" class="carousel slide homCar">
                                <div class="carousel-inner">
                                    <c:forEach var="o" items="${slides}">
                                        <div class="item">
                                            <img style="width:100%" src="<c:url value="/assets/user/img/${o.img}"/>" alt="bootstrap ecommerce templates">
                                            <div class="carousel-caption">
                                                <h4>${o.caption}</h4>
                                                <p><span>${o.content}</span></p>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                                <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
                                <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
                            </div>
                        </div>
                        <!--
                        New Products
                        -->

                        <div class="well well-small">
                            <h3>New Products </h3>
                            <hr class="soften"/>
                            <div class="row-fluid">
                                <div id="newProductCar" class="carousel slide">
                                    <div class="carousel-inner">
                                        <c:if test="${newproducts.size()>0}">
                                            <div class="item active">
                                                <ul class="thumbnails">
                                                    <c:forEach var="o" items="${newproducts}" varStatus="loop">
                                                        <li class="span3">
                                                            <div class="thumbnail">
                                                                <a class="zoomTool" href="<c:url value="/detail/${o.id_productt}"/>" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
                                                                <a href="<c:url value="/detail/${o.id_productt}"/>" class="tag"></a>
                                                                <a href="<c:url value="/detail/${o.id_productt}"/>"><img src="<c:url value="/assets/user/img/${o.image}"/>" alt="bootstrap-ring"></a>
                                                            </div>
                                                        </li>
                                                        <c:if test="${(loop.index +1)%4==0||(loop.index +1)==products.size()}">
                                                        </ul>
                                                    </div> 
                                                    <c:if test="${(loop.index +1) < products.size()}">
                                                        <div class="item">
                                                            <ul class="thumbnails">
                                                            </c:if>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:if>

                                                <a class="left carousel-control" href="#newProductCar" data-slide="prev">&lsaquo;</a>
                                                <a class="right carousel-control" href="#newProductCar" data-slide="next">&rsaquo;</a>
                                        </div>
                                    </div>


                                </div>

                                <div class="well well-small">
                                    <h3><a class="btn btn-mini pull-right" href="products.html" title="View more">VIew More<span class="icon-plus"></span></a> Featured Products  </h3>
                                    <hr class="soften"/>
                                    <div class="row-fluid">
                                        <c:if test="${listPByPagination.size()>0}">
                                            <ul class="thumbnails">
                                                <c:forEach var="o" items="${listPByPagination}" varStatus="loop">
                                                    <li class="span4">
                                                        <div class="thumbnail">
                                                            <a class="zoomTool" href="<c:url value="/detail/${o.id_productt}"/>" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
                                                            <a href="<c:url value="/detail/${o.id_productt}"/>"><img src="<c:url value="/assets/user/img/${o.image}"/>" alt=""></a>
                                                            <div class="caption cntr">
                                                                <p>${o.name}</p>
                                                                <p><strong class="strong"><fmt:formatNumber type="number" groupingUsed="true" value="${o.old_price}" />đ</strong></p>
                                                                <p><strong><fmt:formatNumber type="number" groupingUsed="true" value="${o.price}" />đ</strong></p>
                                                                <h4><a class="shopBtn" href="<c:url value="/cart/${o.id_productt}/1"/>"  title="add to cart"> Add to cart </a></h4>
                                                                <div class="actionList">
                                                                    <a class="pull-left" href="#">Add to Wish List </a> 
                                                                    <a class="pull-left" href="#"> Add to Compare </a>
                                                                </div> 
                                                                <br class="clr">
                                                            </div>
                                                        </div>
                                                    </li>
                                                    <c:if test="${(loop.index +1)%3==0||(loop.index +1)==listPByPagination.size()}">
                                                    </ul>
                                                    <c:if test="${(loop.index +1) < listPByPagination.size()}">
                                                        <ul class="thumbnails">
                                                        </c:if>
                                                    </c:if>
                                                </c:forEach>

                                            </c:if>
                                    </div>
                                </div>
                                <!--
                                Featured Products
                                
                                <div class="well well-small">
                                    <h3><a class="btn btn-mini pull-right" href="products.html" title="View more">VIew More<span class="icon-plus"></span></a> Featured Products  </h3>
                                    <hr class="soften"/>
                                    <div class="row-fluid">
                                        <c:if test="${listPByPagination.size()>0}">
                                            <ul class="thumbnails">
                                                <c:forEach var="o" items="${listPByPagination}" varStatus="loop">
                                                    <li class="span4">
                                                        <div class="thumbnail">
                                                            <a class="zoomTool" href="" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
                                                            <a  href="<c:url value="/detail/${o.id_productt}"/>"><img src="<c:url value="/assets/user/img/${o.image}"/>" alt=""></a>
                                                            <div class="caption">
                                                                <h5>${o.name}</h5>
                                                                <h4>
                                                                    <a class="defaultBtn" href="<c:url value="/detail/${o.id_productt}"/>" title="Click to view"><span class="icon-zoom-in"></span></a>
                                                                    <a class="shopBtn" href="<c:url value="/cart/${o.id_productt}/1"/>" title="add to cart"><span class="icon-plus"></span></a>
                                                                    <span class="pull-right"><fmt:formatNumber type="number" groupingUsed="true" value="${o.price}" />đ</span>
                                                                </h4>
                                                            </div>
                                                        </div>
                                                    </li>
                                                    <c:if test="${(loop.index +1)%3==0||(loop.index +1)==listPByPagination.size()}">
                                                    </ul>
                                                    <c:if test="${(loop.index +1) < listPByPagination.size()}">
                                                        <ul class="thumbnails">
                                                        </c:if>
                                                    </c:if>
                                                </c:forEach>
                                            </c:if>
                                    </div>
                                    -->
                                </div>
									<button style="position: fixed;right: 20px; bottom: 20px; width: 80px; height: 30px;background-color: #f89406; color: white; border: none; display: none;" class="onTop">Go to Top</button>
                                <div class="well well-small">
                                    <a class="btn btn-mini pull-right" href="#">View more <span class="icon-plus"></span></a>
                                    Popular Products 
                                </div>
                                <hr>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="pagination">
                    <c:forEach var="o" begin="1" end="${pagination.totalPage}" varStatus="loop">
                        <c:if test="${loop.index ==pagination.currentPage}">
                            <a href="<c:url value="/getPByPaginationHome/${loop.index}"/>" class="active">${loop.index}</a>
                        </c:if>
                        <c:if test="${loop.index !=pagination.currentPage}">
                            <a href="<c:url value="/getPByPaginationHome/${loop.index}"/>">${loop.index}</a>
                        </c:if>
                    </c:forEach>
                </div>
                <%@include file="/WEB-INF/views/layouts/user/footer.jsp" %>
            </div>
        </div>
        <script src="<c:url value="/assets/user/js/jquery.js"/>"></script>
        <script src="<c:url value="/assets/user/js/bootstrap.min.js"/>"></script>
        <script src="<c:url value="/assets/user/js/jquery.easing-1.3.min.js"/>"></script>
        <script src="<c:url value="/assets/user/js/jquery.scrollTo-1.4.3.1-min.js"/>"></script>
        <script src="<c:url value="/assets/user/js/shop.js"/>"></script>
        <script type="text/javascript">
			window.addEventListener("scroll", function () {
					if (window.scrollY >= 200){
						$(".onTop").show();	
					} else {
						$(".onTop").hide();	
					}
				});

			$(".onTop").on("click", function () {
				$("html, body").animate({ scrollTop: 0 }, "slow");
				
				});
				
			function getAuthToken() {
	    	    return localStorage.getItem('token');
	    	}

			$(document).ready(function() {
			    $('#ok').on('click', function(event) {
			        event.preventDefault();
			        
			$.ajax({
			    url: 'http://localhost:8080/history',
			    type: 'GET',
			    headers: {
			        'Authorization': 'Bearer ' + localStorage.getItem('token')
			    },
			    success: function(response) {
			        console.log("Dữ liệu nhận được:", response);
			    },
			    error: function(xhr, status, error) {
			        console.error("Yêu cầu thất bại:", error);
			    }
			});

			    });
			});
        </script>

    </body>
</html>
