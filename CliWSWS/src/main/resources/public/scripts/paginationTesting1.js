/**
 * paginatingApp Testing Pagaination Name: paginationTesting.js
 * var app=angular.module('paginatingAppTest', []);
app.controller('paginationTestCtlr', function ($scope) {

angular.module('paginatingAppTest', [])
.controller('paginationTestCtlr', function ($scope) {
 */	
	var app=angular.module('paginatingAppTest', []);
	app.controller('paginationTestCtlr', function ($scope) {
    $scope.currentPage = 0;
    $scope.pageSize = 10;
    $scope.data1 = [];
    $scope.numberOfPages=function(){
        return Math.ceil($scope.data1.length/$scope.pageSize);                
    }
   var spanSpace = '   ****    ' ; 
    for (var i=0; i<45; i++) {
    	
        $scope.data1.push("Name "+i + spanSpace+ " Value " + i);
    }
});
//Make the Filter
//We already have a limitTo filter built-in to angular,
//let's make a startFrom filter
app.filter('startFrom1', function() {
    return function(input1, start1) {
        start1 = +start1; //parse to int
        return input1.slice(start1);
    }
});