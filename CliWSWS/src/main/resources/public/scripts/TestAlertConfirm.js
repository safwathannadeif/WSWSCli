// TestAlertConfirm.js TsetAlertConfirm.html TestAlertApp TestAlertCtrl
var TestAlertApp =angular.module('TestAlertApp', ['ui.bootstrap']); //['ngRoute', 'ui.bootstrap']); 
angular.module('TestAlertApp').controller('TestAlertCtrl', function ($scope,$log) { 

 $scope.testAlert = function() {
 $log.info("testAlert Cliked") ;
 $scope.alertMsgs = [
		                   //{ type: 'danger', msg: 'Oh snap! Change a few things up and try submitting again.' },
		                   //{ type: 'success', msg: 'Well done! You successfully read this important alert message.' }
		             ];

		                // $scope.addAlert = function() {
		                    $scope.alertMsgs.push({msg: 'warning Color alert!',type:'warning'}); //.push({msg: 'warning  alert!'});
						    $scope.alertMsgs.push({msg: 'info Color alert!',type:'info'}); 
							$scope.alertMsgs.push({msg: 'danger Color alert!',type:'danger'}); 
							$scope.alertMsgs.push({msg: 'success Color alert!',type:'success'}); 
						   //$scope.alertMsgs={} ; //purge the alert model to close the alert
		               //  };
} ;
$scope.closeAlert = function(index) {
		                $log.info("closeAlert  Cliked") ;
		               // $scope.alertMsgs.push( {msg:"xxxx"} ) ;
					   $scope.alertMsgs={} ; //purge the alert model to close the alert
} ;

$scope.OK = function() {
$log.info("OK Cliked") ;
$scope.alertMsgs={} ; //purge the alert model to close the alert
} ;

$scope.Cancel = function() {
$log.info("Cancel Cliked") ;
$scope.alertMsgs={} ; //purge the alert model to close the alert
} ;

})
/*
var modalInstance = $modal.open({
    	 backdrop: 'static',
         keyboard: true,
         modalFade: true,
      templateUrl: 'myModalFormContent.html',   //By Id "myModalFormContent.html"
      controller: 'ModalInstanceCtrl3',
      size: "sm",
      resolve: {
        formItemsFunc: function () {
          return retOutUpdItem;
        }
      }
    });
    
*/