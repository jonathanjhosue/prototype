/**
 * 
 */

 var app = angular.module('myApp',['ngGrid']);
 
 
 
 app.controller('AccountCtrl', function ($scope, $http) {
    	
    	 var baseURL="http://localhost:8080/prototype-web/rest/accountWs/";
    	 
    	 $scope.UrlService= baseURL;
    	 //$scope.resultData.stateAccounts={};
    	 /*$scope.myData = [{name: "Moroni", age: 50},
    	                  {name: "Tiancum", age: 43},
    	                  {name: "Jacob", age: 27},
    	                  {name: "Nephi", age: 29},
    	                  {name: "Enos", age: 34}];
    	$scope.gridOptions = { data: 'myData' };*/
    	//$scope.selectedAccount={};
    	 
    	 $scope.selectedAccount = [];
    	
    	$scope.searchAccount = function () {
    	
 	        // Delete from Grid
 	        /*var index = this.row.rowIndex;
 	
 	        $scope.gridOptions.selectItem(index, false);
 	        $scope.users.splice(index, 1);*/
    		
    		$scope.UrlService=baseURL+$scope.accountForm.entity+'/'+$scope.accountForm.account+'/json';
 	        // Server side
 	     $http.get($scope.UrlService,{}).
 	        success(function (data) {
 	        	
 	        	$scope.resultData = data; 
 	        	
 	        });
    	}
         
         $scope.getResponse =function (type) {   
        	 
         	$scope.UrlService=baseURL+$scope.accountForm.entity+'/'+$scope.accountForm.account+'/'+type;
         	
         	
         	$("#formAccount").attr("action", $scope.UrlService);        	
         	$( "#formAccount" ).submit();
         	
         };
         
         
     
         $scope.gridOptions = {
                 data: 'resultData.stateAccounts',
                 selectedItems: $scope.selectedAccount,
                 enableRowSelection: true,
                 enableCellEditOnFocus: true,
                 multiSelect: false,
                 columnDefs: [{
                     field: 'accountNumber',
                     displayName: 'Número de Cuenta',
                     enableCellEdit: false
                 }, {
                     field: 'date',
                     displayName: 'Fecha',
                     cellFilter: 'date:\'yyyy-MM-dd\'',
                     enableCellEdit: false
                 }, {
                     field: 'currentBalance',
                     displayName: 'Saldo Actual',
                     enableCellEdit: false
                 },{
                     field: 'lastBalance',
                     displayName: 'Saldo Anterior',
                     enableCellEdit: false
                 },{
                     field: 'state',
                     displayName: 'Estado',
                     enableCellEdit: false
                 }/*,{
                     field: '',
                     displayName: 'Save',
                     enableCellEdit: false,
                     cellTemplate: '<button id="editBtn" type="button"  ng-click="saveItem(row.entity.name, row.entity.surname,row.entity.address)" >Save</button>'
                 }, {
                     field: '',
                     displayName: 'Delete',
                     enableCellEdit: false,
                     cellTemplate: '<button id="editBtn" type="button"  ng-click="removeRow(row.entity.name, row.entity.surname)" >Delete</button>'
                 }*/]
      
             };
         
         
         $scope.gridOptionsDetails = {
                 data: 'selectedAccount[0].movements',               
                 enableRowSelection: true,
                 enableCellEditOnFocus: true,
                 multiSelect: false,
                 columnDefs: [{
                     field: 'id',
                     displayName: 'Identificador',
                     enableCellEdit: false
                 },{
                     field: 'date',
                     displayName: 'date',
                     cellFilter: 'date:\'yyyy-MM-dd\'',
                     enableCellEdit: false
                 },{
                     field: 'description',
                     displayName: 'Description',
                     enableCellEdit: false
                 }, {
                     field: 'type',
                     displayName: 'Tipo',
                     enableCellEdit: false
                 },{
                     field: 'exchange.currencyFrom',
                     displayName: 'Moneda Origen',
                     enableCellEdit: false
                 },{
                     field: 'exchange.currencyTo',
                     displayName: 'Moneda Destino',
                     enableCellEdit: false
                 },{
                     field: 'value',
                     displayName: 'Valor',
                     enableCellEdit: false
                 }/*,{
                     field: '',
                     displayName: 'Save',
                     enableCellEdit: false,
                     cellTemplate: '<button id="editBtn" type="button"  ng-click="saveItem(row.entity.name, row.entity.surname,row.entity.address)" >Save</button>'
                 }, {
                     field: '',
                     displayName: 'Delete',
                     enableCellEdit: false,
                     cellTemplate: '<button id="editBtn" type="button"  ng-click="removeRow(row.entity.name, row.entity.surname)" >Delete</button>'
                 }*/]
      
             };
         
 });