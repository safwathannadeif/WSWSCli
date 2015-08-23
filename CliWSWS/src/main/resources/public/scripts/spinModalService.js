/* using Factory with Intercepter http://codingsmackdown.tv/blog/2013/04/20/using-response-interceptors-to-show-and-hide-a-loading-widget-redux/ */
	
/* spinModalService.js , spinModalServiceApp, spinnnerService ,startSpin(), endSpin()  */
var spinModalServiceApp =angular.module('spinModalServiceApp', ['ui.bootstrap']) ;
 var modalInstance ;
 var doTimeOut ;
 var modalOpend = false ;
 /** Added to test**   */

/* spinModalServiceApp.directive('modalWindow', function(){
   return {
     restrict: 'EA',
     link: function(scope, element) {
       console.log(element);
     }
   }  
 });
 */
 /** Added to test**   */
 
spinModalServiceApp.service('spinnnerService', function ($q,$modal,$log,$timeout) { 
this.startSpin = function (runWaht,timeOutInp) {			//timeoutinp in secs
	timeOutInp = timeOutInp*1000;
$log.info( "1  lSpinnerService--startSpin..."   + new Date());
$log.info( "2  lSpinnerService--startSpin--spinnnerServ Started ..."  + new Date());
     modalInstance = $modal.open( {
      templateUrl: 'ShowBlockSpinner.html',    //by-Id ShowBlockSpinner.html from html:ShowBlockSpinner.html
      controller: ModalSpinnerInstanceCtlr,
      resolve: {
       whatIsRunFunc: function () {
          return runWaht ;
      }
        } });
   modalOpend=true ;	
   doTimeOut =  $timeout(function (timeOutInp) {
      modalInstance.close('closing');
	  modalOpend=false ;
	  $log.info( "3  lSpinnerService--startSpin--spinnnerServ TimeOut ..."  + new Date());
    }, timeOutInp);

   modalInstance.result
   .then( function done() {
	   $log.info( " modalInstance.result done  ..."  + new Date());
   })
   .catch( function notDone(err) {
	   $log.info( " modalInstance.result notDone RRROR!  ..."+ err  + " " +new Date());
   })
   .finally( function noMatter(){
	   $log.info( " modalInstance.result noMatter "+ new Date());
	   $timeout.cancel(doTimeOut); 
	   modalOpend=false ;	
   } );
   
}  ;
//
this.endSpin = function () {
 $log.info( "4  lSpinnerService--OpenByCaller--spinnnerServ endSpin ..."  + new Date());
 //if it Happend check the instance
 if ( !modalOpend  ) {
 $log.info( "5  lSpinnerService--OpenByCaller--spinnnerServ endSpin, Modal Alreday Closedby itself... return...."  + new Date());
 return ;
 }
 $timeout.cancel(doTimeOut);
modalInstance.close('closingByCaller');

}
} );
 //
var ModalSpinnerInstanceCtlr = function ($scope, $modalInstance,whatIsRunFunc) {

$scope.runningWhat= whatIsRunFunc ;
 $scope.ok = function () {
    $modalInstance.close("OkClosed");
  };

  $scope.cancel = function () {
    $modalInstance.dismiss("Cancled");
  };
  
//////
  $scope.countup = 0;    
  setInterval(function(){$scope.countup++; $scope.$apply()},1000); 
  //////
  
};
