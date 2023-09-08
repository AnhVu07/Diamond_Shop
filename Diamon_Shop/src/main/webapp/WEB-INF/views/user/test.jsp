<%-- 
    Document   : test
    Created on : Jun 4, 2023, 1:19:34 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="my_app" ng-init="page = ['Home', 'Management', 'User']">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

        <!-- Popper JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body ng-controller="myController">

        <!--        <div ng-controller="controllerOne">
                    {{name}}
                </div>
                <div ng-controller="controllerTwo">
                    {{name}}
                </div>-->
        <div>
            {{name}}
        </div>
        <form name="myForm">
            <div>
                <input ng-model="input" name="input" type="password" size="50" required="">{{myForm.input.$valid?'':'*khong đc de trong'}}
            </div>
        </form>
        <ul class="breadcrumb">
            <li ng-repeat="pa in page">{{pa}}</li>
        </ul>


        <input type="checkbox" ng-model="hu" value="hide/show">Show/Hide


        <!--        <form name="myForm1">
                    <div>
                        <input ng-model="searching" name="input" type="password" size="50" required="">
                    </div>
                </form>-->
        <div class="container-fluid">
            <div class="panel panel-primary">
                <br><br>

                <div class="panel-heading">User Management</div>
                <div class="panel-body">
                    <table class="table table-hover">
                        <thead>
                            <tr >
                                <td ng-repeat="a in data">{{a}}</td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr ng-repeat="student in dataList|filter:searching">
                                <td >{{student.No}}</td>
                                <td >{{student.Fullname|uppercase}}</td>
                                <td >{{student.Email|lowercase}}</td>
                                <td >{{student.Age}}</td>
                                <td>{{student.Phone}}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="panel panel-primary">
                <div class="panel-heading">Add User </div>
                <div class="panel-body">
                    <input placeholder="Name" size="50" type="text" name="name" ng-model="name">
                    <br><br>
                    <input placeholder="Email" size="50" type="text" name="email" ng-model="email">
                    <br><br>
                    <input placeholder="Age" size="50" type="number" name="age" ng-model="age">
                    <br><br>
                    <input placeholder="Phone" size="50" type="text" name="phone" ng-model="phone">
                    <br><br>
                    <input type="button" ng-click="addUser()"value="Add">
                    <input type="button" value="Sort">
                    <input type="button" value="Reset">
                </div>
            </div>
        </div>
        <table 
            <ul ng-repeat="ci in city" ng-show="hu" >
                <li>{{ci}}</li>
            </ul>
            <form>
                <input ng-model="content" type="text" size="200">
            </form>
            <div><h1>{{content}}</h1></div>
            <div><p>{{price|currency:"VNĐ"}}-Today:{{today| date:"dd/MM/yyyy"}}</p></div>

            <script type="text/javascript">

                        var app = angular.module("my_app", []);
                        app.factory('myFactory', function () {
                            return {'name': 'Anh Vu nè',
                                'age': 21,
                                showAlert: function () {
                                    alert('Anh Vu 123');
                                }
                            };
                        });
//                        app.controller('controllerOne', ['$scope', 'data', function ($scope, data) {
//                            $scope.dataList = new Array();
//                            $scope.content = " Chào Anh Vũ";
//                            $scope.price = 2000;
//                            $scope.today = new Date();
//                            $scope.data = ['STT', 'Ho ten', 'Email', 'Age', 'Phone'];
//                            $scope.addUser = function () {
//                                var student = {
//                                    "No": this.dataList.length + 1,
//                                    "Fullname": this.name,
//                                    "Email": this.email,
//                                    "Age": this.age,
//                                    "Phone": this.phone
//                                };
//                                this.dataList[this.dataList.length] = student;
//                        $scope.name = data.name;
//                        }]);
                        app.controller('myController', ['$scope', 'myFactory', function ($scope, myFactory) {
                                $scope.name = myFactory.name;
                                myFactory.showAlert();
                            }]);
            </script>
    </body>
</html>
