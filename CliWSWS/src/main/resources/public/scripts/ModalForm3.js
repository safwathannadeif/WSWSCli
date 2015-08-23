var app =angular.module('xModalForm3', ['ngRoute', 'ui.bootstrap']); 
app.service('ModalForm3Service', function ($modal, $http,$q, $log) { 
/*
 *  formInpItem Input Model to the Modal : 
 *  ({wsId:4, wsName:"makeOneASyncrEmpWS", doIt:true, processMode:"Asynchronous", description:"....",  *   doForm:false, runId:""})
 *  retOutUpdItem for Edit Add : the same just  update for the wsRunMemorizedId
 */
 
 this.openByCaller = function (formInpItem,WorkType) {
 var  retOutUpdItem = angular.copy(formInpItem); //use this copy for out 	
 retOutUpdItem.title=WorkType ;
 /* Use Ternary operators: */ 
 alert('Before Semi-Ternary  WorkType = ' + WorkType.toSource());	
// {{WorkType === "Add" ? "Edit" : "WorkType"}}
 if (WorkType === "Add") WorkType="Edit" ;
 if (WorkType === "edit") 
 alert('After  Semi-Ternary  WorkType = ' + WorkType.toSource());
 retOutUpdItem.form3WorkType=WorkType ; //form3WorkType = "Add" | "Edit" | "Delete" but Add converted to Edit

$log.info('Modal ModalForm3Service.openByCaller  formInpItem inp to open Modal : ' +  formInpItem.toSource() );
 //When Opend Init 
  var deferred = $q.defer();
    var modalInstance = $modal.open({
   	 backdrop: 'static',
      templateUrl: 'myModalFormContent.html',   //By Id "myModalFormContent.html"
      controller: 'ModalInstanceCtrl3',
      size: "sm",
      resolve: {
        formItemsFunc: function () {
          return retOutUpdItem;
        }
      }
    });

  ///-- modalInstance.result.then(function (updtedFormItems) {
	 modalInstance.result.then(function () {
      $log.info('Save/Cancel Modal Return : ' + new Date());
	 ///-- $log.info('Save/Cancel Modal ReturnItem updtedFormItems: ' +  updtedFormItems.toSource() );
	////  retOutUpdItem=updtedFormItems ;
	  $log.info('Save/Cancel Modal Return init formInpItem: ' +  formInpItem.toSource() );
	  $log.info('Save/Cancel Modal Updated retOutUpdItem: ' +  retOutUpdItem.toSource() );
	  deferred.resolve(retOutUpdItem) ;
    }, function (reason) { //Error
	  $log.info('Error Modal Return : ' + new Date());
      alert('Failed return confirmMode: ' + reason.toSource());	
	   deferred.reject = (reason)
	  
    });
	return deferred.promise;		//back to the caller
  };

 

});
 

// Please note that $modalInstance represents a modal window (instance) dependency.
// It is not the same as the $modal service used above.

angular.module('ui.bootstrap').controller('ModalInstanceCtrl3', function ($scope, $modalInstance, formItemsFunc) {

  $scope.formItems = formItemsFunc;
  $scope.save= function () {
    $scope.formItems.doForm=true ;
  ///--   $modalInstance.close($scope.formItems);
  $modalInstance.close();
  };
  $scope.cancel= function () {
     $scope.formItems.doForm=false ;
  ///--   $modalInstance.close($scope.formItems);
    $modalInstance.close();
  };
});