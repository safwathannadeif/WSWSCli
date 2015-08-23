angular.module('track_wsws').controller('TestModalForm3Ctlr', function ($scope,$location, $modal, $log, $http, ModalForm3Service) {
console.log("-2 strsLis from TestModalForm3 Loaded  Completed A....") ;	
 $scope.openModalForm3 = function () {  
formInpItems = {wsName:"WebServiceN",wsType:"Synchronized", Description:"To Do emoplyee listing with Salries",wsRunMemorizedId:"",doForm:false} ; 
////////////////
/* */
  var retFormPromise  = ModalForm3Service.openByCaller(formInpItems,"Add") ; //"Add" | "Edit" | "Delete"
 retFormPromise.then(function(retItem) {
  alert('Success return retFormPromise: ' + retItem.toSource());				//-1 Success
  $log.info(">>>>>>>>>>>We can use the updated here retFormPromise: " + retItem.toSource());		
}, function(reason) {
  alert('Failed return retFormPromise: ' + reason.toSource());						//-2 Fail
}, function(notifyMsg) {
  alert('Got notification: ' + notifyMsg.toSource());							//-3 else notify progress
});
$log.info('Start Waiting--We should NOT Do any action now. action wil be resumed from retFormPromise.:' + new Date());
/* */
// we can not do it				//-1 Success

//////////////////////////
/*
ModalForm3Service.openByCaller(formInpItems) ;
*/
 
} 
////Adding Test FileUpload  $location.path('/lisAvlWSWS');
 $scope.testFileUpLoad = function () {  
	 $location.path('/testUpLoadFile'); 
 }
 $scope.testPagination = function () {  
	 $location.path('/PaginationTesting'); 
 }

 
 
}) ; 
