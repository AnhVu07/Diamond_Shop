

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Edit</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="<c:url value="/assets/user/css/manager.css"/>" rel="stylesheet" type="text/css"/>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="<c:url value="/assets/user/css/style.css"/>" rel="stylesheet" type="text/css"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <style>
            .img{
                width: 200px;
                height: 120px;
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
            /*            table.table td a.edit {
                            color: #FFC107;
                        }*/
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
            .col-sm-6{
                text-align: center;
            }

            .btn-success{
                background-color: #f89406;
            }
        </style>
    </head>
    <body>


        <div id="editEmployeeModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form:form action="/admin/editProduct" id="myForm" method="post" modelAttribute="product">
                        <div class="modal-header">						
                            <h4 class="modal-title">Edit Product</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <form:input value="${detail.idProducts}" name="id" type="text" class="form-control" style="display: none" path="idProducts"></form:input>
                                </div>
                                <div class="form-group">
                                    <label>Name</label>
                                <form:input value="${detail.name}" id="name" name="name" type="text" class="form-control" path="name"></form:input>
                                <span id="nameError" class="error" style="color: red;"></span>
                                </div>
                                <div class="form-group">
                                    <label>Image</label>
                                <form:input value="${detail.image}" id="image" name="image" type="text" class="form-control" path="image"></form:input>
                                <span id="imageError" class="error" style="color: red;"></span>
                                </div>
                                <div class="form-group">
                                    <label>Price</label>
                                <form:input value="${detail.price}" id="price" name="price" type="text" class="form-control" path="price"></form:input>
                                <span id="priceError" class="error" style="color: red;"></span>
                                </div>
                                <div class="form-group">
                                    <label>Old Price</label>
                                <form:input value="${detail.old_price}" id="old_price" name="old_price" type="text" class="form-control" path="old_price"></form:input>
                                <span id="old_priceError" class="error" style="color: red;"></span>
                                </div>
                                <div class="form-group">
                                    <label>Size</label>
                                <form:input value="${detail.size}" id="size" name="size" type="text" class="form-control" path="size"></form:input>
                                <span id="sizeError" class="error" style="color: red;"></span>
                                </div>
                                <div class="form-group">
                                    <label>Sale</label>
                                <form:input value="${detail.sale}" id="sale" name="sale" type="text" class="form-control" path="sale"></form:input>
                                <span id="saleError" class="error" style="color: red;"></span>
                                </div>
                                <div class="form-group">
                                    <label>Highlight</label>
                                <form:input value="${detail.highlight}" id="highlight" name="highlight" type="text" class="form-control" path="highlight"></form:input>
                                <span id="highlightError" class="error" style="color: red;"></span>
                                </div>
                                <div class="form-group">
                                    <label>New Product</label>
                                <form:input value="${detail.newProduct}" id="newProduct" name="newProduct" type="text" class="form-control" path="newProduct"></form:input>
                                <span id="newProductError" class="error" style="color: red;"></span>
                                </div>
                                <div class="form-group">
                                    <label>Title</label>
                                <form:textarea name="title" id="title" path="title" value="${detail.title}" class="form-control"></form:textarea>
                                <span id="titleError" class="error" style="color: red;"></span>
                                </div>
                                <div class="form-group">
                                    <label>Description</label>
                                <form:textarea name="description" id="description" value="${detail.detail}" class="form-control" path="detail"></form:textarea>
                                <span id="descriptionError" class="error" style="color: red;"></span>
                                </div>
                                <div class="form-group">    
                                    <label>Category</label>
                                <form:select name="category" id="category" class="form-select" value="${detail.category.idCategory}" aria-label="Default select example" path="category">
                                        <option value="${detail.category.idCategory}">${detail.category.name}</option>
                                    <c:forEach items="${categorys}" var="o">
                                        <option value="${o.idCategory}">${o.name}</option>
                                    </c:forEach>
                                </form:select>
                                <span id="categoryError" class="error" style="color: red;"></span>
                            </div>

                        </div>
                        <div class="modal-footer">
                            <a href="<c:url value="/admin/returnAdmin"/>" class="cancel">
                                <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            </a>
                            <input type="submit" class="btn btn-success" onclick="notification('Cập nhật sản phẩm thành công!');" value="Edit">
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
        <script type="text/javascript">
        function notification(message){
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
