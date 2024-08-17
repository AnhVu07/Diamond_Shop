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
        <link href="<c:url value="/assets/user/css/bootstrap.css"/>" rel="stylesheet"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="<c:url value="/assets/user/css/manager.css"/>" rel="stylesheet" type="text/css"/>

        <link href="<c:url value="/assets/user/css/style.css"/>" rel="stylesheet" type="text/css"/>
        <!-- Customize styles -->
        <link href="<c:url value="/assets/style.css"/>" rel="stylesheet"/>
        <!-- font awesome styles -->
        <link href="<c:url value="/assets/user/font-awersome/font-awesome.css"/>" rel="stylesheet">
        

        <link href="<c:url value="/assets/user/font-awersome/font-awesome-ie7.min.css"/>" rel="stylesheet">
        <link rel="shortcut icon" href="<c:url value="/assets/favicon.ico"/>">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/solid.css" integrity="sha384-Tv5i09RULyHKMwX0E8wJUqSOaXlyu3SQxORObAI08iUwIalMmN5L6AvlPX2LMoSE" crossorigin="anonymous"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/fontawesome.css" integrity="sha384-jLKHWM3JRmfMU0A5x5AkjWkw/EYfGUAGagvnfryNV3F9VqM98XiIH7VBGVoxVSc7" crossorigin="anonymous"/>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
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

            img{
                width: 200px;
                height: 120px;
            }
            body {
                color: #566787;
                background: #f5f5f5;
                font-family: 'Varela Round', sans-serif;
                font-size: 13px;
            }
            .table-wrapper {
                background: #fff;
                padding: 20px 25px;
                margin: 30px 0;
                border-radius: 3px;
                box-shadow: 0 1px 1px rgba(0,0,0,.05);
            }
            .table-title {        
                padding-bottom: 15px;
                background: #f89406;
                color: #fff;
                padding: 16px 30px;
                margin: -20px -25px 10px;
                border-radius: 3px 3px 0 0;
            }
            .table-title h2 {
                margin: 5px 0 0;
                font-size: 24px;
            }
            .table-title .btn-group {
                float: right;
            }
            .table-title .btn {
                color: #fff;
                float: right;
                font-size: 13px;
                border: none;
                min-width: 50px;
                border-radius: 2px;
                border: none;
                outline: none !important;
                margin-left: 10px;
            }
            .table-title .btn i {
                float: left;
                font-size: 21px;
                margin-right: 5px;
            }
            .table-title .btn span {
                float: left;
                margin-top: 2px;
            }
            table.table tr th, table.table tr td {
                border-color: #e9e9e9;
                padding: 12px 15px;
                vertical-align: middle;
            }
            table.table tr th:first-child {
                width: 60px;
            }
            table.table tr th:last-child {
                width: 100px;
            }
            table.table-striped tbody tr:nth-of-type(odd) {
                background-color: #fcfcfc;
            }
            table.table-striped.table-hover tbody tr:hover {
                background: #f5f5f5;
            }
            table.table th i {
                font-size: 13px;
                margin: 0 5px;
                cursor: pointer;
            }	
            table.table td:last-child i {
                opacity: 0.9;
                font-size: 22px;
                margin: 0 5px;
            }
            table.table td a {
                font-weight: bold;
                color: #566787;
                display: inline-block;
                text-decoration: none;
                outline: none !important;
            }
            table.table td a:hover {
                color: #2196F3;
            }
            table.table td a.edit {
                color: #FFC107;
            }
            table.table td a.delete {
                color: #F44336;
            }
            table.table td i {
                font-size: 19px;
            }
            table.table .avatar {
                border-radius: 50%;
                vertical-align: middle;
                margin-right: 10px;
            }
            .pagination {
                float: right;
                margin: 0 0 5px;
            }
            .pagination li a {
                border: none;
                font-size: 13px;
                min-width: 30px;
                min-height: 30px;
                color: #999;
                margin: 0 2px;
                line-height: 30px;
                border-radius: 2px !important;
                text-align: center;
                padding: 0 6px;
            }
            .pagination li a:hover {
                color: #666;
            }	
            .pagination li.active a, .pagination li.active a.page-link {
                background: #03A9F4;
            }
            .pagination li.active a:hover {        
                background: #0397d6;
            }
            .pagination li.disabled i {
                color: #ccc;
            }
            .pagination li i {
                font-size: 16px;
                padding-top: 6px
            }
            .hint-text {
                float: left;
                margin-top: 10px;
                font-size: 13px;
            }    
            /* Custom checkbox */
            .custom-checkbox {
                position: relative;
            }
            .custom-checkbox input[type="checkbox"] {    
                opacity: 0;
                position: absolute;
                margin: 5px 0 0 3px;
                z-index: 9;
            }
            .custom-checkbox label:before{
                width: 18px;
                height: 18px;
            }
            .custom-checkbox label:before {
                content: '';
                margin-right: 10px;
                display: inline-block;
                vertical-align: text-top;
                background: white;
                border: 1px solid #bbb;
                border-radius: 2px;
                box-sizing: border-box;
                z-index: 2;
            }
            .custom-checkbox input[type="checkbox"]:checked + label:after {
                content: '';
                position: absolute;
                left: 6px;
                top: 3px;
                width: 6px;
                height: 11px;
                border: solid #000;
                border-width: 0 3px 3px 0;
                transform: inherit;
                z-index: 3;
                transform: rotateZ(45deg);
            }
            .custom-checkbox input[type="checkbox"]:checked + label:before {
                border-color: #03A9F4;
                background: #03A9F4;
            }
            .custom-checkbox input[type="checkbox"]:checked + label:after {
                border-color: #fff;
            }
            .custom-checkbox input[type="checkbox"]:disabled + label:before {
                color: #b8b8b8;
                cursor: auto;
                box-shadow: none;
                background: #ddd;
            }
            /* Modal styles */
            .modal .modal-dialog {
                max-width: 400px;
            }
            .modal .modal-header, .modal .modal-body, .modal .modal-footer {
                padding: 20px 30px;
            }
            .modal .modal-content {
                border-radius: 3px;
            }
            .modal .modal-footer {
                background: #ecf0f1;
                border-radius: 0 0 3px 3px;
            }
            .modal .modal-title {
                display: inline-block;
            }
            .modal .form-control {
                border-radius: 2px;
                box-shadow: none;
                border-color: #dddddd;
            }
            .modal textarea.form-control {
                resize: vertical;
            }
            .modal .btn {
                border-radius: 2px;
                min-width: 100px;
            }	
            .modal form label {
                font-weight: normal;
            }
            .bloc_left_price {
                color: #c01508;
                text-align: center;
                font-weight: bold;
                font-size: 150%;
            }
            .category_block li:hover {
                background-color: #007bff;
            }
            .category_block li:hover a {
                color: #ffffff;
            }
            .category_block li a {
                color: #343a40;
            }
            .add_to_cart_block .price {
                color: #c01508;
                text-align: center;
                font-weight: bold;
                font-size: 200%;
                margin-bottom: 0;
            }
            .add_to_cart_block .price_discounted {
                color: #343a40;
                text-align: center;
                text-decoration: line-through;
                font-size: 140%;
            }
            .product_rassurance {
                padding: 10px;
                margin-top: 15px;
                background: #ffffff;
                border: 1px solid #6c757d;
                color: #6c757d;
            }
            .product_rassurance .list-inline {
                margin-bottom: 0;
                text-transform: uppercase;
                text-align: center;
            }
            .product_rassurance .list-inline li:hover {
                color: #343a40;
            }
            .reviews_product .fa-star {
                color: gold;
            }
            .pagination {
                margin-top: 20px;
            }
            footer {
                background: #343a40;
                padding: 40px;
                margin-top: 20px;
            }
            footer a {
                color: #f8f9fa!important
            }
            .bgc{
                background-image: url(image/Clothes+and+shoes-74_banner.jpg);
                /*background-image: url("https://envato-shoebox-0.imgix.net/a553/ba21-ce80-45ee-82d4-120907cdb414/Clothes+and+shoes-74_banner.jpg?auto=compress%2Cformat&fit=max&mark=https%3A%2F%2Felements-assets.envato.com%2Fstatic%2Fwatermark2.png&markalign=center%2Cmiddle&markalpha=18&w=1600&s=a9cc1545e602fe3d3e6c9faed39f0a84");*/
            }
            .show_txt{
                display: inline-block;
                width: 100%;
                white-space: nowrap;
                overflow: hidden !important;
                text-overflow: ellipsis;
            }
            a .active{
                color: white;
            }

            .thongbao{
                text-align: center;
            }

            .btn-success{
                background-color: #f86c06;
              
            }
            
            .btn-danger {
            background-color: #F98406;
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




        <p style="color: red" class="thongbao"></p>
        <div class="container">

            <div id="gototop"> 

                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-6">
                                <h2>Manage <b>Product</b></h2>
                            </div>
                            <div class="col-sm-6">
                                <a href="#addEmployeeModal"  class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Product</span></a>
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>
                                    <span class="custom-checkbox">
                                        <input type="checkbox" id="selectAll">
                                        <label for="selectAll"></label>
                                    </span>
                                </th>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Image</th>
                                <th>Price</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${listP}" var="o">
                                <tr>
                                    <td>
                                        <span class="custom-checkbox">
                                            <input type="checkbox" id="checkbox1" name="options[]" value="1">
                                            <label for="checkbox1"></label>
                                        </span>
                                    </td>
                                    <td>${o.idProducts}</td>
                                    <td>${o.name}</td>
                                    <td>
                                        <img src="<c:url value="/assets/user/img/${o.image}"/>">
                                    </td>
                                    <td><fmt:formatNumber type="number" groupingUsed="true" value="${o.price}" />đ</td>
                                    <td>
                                        <a href="<c:url value="/admin/load/${o.idProducts}"/>"  class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                        <a href="#deleteEmployeeModal" onclick="confirmDelete(${o.idProducts});" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <!--                <div class="clearfix">
                                        <div class="hint-text">Showing <b>1</b> out of <b>25</b> entries</div>
                                        <ul class="pagination">
                                            <li class="page-item disabled"><a href="#">Previous</a></li>
                                            <li class="page-item active"><a href="#" class="page-link">1</a></li>
                                            <li class="page-item"><a href="#" class="page-link">2</a></li>
                                            <li class="page-item "><a href="#" class="page-link">3</a></li>
                                            <li class="page-item"><a href="#" class="page-link">4</a></li>
                                            <li class="page-item"><a href="#" class="page-link">5</a></li>
                                            <li class="page-item"><a href="#" class="page-link">Next</a></li>
                                        </ul>
                                    </div>-->
                </div>
            </div>
            <!-- Add Modal HTML -->
            <div id="addEmployeeModal" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form:form action="/admin/addProduct" id="myForm" method="post" modelAttribute="product">
                            <div class="modal-header">						
                                <h4 class="modal-title">Add Product</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>Name</label>
                                    <form:input name="name" id="name" type="text" class="form-control" path="name"></form:input>
                                    <span id="nameError" class="error" style="color: red;"></span>
                                    </div>
                                    <div class="form-group">
                                        <label>Image</label>
                                    <form:input name="image" id="image" type="text" class="form-control" path="image"></form:input>
                                    <span id="imageError" class="error" style="color: red;"></span>
                                    </div>
                                    <div class="form-group">
                                        <label>Price</label>
                                    <form:input name="price" id="price" type="text" class="form-control" path="price"></form:input>
                                    <span id="priceError" class="error" style="color: red;"></span>
                                    </div>
                                    <div class="form-group">
                                        <label>Old Price</label>
                                    <form:input name="old_price" id="old_price" type="text" class="form-control" path="old_price"></form:input>
                                    <span id="old_priceError" class="error" style="color: red;"></span>
                                    </div>
                                    <div class="form-group">
                                        <label>Size</label>
                                    <form:input name="size" id="size" type="text" class="form-control" path="size"></form:input>
                                    <span id="sizeError" class="error" style="color: red;"></span>
                                    </div>
                                    <div class="form-group">
                                        <label>Sale</label>
                                    <form:input name="sale" id="sale" type="text" class="form-control" path="sale"></form:input>
                                    <span id="saleError" class="error" style="color: red;"></span>
                                    </div>
                                    <div class="form-group">
                                        <label>Highlight</label>
                                    <form:input name="highlight" id="highlight" type="text" class="form-control" path="highlight"></form:input>
                                    <span id="highlightError" class="error" style="color: red;"></span>
                                    </div>
                                    <div class="form-group">
                                        <label>New Product</label>
                                    <form:input name="newProduct" id="newProduct" type="text" class="form-control" path="newProduct"></form:input>
                                    <span id="newProductError" class="error" style="color: red;"></span>
                                    </div>
                                    <div class="form-group">
                                        <label>Title</label>
                                    <form:textarea name="title" id="title" class="form-control" path="title"></form:textarea>
                                    <span id="titleError" class="error" style="color: red;"></span>
                                    </div>
                                    <div class="form-group">
                                        <label>Description</label>
                                    <form:textarea name="description" id="description" class="form-control" path="detail"></form:textarea>
                                    <span id="descriptionError" class="error" style="color: red;"></span>
                                    </div>
                                    <div class="form-group">
                                        <label>Category</label>
                                    <form:select name="category" id="category" class="form-select" aria-label="Default select example" path="category">
                                        <c:forEach items="${categorys}" var="o">
                                            <option value="${o.idCategory}">${o.name}</option>
                                        </c:forEach>
                                    </form:select>
                                    <span id="categoryError" class="error" style="color: red;"></span>
                                </div>

                            </div>
                            <div class="modal-footer">
                                <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                <input type="submit"  class="btn btn-success" onclick="notificationAdd('Thêm sản phẩm thành công!');" value="Add">
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
            <%@include file="/WEB-INF/views/layouts/user/footer.jsp" %>
        </div>

        <!-- Delete Modal HTML -->
        <div id="deleteEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="delete" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Delete Product</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <input type="hidden" name="productId" id="productId" >
                        <div class="modal-body">					
                            <p>Are you sure you want to delete these Record?</p>
                            <p class="text-warning"><small>This action cannot be undone!</small></p>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-danger" onclick="notification('Xóa sản phẩm thành công!');" value="Delete">
                        </div>
                    </form>
                </div>
            </div>
        </div>




        <!-- /container 
        
        
        
        
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
        <script src="<c:url value="/assets/user/js/manager.js"/>" type="text/javascript"></script>
        <script type="text/javascript">
	    function confirmDelete(idProducts){
	    	$("#productId").val(idProducts);
	    }

	    function notification(message){
			alert(message);
	    }

	    function notificationAdd(message){
	    	if($("#name").val()!==""&&$("#image").val()!==""&&$("#price").val()!==""&&$("#old_price").val()!==""
                &&$("#size").val()!==""&&$("#sale").val()!==""&&$("#highlight").val()!==""&&$("#newProduct").val()!==""
                    &&$("#title").val()!==""&&$("#description").val()!==""&&$("#category").val()!==""){

					alert(message);
                }
	    }

	    $(document).ready(function() {
            $("#myForm").submit(function(event) {
                var isValid = true;
                $(".error").text("");  

                var name = $("#name").val();
                var image = $("#image").val();
                var price = $("#price").val();
                var old_price = $("#old_price").val();
                var size = $("#size").val();
                var sale = $("#sale").val();
                var highlight = $("#highlight").val();
                var newProduct = $("#newProduct").val();
                var description = $("#description").val();
                var title = $("#title").val();
                var category = $("#category").val();

                if (name.trim() === "") {
                    $("#nameError").text("Name is required.");
                    isValid = false;
                }
                if (image.trim() === "") {
                    $("#imageError").text("Image is required.");
                    isValid = false;
                }
                if (price.trim() === "") {
                    $("#priceError").text("Price is required.");
                    isValid = false;
                }
                if (old_price.trim() === "") {
                    $("#old_priceError").text("Old price is required.");
                    isValid = false;
                }
                if (size.trim() === "") {
                    $("#sizeError").text("Size is required.");
                    isValid = false;
                }
                if (sale.trim() === "") {
                    $("#saleError").text("Sale is required.");
                    isValid = false;
                }
                if (highlight.trim() === "") {
                    $("#highlightError").text("Highlight is required.");
                    isValid = false;
                }
                if (newProduct.trim() === "") {
                    $("#newProductError").text("NewProduct is required.");
                    isValid = false;
                }
                
                if (description.trim() === "") {
                    $("#descriptionError").text("Description is required.");
                    isValid = false;
                }
                if (title.trim() === "") {
                    $("#titleError").text("Title is required.");
                    isValid = false;
                }
                if (category.trim() === "") {
                    $("#categoryError").text("Category is required.");
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
