/**
 * 
 */
 var app = angular.module('myApp',[]);
 /*
 $("#dialogCode").dialog({
     autoOpen: false,
     modal: true,
     height: 600,
     open: function(ev, ui){
              
           }
	});*/
 
 app.controller('ClientCtrl', function ($scope, $http) {
	 
	 var baseURL="http://localhost:8080/prototype-web/rest/clientWs/";
	 
	 $scope.UrlService= baseURL;
 
       /* $http.get('http://localhost:8080/rest-angular/rest/service').
        success(function (data) {
            $scope.users = data;
        });
        */
        $scope.searchClient = function () {
        	
	        // Delete from Grid
	       /* var index = this.row.rowIndex;
	
	        $scope.gridOptions.selectItem(index, false);
	        $scope.users.splice(index, 1);*/
	        
        	/*$scope.UrlService=baseURL+'search/json';
	        // Server side
	        $http.post($scope.UrlService, {
	        	entity: entity,
	        	client: client
	        }).
	        success(function (data) {
	        	$scope.clientData = data;
	            //alert('delete completed!');
	        });*/
	        
	    	$scope.UrlService=baseURL+'search/json';
	        $http(
	 	    		{
	 	    		    url: $scope.UrlService,
	 	    		    method: 'POST', 	 
	 	    		   data: $.param({ "entity": $scope.formClient.entity, "client": $scope.formClient.client }), 	    		  
	 	    		    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
	 	    		    //timeout: 1,
	 	    		    //cache: false,
	 	    		    //transformRequest: false,
	 	    		    //transformResponse: false
	 	    		}		 
	 	     ).success(function (data, status, headers, config) {
	 	    	// this callback will be called asynchronously
	 	        // when the response is available
	 	    	$scope.clientData = data;
	 	        	
	 	     }).error(function(data, status, headers, config) {
	 	        // called asynchronously if an error occurs
	 	        // or server returns response with an error status.
	 	    	 alert("Error, status: "+status+", data: "+data);
	 	    	 
	 	      });
	        
	        
        };
        
        $scope.getClientResponse =function (type) {        
        	$scope.UrlService=baseURL+'search/'+type
        	$("#formClient").attr("action", $scope.UrlService);        	
        	$( "#formClient" ).submit();
        };


        
 /*
        $scope.removeRow = function (name, surname) {;
            // Delete from Grid
            var index = this.row.rowIndex;
 
            $scope.gridOptions.selectItem(index, false);
            $scope.users.splice(index, 1);
            
 
            // Server side
            $http.post('http://localhost:8080/rest-angular/rest/service/delete', {
                name: name,
                surname: surname
            }).
            success(function (data) {
                alert('delete completed!');
            });
        };
 
        $scope.saveItem = function (name, surname, address) {
            $http.post('http://localhost:8080/rest-angular/rest/service/save', {
                name: name,
                surname: surname,
                address: address
            }).
            success(function (data) {
                alert('update completed!');
            });
        }
         
        $scope.addRow = function () {
 
            $scope.hidden = true;
 
        };
         
        $scope.insertRow = function () {
 
            // Update ng-grid
            $scope.users.push({
                name: $scope.myForm.name,
                surname: $scope.myForm.surname,
                address: $scope.myForm.address
            });
            $scope.hidden = false;
 
           // Persist on database
            $http.put('http://localhost:8080/rest-angular/rest/service', {
                name: $scope.myForm.name,
                surname: $scope.myForm.surname,
                address: $scope.myForm.address
            }).
            success(function (data) {
                alert('insert completed!');
            });
 
 
        };
        
        $scope.gridOptions = {
            data: 'users',
            enableRowSelection: false,
            enableCellEditOnFocus: true,
            multiSelect: false,
            columnDefs: [{
                field: 'name',
                displayName: 'name',
                enableCellEdit: false
            }, {
                field: 'surname',
                displayName: 'surname',
                enableCellEdit: false
            }, {
                field: 'address',
                displayName: 'address',
                enableCellEdit: true
            }, {
                field: '',
                displayName: 'Save',
                enableCellEdit: false,
                cellTemplate: '<button id="editBtn" type="button"  ng-click="saveItem(row.entity.name, row.entity.surname,row.entity.address)" >Save</button>'
            }, {
                field: '',
                displayName: 'Delete',
                enableCellEdit: false,
                cellTemplate: '<button id="editBtn" type="button"  ng-click="removeRow(row.entity.name, row.entity.surname)" >Delete</button>'
            }]
 
        };
 */
    });