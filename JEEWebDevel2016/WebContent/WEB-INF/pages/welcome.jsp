<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.angularjs.org/1.5.8/angular.min.js"></script>
<script src="https://code.angularjs.org/1.5.8/angular.min.js.map"></script>
<script src="https://code.angularjs.org/1.5.8/angular-route.min.js"></script>
<script src="https://code.angularjs.org/1.5.8/angular-route.min.js.map"></script>
<link rel="stylesheet" type="text/css" media="screen" href="/resources/css/main.css" />
</head>
<body>
<h1>Welcome</h1>

	<body data-ng-app="AngularExample"> 
        <div data-ng-view>
            Loading...
        </div>
    </body>
    
    <div data-ng-controller="exampleController">
	    <p>{{message}}</p>
	    <a href="/#page-1">Page 1</a>
	    <a href="/#page-2">Page 2</a>
	</div>
	
	<div data-ng-controller="exampleController">
	    <div data-ng-repeat="item in list">
	        <h4>User:</h4>
	        <p>Fist name: {{item.firstName}}</p>
	        <p>Last name: {{item.lastName}}</p>
	    </div>    
	</div>


<script type="text/javascript">
	angular.module('AngularExample', ['ngRoute']);
	angular.module('AngularExample').controller('exampleController', function ($scope){
	    $scope.message = 'Hello world!';
	    $scope.list = [
	        {
	            firstName: 'John',
	            lastName: 'Doe'
	        },
	        {
	            firstName: 'Mark',
	            lastName: 'Smith'
	        },
	        {
	            firstName: 'James',
	            lastName: 'Mole'
	        }
	    ];
	});
	
	angular.module('AngularExample').config(['$routeProvider',
		function($routeProvider) {
		    $routeProvider
		        .when('/page-1', {
		            templateUrl: '/page-1',
		            controller: 'exampleController'
		        }, null)
		        .when('/page-2', {
		            templateUrl: 'page-2',
		            controller: 'exampleController'
		        }, null);
		}]);

</script>

</body>
</html>