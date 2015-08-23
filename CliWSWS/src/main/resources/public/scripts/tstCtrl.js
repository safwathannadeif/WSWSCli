/**
 * tstCtrl.js
*/
angular.module('track_wsws').controller('tstCtrl', function ($scope, $http) {
    $http.get('/wsws/tst').success(function (data) {  
        $scope.strsLis = data; 
        console.log("-1 strsLis from  tstCtrl Completed....") ;
    }).error(function (data, status) {
        console.log('Error ' + data)
    });
});

