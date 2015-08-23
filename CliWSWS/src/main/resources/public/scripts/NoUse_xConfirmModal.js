/* xConfirmModal.js
/* <!- this xConfirmModal.js and  file xConfirmModalView.htm are the main reusable component for Yes/No Confirm -->
/*
 * Pure Modal return function() {
    var instance = $modal.open({
      templateUrl: 'views/loginModalTemplate.html',
      controller: 'ModalCtrl',
      controllerAs: 'ModalCtrl'
    })
*/
/* Async note;
* see : https://docs.angularjs.org/api/ng/service/$q for right resources for defeerd and promised synch
* How it works : openByCaller make the call and the callback executed by the confirmPromise.
* the callback from : var deferred = $q.defer() , deferred.resolve() | deferred.reject() | notify() at the end deferred.promise and retun to exec confirmPromise	
* xConfirmModalModule
*
* ConfirmService define  API function "openByCaller" to connect to to the Modal 
*/

var app = angular.module('xConfirmModalModule', ['ngRoute','ui.bootstrap']); //myModuleForModal  

app.service('ConfirmService', function ($modal, $http,$q, $log) {
$log.info('Modal xConfirmModalModule for .openByCaller LOADED for ConfirmService ..........') ;
 this.openByCaller = function (targetItemx) {
$log.info('Modal xConfirmModalModule.openByCaller  targetItemx from input: ' +  targetItemx.toSource() );
 this.confirmModel= targetItemx ;
 var deferred = $q.defer();
 this.deferred = deferred ;  //transmit the deferred with ConfirmService just for testing the Alert progress ONLY 
 $log.info('Modal xConfirmModalModule.openByCaller confirmModel post upd  ConfirmService2: ' +  this.confirmModel.toSource() );
  
  var  modalServcInsc = $modal.open({
      animation: true ,
      templateUrl: 'xConfirmModalContent.html',		//by id
      controller: 'ModalInstanceCtrl',
      size: "sm",
      resolve: {
        confirmModel: function () {
          return this.confirmModel;
		
        }
      }
    });
    modalServcInsc.result.then(function (confirmModel) {
	  $log.info('Modal xConfirmModalModule Confirmed Return : ' + new Date());
	  $log.info('Modal xConfirmModalModule Return  YES* confirmModel=[' +  confirmModel.toSource() +']' );
	  deferred.resolve(confirmModel) ;
	  //return confirmModel ;
	  
    }, function (confirmModel) {
      $log.info('Modal xConfirmModalModule dismissed at: ' + new Date());
	  $log.info('Modal xConfirmModalModule Return  NO* confirmModel=[' +  confirmModel.toSource() +']' );
	  deferred.reject = (confirmModel) ;
	  //return confirmModel ;
	 
    })
		return deferred.promise;		//back to the caller
} } );


// Please note that $modalInstance represents a modal window (instance) dependency.
// It is not the same as the $modal service used above.

 angular.module('ui.bootstrap').controller('ModalInstanceCtrl',  function ($scope, $modalInstance,$log,ConfirmService) {
 $log.info('Modal xConfirmModalModule.ModalInstanceCtrl confirmModel from ConfirmService2 inp: ' +   ConfirmService.confirmModel.toSource() );
  $scope.confirmModel = {} ; 
  $scope.confirmModel = ConfirmService.confirmModel; //ToDisaply The Inp Msg //app.value("confirmModelInp", targetItemx) ;
  $log.info('Modal xConfirmModalModule.ModalInstanceCtrl confirmModel from scope: ' +   $scope.confirmModel.toSource() );

  $scope.ok = function () {
    ////$modalInstance.close($scope.confirmModel={yes:true}) ;
    //////$modalInstance.close($scope.confirmModel.yes=true) ;
    ConfirmService.confirmModel.yes=true  ;
	$modalInstance.close(ConfirmService.confirmModel);
	ConfirmService.deferred.notify("Ok Cliked ....");
	$log.info('Modal xConfirmModalModule.ModalInstanceCtrl confirmModel Updated from scope: ' +   $scope.confirmModel.toSource() );
  };

  $scope.cancel = function () {
   // $scope.confirmModel={yes:false} ;
	////				$modalInstance.close($scope.confirmModel={yes:false}) ;
	ConfirmService.deferred.notify("Cancel  Cliked ....");
	/////$modalInstance.close($scope.confirmModel={yes:false}) ;
	/////$modalInstance.close($scope.confirmModel.yes=false) ;
	ConfirmService.confirmModel.yes=false  ;
	$modalInstance.close(ConfirmService.confirmModel);
	$log.info('Modal xConfirmModalModule.ModalInstanceCtrl confirmModel Updated from scope: ' +   $scope.confirmModel.toSource() );
  /////			 $modalInstance.dismiss('cancel');
  } } );

