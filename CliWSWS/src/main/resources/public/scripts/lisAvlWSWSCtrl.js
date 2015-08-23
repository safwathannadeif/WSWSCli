/**
 * lisAvlWSWSCtrl.js
 */
angular.module('track_wsws').controller('lisAvlWSWSCtrl' ,  function ($scope, $http,$location) 
{
    $http.get('/wsws/lisAvlWSWS').success(function (data) {  
        $scope.wswsAvlLis = data; 
    }).error(function (data, status) {
        console.log('Error ' + data)
    });
    
});