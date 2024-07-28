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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>


<!--[if lt IE 9]>
                <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
.review-form {
	max-width: 500px;
	margin: 0 auto;
}

.review-form label {
	display: block;
	margin-top: 10px;
}

.review-form textarea {
	width: 100%;
	height: 100px;
}

.review-form input[type="submit"] {
	display: block;
	margin-top: 10px;
	min-height: 34px;
	font-size: 14px;
	font-weight: bold;
	line-height: 34px;
	text-align: center;
	color: #fff;
	border-radius: 2px;
	background: #f89406;
	display: inline-block;
	padding: 0 12px;
	border: 0;
}

.reviews {
	max-width: 500px;
	margin: 20px auto;
}

.review {
	margin-bottom: 10px;
	padding: 10px;
	background-color: #e1e2dd;
}

.tabWrapper1 {
	margin-top: 2px;
}
</style>
</head>
<body>
	<%@include file="/WEB-INF/views/layouts/user/menu.jsp"%>

	<!--
        Lower Header Section 
        -->
	<div class="container">
		<div id="gototop"></div>

		<%@include file="/WEB-INF/views/layouts/user/header.jsp"%>



		<div class="row">
			<%@include file="/WEB-INF/views/layouts/user/category.jsp"%>
			<div class="span9">
				<ul class="breadcrumb">
					<li><a href="<c:url value="/"/>">Home</a> <span
						class="divider">/</span></li>
					<li><a href="">Items</a> <span class="divider">/</span></li>
					<li class="active">Preview</li>
				</ul>
				<div class="well well-small">
					<div class="row-fluid">
						<div class="span5">
							<div id="myCarousel" class="carousel slide cntr">
								<div class="carousel-inner">
									<div class="item active">
										<a href="#"><img
											src="<c:url value="/assets/user/img/${detail.image}"/>"
											alt="" style="width: 100%"></a>
									</div>
								</div>
								<a class="left carousel-control" href="#myCarousel"
									data-slide="prev">‹</a> <a class="right carousel-control"
									href="#myCarousel" data-slide="next">›</a>
							</div>
						</div>
						<div class="span7">
							<h3>${detail.name}
								[
								<fmt:formatNumber value="${detail.price}" groupingUsed="true"
									type="number" />
								đ]
							</h3>
							<hr class="soft" />

							<form class="form-horizontal qtyFrm">
								<div class="control-group">
									<label class="control-label"><span><fmt:formatNumber
												value="${detail.price}" groupingUsed="true" type="number" />đ</span></label>
									<div class="controls">
										<input type="number" class="span6" class="quatity"
											id="quatity${detail.id_productt}" min="1"
											value="1">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label"><span>Color</span></label>
									<div class="controls">
										<select class="span11">
											<option>Red</option>
											<option>Purple</option>
											<option>Pink</option>
											<option>Red</option>
										</select>
									</div>
								</div>
								<!--                                <a class="" href="<c:url value="/review"/>" data-slide="next"><h4>Đánh giá và nhận xét sản phẩm</h4></a>-->
								<p>${detail.title}
								<p>
									<button data-id="${detail.id_productt}" type="button"
										class="shopBtn addCart">
										<span class="icon-shopping-cart"></span> Add to cart
									</button>
							</form>
						</div>
					</div>
					<hr class="softn clr" />

					<ul id="productDetail" class="nav nav-tabs">
						<li class="active"><a href="#home" data-toggle="tab">Product
								Details</a></li>
						<li class=""><a href="#profile" data-toggle="tab">Related
								Products </a></li>
						
					</ul>
					<div id="myTabContent" class="tab-content tabWrapper">
						<div class="tab-pane fade active in" id="home">
							<h4>Product Information</h4>
							<table class="table table-striped">
								<tbody>
									<tr class="techSpecRow">
										<td class="techSpecTD1">Color:</td>
										<td class="techSpecTD2">Black</td>
									</tr>
									<tr class="techSpecRow">
										<td class="techSpecTD1">Style:</td>
										<td class="techSpecTD2">Apparel,Sports</td>
									</tr>
									<tr class="techSpecRow">
										<td class="techSpecTD1">Season:</td>
										<td class="techSpecTD2">spring/summer</td>
									</tr>
									<tr class="techSpecRow">
										<td class="techSpecTD1">Usage:</td>
										<td class="techSpecTD2">fitness</td>
									</tr>
									<tr class="techSpecRow">
										<td class="techSpecTD1">Sport:</td>
										<td class="techSpecTD2">122855031</td>
									</tr>
									<tr class="techSpecRow">
										<td class="techSpecTD1">Brand:</td>
										<td class="techSpecTD2">Shock Absorber</td>
									</tr>
								</tbody>
							</table>
							<p>${detail.detail}</p>

						</div>
						<div class="tab-pane fade" id="profile">
							
							<hr class="soft">
							
							 <c:if test="${listPByCategory.size()>0}">
							<c:forEach var="o" items="${listPByCategory}" varStatus="loop">
							<div class="row-fluid">
								<div class="span2">
									<img src="<c:url value="/assets/user/img/${o.image}"/>" alt="">
								</div>
								<div class="span6">
									<h5>${o.title}</h5>
									<p>${o.detail}</p>
								</div>
								<div class="span4 alignR">
									<form class="form-horizontal qtyFrm">
										<h3><strong><fmt:formatNumber type="number" value="${o.price}" groupingUsed="true"/>đ</strong></h3>
										<label class="checkbox"> <input type="checkbox">
											Adds product to compair
										</label><br>
										<div class="btn-group">
											<a href="<c:url value="/cart/${o.id_productt}/1"/>" class="defaultBtn"><span
												class=" icon-shopping-cart"></span> Add to cart</a> <a
												href="<c:url value="/detail/${o.id_productt}"/>" class="shopBtn">VIEW</a>
										</div>
									</form>
								</div>
							</div>
							<hr class="soften" />
								</c:forEach>
							</c:if>
						</div>
						
							
							<hr class="soften" />

						</div>

					</div>
					
				<div class="well well-small">
					<div class="row-fluid">
						<div id="myTabContent" class="tab-content tabWrapper">
							<div class="tab-pane fade active in" id="home">
					<div class="span9">
						<h3 style="color:#0088cc;font-size:30px;margin-top: 0px;">Review</h3>
				
						<div class="review-form">
							<h4>Đánh giá và nhận xét sản phẩm</h4>
							<form:form id="reviewForm" enctype="multipart/form-data" action="/submit_review" method="post"
								modelAttribute="product_review">
								<form:input type="text" value="${detail.id_productt}" id="id"
									style="display: none" path="product" />
								<label for="name">Họ và tên:</label>
								<form:input type="text" class="name" id="name" path="name" />
								<span id="nameError" class="error" style="color: red;"></span> 
								<label for="email">Email:</label>
								<form:input type="text" class="email" id="email" path="email" />
								<span id="emailError" class="error" style="color: red;"></span> 
								<label for="rating">Đánh giá:</label>
								<form:select id="rating" class="rating" path="evaluate">
									<option value="">-- Chọn đánh giá --</option>
									<option value="5">5 sao</option>
									<option value="4">4 sao</option>
									<option value="3">3 sao</option>
									<option value="2">2 sao</option>
									<option value="1">1 sao</option>
								</form:select>
								<span id="evaluateError" class="error" style="color: red;"></span> 
								<label for="file">Choose file:</label>
								<input name="file" type="file" class="file" id="file" >
								<span id="fileError" class="error" style="color: red;"></span> 
								<label for="comment">Nhận xét:</label>
								<form:textarea id="comment" class="comment" path="comment"></form:textarea>
								<span id="commentError" class="error" style="color: red;"></span> 
								<input type="submit" value="Gửi nhận xét">
							</form:form>
						</div>

						<div class="reviews">
							<h4>Nhận xét từ khách hàng</h4>
							<c:forEach var="o" items="${listReview}">
								<div class="review">
									<h3>Tên khách hàng:<c:out value="${o.name}"></c:out></h3>
									<p>
										Đánh giá: <c:out value="${o.evaluate}"></c:out> <span class="icon-star"
											style="color: #f89406"></span>
									</p>
									<p>
										<img src="<c:url value="/assets/user/img_review/${o.productImage}"/>" alt="ảnh">
									</p>
									<p>Nhận xét: <c:out value="${o.comment}"></c:out></p>
								</div>
							</c:forEach>
						</div>

					</div>
				</div>
			</div>
			</div>
			</div>


				
					<!--
                        Featured Products
                        -->


					<div class="well well-small">
						<a class="btn btn-mini pull-right" href="#">View more <span
							class="icon-plus"></span></a> Popular Products
					</div>
					<hr>
				</div>
			</div>
			<%@include file="/WEB-INF/views/layouts/user/footer.jsp"%>
		</div>
		<!-- /container -->


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
		<script
			src="<c:url value="/assets/user/js/jquery.easing-1.3.min.js"/>"></script>
		<script
			src="<c:url value="/assets/user/js/jquery.scrollTo-1.4.3.1-min.js"/>"></script>
		<script src="<c:url value="/assets/user/js/shop.js"/>"></script>
		<script type="text/javascript">
			$(".addCart").on("click", function() {
				var id = $(this).data("id");
				var quaty = $("#quatity" + id).val();
				if(quaty===""||quaty.indexOf(".")!==-1||quaty.indexOf("+")!==-1||quaty.indexOf("-")!==-1||isNaN(quaty)||quaty<1){
                	$(".quatity").setCustomValidity("Vui lòng nhập một số lớn hơn 0");
               	}
				else {
					window.location = "/cart/" + id + "/" + quaty;
				}
					
			});

			$(document).ready(function() {
	            $("#reviewForm").submit(function(event) {
	                var isValid = true;
	                $(".error").text("");  

	                var name = $(".name").val();
	                var email = $(".email").val();
	                var rating = $(".rating").val();
	                var comment = $(".comment").val();
	                var file = $(".file").val();

	                if (name.trim() === "") {
	                    $("#nameError").text("Name is required.");
	                    isValid = false;
	                }
	                
	                if (rating.trim() === "") {
	                    $("#evaluateError").text("Evaluate is required.");
	                    isValid = false;
	                }
	                
	                if (comment.trim() === "") {
	                    $("#commentError").text("Comment is required.");
	                    isValid = false;
	                }
	                
	                if (file.trim() === "") {
	                    $("#fileError").text("Product Image is required.");
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



			//                $('#imageFile').change(function () {
			//                    showImage(this);
			//                });
			//
			//                function showImage(fileInput) {
			//                    file = fileInput.files[0];
			//                    reader = new FileReader();
			//                    reader.onload = function (e) {
			//                        $('#img').attr('src', e.target.result);
			//                    };
			//                    reader.readAsDataURL(file);
			//                }
		</script>
</body>
</html>
