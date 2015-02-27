(function(){

var storeModule = angular.module('storeApp', ['ngAnimate']);

storeModule.controller('PanelController', function ($scope, $http) {
	this.tab=1;
	
	this.selectTab = function(tabNr){
		this.tab=tabNr;
	};
	
	this.isSelected = function(tabNr){
		return this.tab === tabNr;
	};
	
});

storeModule.controller('StoreController', function ($scope, $http) {
 
 var urlBase="";
 $scope.toggle=true;
 $scope.selection = [];
 $scope.statuses=['ACTIVE','COMPLETED'];
 $scope.priorities=['HIGH','LOW','MEDIUM'];
 $http.defaults.headers.post["Content-Type"] = "application/json";

 //add a new task
 $scope.addTask = function addTask() {
	if ($scope.taskName == "" || $scope.taskDesc == ""
			|| $scope.taskPriority == ""
			|| $scope.taskStatus == "") {
		alert("Insufficient Data! Please provide values for task name, description, priortiy and status");
	} else {
		var task = {
			taskName : $scope.taskName,
			taskDescription : $scope.taskDesc,
			taskPriority : $scope.taskPriority,
			taskStatus : $scope.taskStatus,
			taskEstimate : 12
		};
		tasks.push(task);
		$scope.tasks = tasks;
		console.log('add task: ' + tasks);
	}
};
 
 var tasks = [
 {
	 taskName: 'Angular POC',
	 taskDesc: 'Angular POC',
	 taskStatus: 'PREPARING',
	 taskPriority: 'HIGH',
	 taskEstimate: 10
 },              
 {
	 taskName: 'Angular POC',
	 taskDesc: 'Angular POC',
	 taskStatus: 'PREPARING',
	 taskPriority: 'HIGH',
	 taskEstimate: 55
 }              
 ];
 
  this.tasks = tasks;

 
});

storeModule.directive('ngConfirmClick', [
 function(){
         return {
             link: function (scope, element, attr) {
                 var msg = attr.ngConfirmClick || "Are you sure?";
                 var clickAction = attr.confirmedClick;
                 element.bind('click',function (event) {
                     if ( window.confirm(msg) ) {
                         scope.$eval(clickAction);
                     }
                 });
             }
         };
 }]);

})() 
