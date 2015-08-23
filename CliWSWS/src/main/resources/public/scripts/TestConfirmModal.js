
/*
 * TestConfirmModal.js    testConfirmModalModule
 * 'track_wsws
 */
////var appTest = angular.module('testConfirmModalModule', ['ngRoute', 'ui.bootstrap','xConfirmModalModule']);
/*
 wsAngApp =angular.module('track_wsws', ['ngRoute', 'ui.bootstrap','xConfirmModalModule']);

 /*appTest.config(function ($routeProvider) {
 $routeProvider.when('/tst', {
       	templateUrl: 'views/TestConfirmModal_J.html', 
        controller: 'tstCtrlxxxx'
 })
 }  angular.module('track_wsws').controller('tstCtrl', function ($scope, $http) {
 );
 */

angular.module('track_wsws').controller('testCtlr2', function ($scope, $modal, $log, $http, ConfirmService) {
console.log("-2 strsLis from TestConfirmModal Completed A....") ;
///wsAngApp.controller('tstCtrl', function ($scope, $modal, $log, $http, ConfirmService) {
	console.log("-2 strsLis from TestConfirmModal Completed ***....") ;
	$http.get('/wsws/servTest2').success(function (data) {  
        $scope.strsLis = data; 
        console.log("-2 strsLis from TestConfirmModal Completed********....") ;
    }).error(function (data, status) {
        console.log('Error ' + data)
    })

 var targetItem ={"name":"xxx-yyy","value":72181102} ;
 var confirmModel = {"header":"Delete Confirmation","msg":"Please Confrim?","yes":false, targetItem};
 $log.info('Loading TestConfirmModel Load2:' + new Date());
 $scope.openConfirm = function () {  
$log.info('Start openConfirm From TestConfirNodal ....:' + new Date());
var confirmPromise  = ConfirmService.openByCaller(confirmModel);
 confirmPromise.then(function(confirmModel) {
  alert('Success return confirmModel: ' + confirmModel.toSource());				//-1 Success
}, function(reason) {
  alert('Failed return confirmMode: ' + reason.toSource());						//-2 Fail
}, function(notifyMsg) {
  alert('Got notification: ' + notifyMsg.toSource());							//-3 else notify progress
});
$log.info('Start Waiting--We should NOT Do any action now. action wil be resumed from confirmPromise.:' + new Date());
}
  $scope.toggleAnimation = function () {
    $scope.animationsEnabled = !$scope.animationsEnabled;
  };

}
);

/*
* see : https://docs.angularjs.org/api/ng/service/$q for right resources for defeerd and promised synch
* How it works : openByCaller make the call and the callback executed  by the confirmPromise.
* the callback from : var deferred = $q.defer() , deferred.resolve() | deferred.reject() | notify() at the end deferred.promise and retun to exec confirmPromise	
* 
*/
