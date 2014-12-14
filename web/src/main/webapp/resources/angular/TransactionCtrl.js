/**
 * 
 */

 var app = angular.module('myApp',['ngGrid']);
 
 
 
 app.controller('TransactionCtrl', function ($scope, $http) {
    	
    	 var baseURL="http://localhost:8080/prototype-web/rest/authorizerWsRest/authorize/";
    	 
    	 
    	 //$scope.UrlService= baseURL;
    	 //$scope.resultData.stateAccounts={};
    	 /*$scope.myData = [{name: "Moroni", age: 50},
    	                  {name: "Tiancum", age: 43},
    	                  {name: "Jacob", age: 27},
    	                  {name: "Nephi", age: 29},
    	                  {name: "Enos", age: 34}];
    	$scope.gridOptions = { data: 'myData' };*/
    	//$scope.selectedAccount={};
    	 
    	 $scope.responseData =[];    	 
    	 
    	
    	$scope.authorize = function (type) {
    	
 	        // Delete from Grid
 	        /*var index = this.row.rowIndex;
 	
 	        $scope.gridOptions.selectItem(index, false);
 	        $scope.users.splice(index, 1);*/
    		
    	$scope.UrlService=baseURL+type;
    		//alert( $scope.UrlService);
 	        // Server side
 	     $http(
 	    		{
 	    		    url: $scope.UrlService,
 	    		    method: 'POST', 	 
 	    		   data: $scope.transactionForm, 	    		  
 	    		    headers: { 'Content-Type': 'application/json;charset=utf-8' },
 	    		    //timeout: 1,
 	    		    //cache: false,
 	    		    //transformRequest: false,
 	    		    //transformResponse: false
 	    		}		 
 	     ).success(function (data, status, headers, config) {
 	    	// this callback will be called asynchronously
 	        // when the response is available
 	        	$scope.responseData = data; 
 	        	
 	     }).error(function(data, status, headers, config) {
 	        // called asynchronously if an error occurs
 	        // or server returns response with an error status.
 	    	 alert("Error, status: "+status+", data: "+data);
 	    	 
 	      });
    }
         
        /* $scope.getResponse =function (type) {   
        	 
         	$scope.UrlService=baseURL+type;
         	
         	
         	$("#transactionForm").attr("action", $scope.UrlService);        	
         	$( "#transactionForm" ).submit();
         	
         };*/
         
         
 });