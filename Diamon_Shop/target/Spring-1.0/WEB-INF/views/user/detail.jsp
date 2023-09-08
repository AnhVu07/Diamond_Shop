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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://kit.fontawesome.com/7baff7d167.js" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>


        <!--[if lt IE 9]>
                <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

        <!-- Favicons -->
        <link rel="shortcut icon" href="<c:url value="/assets/favicon.ico"/>">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/solid.css" integrity="sha384-Tv5i09RULyHKMwX0E8wJUqSOaXlyu3SQxORObAI08iUwIalMmN5L6AvlPX2LMoSE" crossorigin="anonymous"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/fontawesome.css" integrity="sha384-jLKHWM3JRmfMU0A5x5AkjWkw/EYfGUAGagvnfryNV3F9VqM98XiIH7VBGVoxVSc7" crossorigin="anonymous"/>
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

            .tabWrapper1{
                margin-top: 2px;
            }



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



            <div class="row">
                <%@include file="/WEB-INF/views/layouts/user/category.jsp" %>
                <div class="span9">
                    <ul class="breadcrumb">
                        <li><a href="<c:url value="/"/>">Home</a> <span class="divider">/</span></li>
                        <li><a href="">Items</a> <span class="divider">/</span></li>
                        <li class="active">Preview</li>
                    </ul>	
                    <div class="well well-small">
                        <div class="row-fluid">
                            <div class="span5">
                                <div id="myCarousel" class="carousel slide cntr">
                                    <div class="carousel-inner">
                                        <div class="item active">
                                            <a href="#"><img src="<c:url value="/assets/user/img/${detail.image}"/>" alt="" style="width:100%"></a>
                                        </div>
                                    </div>
                                    <a class="left carousel-control" href="#myCarousel" data-slide="prev">‹</a>
                                    <a class="right carousel-control" href="#myCarousel" data-slide="next">›</a>
                                </div>
                            </div>
                            <div class="span7">
                                <h3>${detail.name} [<fmt:formatNumber value="${detail.price}" groupingUsed="true" type="number"/>đ]</h3>
                                <hr class="soft"/>

                                <form class="form-horizontal qtyFrm">
                                    <div class="control-group">
                                        <label class="control-label"><span><fmt:formatNumber value="${detail.price}" groupingUsed="true" type="number"/>đ</span></label>
                                        <div class="controls">
                                            <input type="number" class="span6" id="quatity${detail.id_productt}" min="1" placeholder=" " value="1">
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
                                    <p>
                                        ${detail.title}
                                    <p>
                                        <button data-id = "${detail.id_productt}" type="button" class="shopBtn addCart"><span class="icon-shopping-cart"></span> Add to cart</button>
                                </form>
                            </div>
                        </div>
                        <hr class="softn clr"/>


<!--                        <ul id="productDetail" class="nav nav-tabs">
                            <li class="active"><a href="#home" data-toggle="tab">Product Details</a></li>
                            <li class=""><a href="#profile" data-toggle="tab">Related Products </a></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Acceseries <b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li><a href="#cat1" data-toggle="tab">Category one</a></li>
                                    <li><a href="#cat2" data-toggle="tab">Category two</a></li>
                                </ul>
                            </li>
                        </ul>
                        <div id="myTabContent" class="tab-content tabWrapper">
                            <div class="tab-pane fade active in" id="home">
                                <h4>Product Information</h4>
                                <table class="table table-striped">
                                    <tbody>
                                        <tr class="techSpecRow"><td class="techSpecTD1">Color:</td><td class="techSpecTD2">Black</td></tr>
                                        <tr class="techSpecRow"><td class="techSpecTD1">Style:</td><td class="techSpecTD2">Apparel,Sports</td></tr>
                                        <tr class="techSpecRow"><td class="techSpecTD1">Season:</td><td class="techSpecTD2">spring/summer</td></tr>
                                        <tr class="techSpecRow"><td class="techSpecTD1">Usage:</td><td class="techSpecTD2">fitness</td></tr>
                                        <tr class="techSpecRow"><td class="techSpecTD1">Sport:</td><td class="techSpecTD2">122855031</td></tr>
                                        <tr class="techSpecRow"><td class="techSpecTD1">Brand:</td><td class="techSpecTD2">Shock Absorber</td></tr>
                                    </tbody>
                                </table>
                                <p> ${detail.detail}</p>

                            </div>
                            <div class="tab-pane fade" id="profile">
                                <div class="row-fluid">	  
                                    <div class="span2">
                                        <img src="<c:url value="/assets/user/img/d.jpg"/>" alt="">
                                    </div>
                                    <div class="span6">
                                        <h5>Product Name </h5>
                                        <p>
                                            Nowadays the lingerie industry is one of the most successful business spheres.
                                            We always stay in touch with the latest fashion tendencies - 
                                            that is why our goods are so popular..
                                        </p>
                                    </div>
                                    <div class="span4 alignR">
                                        <form class="form-horizontal qtyFrm">
                                            <h3> $140.00</h3>
                                            <label class="checkbox">
                                                <input type="checkbox">  Adds product to compair
                                            </label><br>
                                            <div class="btn-group">
                                                <a href="product_details.html" class="defaultBtn"><span class=" icon-shopping-cart"></span> Add to cart</a>
                                                <a href="product_details.html" class="shopBtn">VIEW</a>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <hr class="soft">
                                <div class="row-fluid">	  
                                    <div class="span2">
                                        <img src="<c:url value="/assets/user/img/a.jpg"/>" alt="">
                                    </div>
                                    <div class="span6">
                                        <h5>Product Name </h5>
                                        <p>
                                            Nowadays the lingerie industry is one of the most successful business spheres.
                                            We always stay in touch with the latest fashion tendencies - 
                                            that is why our goods are so popular..
                                        </p>
                                    </div>
                                    <div class="span4 alignR">
                                        <form class="form-horizontal qtyFrm">
                                            <h3> $140.00</h3>
                                            <label class="checkbox">
                                                <input type="checkbox">  Adds product to compair
                                            </label><br>
                                            <div class="btn-group">
                                                <a href="product_details.html" class="defaultBtn"><span class=" icon-shopping-cart"></span> Add to cart</a>
                                                <a href="product_details.html" class="shopBtn">VIEW</a>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <hr class="soft"/>
                                <div class="row-fluid">	  
                                    <div class="span2">
                                        <img src="<c:url value="/assets/user/img/b.jpg"/>" alt="">
                                    </div>
                                    <div class="span6">
                                        <h5>Product Name </h5>
                                        <p>
                                            Nowadays the lingerie industry is one of the most successful business spheres.
                                            We always stay in touch with the latest fashion tendencies - 
                                            that is why our goods are so popular..
                                        </p>
                                    </div>
                                    <div class="span4 alignR">
                                        <form class="form-horizontal qtyFrm">
                                            <h3> $140.00</h3>
                                            <label class="checkbox">
                                                <input type="checkbox">  Adds product to compair
                                            </label><br>
                                            <div class="btn-group">
                                                <a href="product_details.html" class="defaultBtn"><span class=" icon-shopping-cart"></span> Add to cart</a>
                                                <a href="product_details.html" class="shopBtn">VIEW</a>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <hr class="soft"/>
                                <div class="row-fluid">	
                                    <div class="span2">
                                        <img src="<c:url value="/assets/user/img/c.jpg"/>" alt="" style="width:200px">
                                    </div>
                                    <div class="span6">
                                        <h5>Product Name </h5>
                                        <p>
                                            Nowadays the lingerie industry is one of the most successful business spheres.
                                            We always stay in touch with the latest fashion tendencies - 
                                            that is why our goods are so popular..
                                        </p>
                                    </div>
                                    <div class="span4 alignR">
                                        <form class="form-horizontal qtyFrm">
                                            <h3> $140.00</h3>
                                            <label class="checkbox">
                                                <input type="checkbox">  Adds product to compair
                                            </label><br>
                                            <div class="btn-group">
                                                <a href="product_details.html" class="defaultBtn"><span class=" icon-shopping-cart"></span> Add to cart</a>
                                                <a href="product_details.html" class="shopBtn">VIEW</a>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <hr class="soften"/>
                                <div class="row-fluid">	  
                                    <div class="span2">
                                        <img src="<c:url value="/assets/user/img/e.jpg"/>" alt="">
                                    </div>
                                    <div class="span6">
                                        <h5>Product Name </h5>
                                        <p>
                                            Nowadays the lingerie industry is one of the most successful business spheres.
                                            We always stay in touch with the latest fashion tendencies - 
                                            that is why our goods are so popular..
                                        </p>
                                    </div>
                                    <div class="span4 alignR">
                                        <form class="form-horizontal qtyFrm">
                                            <h3> $140.00</h3>
                                            <label class="checkbox">
                                                <input type="checkbox">  Adds product to compair
                                            </label><br>
                                            <div class="btn-group">
                                                <a href="product_details.html" class="defaultBtn"><span class=" icon-shopping-cart"></span> Add to cart</a>
                                                <a href="product_details.html" class="shopBtn">VIEW</a>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="cat1">
                                <p>Etsy mixtape wayfarers, ethical wes anderson tofu before they sold out mcsweeney's organic lomo retro fanny pack lo-fi farm-to-table readymade. Messenger bag gentrify pitchfork tattooed craft beer, iphone skateboard locavore carles etsy salvia banksy hoodie helvetica. DIY synth PBR banksy irony. Leggings gentrify squid 8-bit cred pitchfork. Williamsburg banh mi whatever gluten-free, carles pitchfork biodiesel fixie etsy retro mlkshk vice blog. Scenester cred you probably haven't heard of them, vinyl craft beer blog stumptown. Pitchfork sustainable tofu synth chambray yr.</p>
                                <br>
                                <br>
                                <div class="row-fluid">	  
                                    <div class="span2">
                                        <img src="<c:url value="/assets/user/img/f.jpg"/>" alt="">
                                    </div>
                                    <div class="span6">
                                        <h5>Product Name </h5>
                                        <p>
                                            Nowadays the lingerie industry is one of the most successful business spheres.
                                            We always stay in touch with the latest fashion tendencies - 
                                            that is why our goods are so popular..
                                        </p>
                                    </div>
                                    <div class="span4 alignR">
                                        <form class="form-horizontal qtyFrm">
                                            <h3> $140.00</h3>
                                            <label class="checkbox">
                                                <input type="checkbox">  Adds product to compair
                                            </label><br>
                                            <div class="btn-group">
                                                <a href="product_details.html" class="defaultBtn"><span class=" icon-shopping-cart"></span> Add to cart</a>
                                                <a href="product_details.html" class="shopBtn">VIEW</a>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <hr class="soften"/>

                                <div class="row-fluid">	  
                                    <div class="span2">
                                        <img src="<c:url value="/assets/user/img/g.jpg"/>" alt="">
                                    </div>
                                    <div class="span6">
                                        <h5>Product Name </h5>
                                        <p>
                                            Nowadays the lingerie industry is one of the most successful business spheres.
                                            We always stay in touch with the latest fashion tendencies - 
                                            that is why our goods are so popular..
                                        </p>
                                    </div>
                                    <div class="span4 alignR">
                                        <form class="form-horizontal qtyFrm">
                                            <h3> $140.00</h3>
                                            <label class="checkbox">
                                                <input type="checkbox">  Adds product to compair
                                            </label><br>
                                            <div class="btn-group">
                                                <a href="product_details.html" class="defaultBtn"><span class=" icon-shopping-cart"></span> Add to cart</a>
                                                <a href="product_details.html" class="shopBtn">VIEW</a>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <hr class="soften"/>

                            </div>-->
                        </div>


                        <div class="span9">
                            <h3>Review</h3>	
                            <hr class="soft"/>
                            <div class="review-form">
                                <h4>Đánh giá và nhận xét sản phẩm</h4>
                                <form:form id="reviewForm" action="/Spring/submit_review" method="post"  modelAttribute="product_review">
                                    <form:input type="text" value="${detail.id_productt}" id="id" style="display: none" path="idProduct" /> 
                                    <label for="name">Họ và tên:</label>    
                                    <form:input type="text" id="name" path="name" /> 
                                     <label for="email">Email:</label>
                                    <form:input type="text" name="email" id="email" path="email"/> 
                                    <label for="rating">Đánh giá:</label>
                                    <form:select id="rating" name="rating" path="evaluate">
                                        <option value="">-- Chọn đánh giá --</option>
                                        <option value="5">5 sao</option>
                                        <option value="4">4 sao</option>
                                        <option value="3">3 sao</option>
                                        <option value="2">2 sao</option>
                                        <option value="1">1 sao</option>
                                    </form:select>
                                    <label for="comment">Nhận xét:</label>
                                    <form:textarea id="comment" name="comment" path="comment"></form:textarea>
                                        <input type="submit" value="Gửi nhận xét">
                                </form:form>
                            </div>

                            <div class="reviews">
                                <h4>Nhận xét từ khách hàng</h4>
                                <c:forEach var="o" items="${listReview}">
                                    <div class="review">
                                        <h3>Tên khách hàng: ${o.name}</h3>
                                        <p>Đánh giá: ${o.evaluate} <span class="icon-star" style="color: #f89406"></span></p>
                                        <p>Nhận xét: ${o.comment}</p>
                                    </div>
                                </c:forEach>
                            </div>

                        </div>
                        <!--
                        Featured Products
                        -->


                        <div class="well well-small">
                            <a class="btn btn-mini pull-right" href="#">View more <span class="icon-plus"></span></a>
                            Popular Products 
                        </div>
                        <hr>
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
                $(".addCart").on("click", function () {
                    var id = $(this).data("id");
                    var quaty = $("#quatity" + id).val();
                    window.location = "/Spring/cart/" + id + "/" + quaty;
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
