var storeModule = angular.module('storeApp', ['ngAnimate']);

storeModule.controller('storeController', function ($scope, $http) {
 
 var urlBase="";
 $scope.toggle=true;
 $scope.selection = [];
 $scope.statuses=['ACTIVE','COMPLETED'];
 $scope.priorities=['HIGH','LOW','MEDIUM'];
 $http.defaults.headers.post["Content-Type"] = "application/json";

 

 //add a new task
 $scope.addTask = function addTask() {
  console.log('add task');
 };
 
 
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
