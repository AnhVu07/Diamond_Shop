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


        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">

            var jsonData = <%= request.getAttribute("listData")%>;

            google.charts.load('current', {'packages': ['corechart']});
            google.charts.setOnLoadCallback(drawChart);

            function drawChart() {
                var data = new google.visualization.DataTable();
                data.addColumn('date', 'Thời gian');
                data.addColumn('number', 'Số đơn hàng');
                data.addColumn('number', 'Doanh thu');

                var dataArray = jsonData.map(function (item) {
                    return [new Date(item.ngay), item.orderMonth, item.revenue];
                });
                data.addRows(dataArray);

                var options = {
                    title: 'Biểu đồ đơn hàng và doanh thu',
                    curveType: 'function',
                    legend: {position: 'bottom'},
                    hAxis: {
                        format: 'dd/MM/yyyy'
                    }
                };

                var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
                chart.draw(data, options);
            }
        </script>

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
                <div class="span9">
                    <div class="row">
                        <div class="span12">
                            <ul class="breadcrumb">
                                <li><a href="<c:url value="/home"/>">Home</a> <span class="divider">/</span></li>
                                <li class="active">Manager Orders</li>
                            </ul>

                            <div class="well well-small">
                                <h1>Manager Orders<small class="pull-right"></small></h1>
                                <hr class="soften"/>	

                                <table class="table table-bordered table-condensed">
                                    <thead>
                                        <tr>
                                            <th>ID_Bill</th>
                                            <th>Display Name</th>
                                            <th>Image</th>
                                            <th>Product</th>
                                            <th>Price</th>
                                            <th>Qty</th>
                                            <th>Phone</th>
                                            <th>Note</th>
                                            <th>Address</th>
                                            <th>PDF</th>
                                            <th>Delete </th>
                                            <th>Total</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:if test="${listOrder.size() > 0}">
                                        <c:forEach var="o" items="${listOrder}">
                                            <tr>
                                                <td>${o.bill_id}</td>
                                                <td>${o.display_name}</td>
                                                <td><img width="100" src="<c:url value="/assets/user/img/${o.product_image}"/>" alt=""></td>
                                                <td>${o.product_name}</td>
                                                <td><fmt:formatNumber type="number" groupingUsed="true" value="${o.product_price}" /> đ</td>
                                                <td>${o.quantity}</td>
                                                <td>${o.phone}</td>
                                                <td>${o.note}</td>
                                                <td>${o.address}</td>
                                                <td>
                                                    <a href="<c:url value="/admin/pdf/${o.bill_id}"/>" class="btn btn-mini btn-success" type="button">
                                                        <span class="icon-edit"></span> 
                                                    </a>
                                                </td>
                                                <td>
                                                    <a href="#deleteEmployeeModal" onclick="confirmDelete(${o.bill_id});" data-toggle="modal" class="btn btn-mini btn-danger" type="button">
                                                        <span class="icon-remove"></span> 
                                                    </a>
                                                </td>
                                                <td><fmt:formatNumber type="number" groupingUsed="true" value="${o.bill_detail_total}" /> đ</td>
                                            </tr>
                                        </c:forEach>
                                        </c:if>
                                    </tbody>
                                </table><br/>

                                <div class="well-1 well-small" style="background: #a9dba9">
                                    <h2>Manager Orders Detail<small class="pull-right"></small></h2>
                                    <hr class="soften"/>	
                                    <table class="table table-bordered table-condensed">
                                        <thead>
                                            <tr>
                                                <th>Ngày</th>
                                                <th>Số đơn đặt hàng </th>
                                                <th>Tổng tiền</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="o" items="${listOrders}">
                                                <tr>
                                                    <td>${o.ngay}</td>
                                                    <td>${o.orderMonth}</td>
                                                    <td><fmt:formatNumber type="number" groupingUsed="true" value="${o.revenue}" /> đ</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table><br/>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

            </div>
            <div id="chart_div" style="width: 100%; height: 400px;">Bieu do</div>
            <%@include file="/WEB-INF/views/layouts/user/footer.jsp" %>
        </div><!-- /container -->


		<div id="deleteEmployeeModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="deleteBills" method="post">
					<div class="modal-header">
						<h4 class="modal-title">Delete Order</h4>
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
					</div>
					<input type="hidden" name="billId" id="billId">
					<div class="modal-body">
						<p>Are you sure you want to delete these Record?</p>
						<p class="text-warning">
							<small>This action cannot be undone!</small>
						</p>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal"
							value="Cancel"> <input type="submit"
							class="btn btn-danger" onclick="notification('Xóa đơn hàng thành công!');" value="Delete">
					</div>
				</form>
			</div>
		</div>
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
		<script type="text/javascript">
		function confirmDelete(billId){
			$("#billId").val(billId);
		}

		function notification(message){
			alert(message);
	    }
    </script>
    </body>
</html>
