angular.module('track_wsws').controller('UnitTestingCtlr', function ($scope,$location, $modal, $log,$q, $http,$timeout, ModalForm3Service,unitTestServcFuns) {
console.log("**** UnitTestingCtlr*** strsLis from TestModalForm3 Loaded  Completed A....") ;	


$scope.actView="testAll" ; /* testAll testTable Default or tablePagination2		views/paginationTesting2.html*/
console.log("**** UnitTestingCtlr*** $scope.actView=" + $scope.actView) ;	

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
} 
////Adding Test FileUpload  $location.path('/lisAvlWSWS');
 $scope.testFileUpLoad = function () {  
	 $location.path('/testUpLoadFile'); 
 }
 $scope.testPagination1 = function () {  
	 $location.path('/PaginationTesting1'); 
 }
 /*  testSpinModal.html , testSpinModal.js ,  testSpinModalApp , testSpinModalCtlr testSpinModal() */
 $scope.testSpinModal = function () {  
	 $location.path('/testSpinModal'); 
 }
 /*//TsetAlertConfirm.html TestAlertApp TestAlertCtrl when '/testAlertConfirm' */
 $scope.testAlertConfirm = function () {  
	 $location.path('/testAlertConfirm'); 
 };


//// Another Conroller: Pagination2Ctlr
////angular.module('track_wsws').controller('Pagination2Ctlr', function ($scope,$location,  $log) {
$scope.tablePagination22 = function () {  /* tablePagination2 */
$log.info('Start tablePagination2 from UnitTesting Contolerer ' + new Date());
$scope.actView="tablePagination2" ; 
}

$scope.paginationSpinner = function() {  
	$scope.actView="paginationSpinner" ; //paginationSpinner tablePagination2 testAll paginationTesting2
	$log.info(">>>>>>>>>>>paginationSpinner ActView = " +  " scope Changed -- $scope.actView=" + $scope.actView) ;
	
}


$scope.refreshUnitTest = function(actViewi) {
	$scope.actView= actViewi ; 
	$scope.actView="testAll" ; //paginationSpinner tablePagination2 testAll
	$log.info(">>>>>>>>>>>refreshUnitTest IncomingInput = " + actViewi + " scope Changed " + $scope.actView) ;
}
//////////////	Pagination Functions
$scope.PagSpinloadStatuse="loadNotDone";
$scope.testSpinnerPagination = function () { //testSpinnerPagination()
	$log.info("testSpinnerPagination Start ............");

	$scope.dataAry=unitTestServcFuns.loadData() ;
	$scope.PagSpinloadStatuse="loadDone";
	$log.info("testSpinnerPagination Done Data length $scope.dataAry=" + $scope.dataAry.length ) ;
}
//tsetHttp
$scope.PagSpinloadStatuse="loadNotDone";
$scope.testHttpSpinnerPagination = function () { //testSpinnerPagination()
	//$scope.tsetHttp = function() {
	 var promise =  unitTestServcFuns.tsetHttp1() ;
	 promise.then(function(data) {
		 $scope.dataAry=data ;
		 $scope.PagSpinloadStatuse="HttploadDone";		
		 $log.info("unitTestServcFuns.tsetHttp() the length $scope.dataAry=" + $scope.dataAry.length ) ;
		}, function(reason) {
			 $log.info('Failed: ' + reason);
		});
	 
}
////////////
$scope.PagSpinloadStatuse="loadNotDone";
$scope.doTestspin2 = function () { 
	$log.info("testSpinnerPagination Start ............");
	var funcArgumnet = funcArgumnet2 ;
	//var promise =  unitTestServcFuns.testSpin2(funcArgumnet) ;
	var promise = unitTestServcFuns.testSpin3(funcArgumnet,"HttpLoad",17) ; //DataLoad
	promise.then(function(data2){
		 $scope.dataAry=data2 ;
		 $log.info("testSpinnerPagination Done Data length $scope.dataAry=" + $scope.dataAry.length ) ;
		 $log.info('data: ' + data2.toSource());
		//$scope.PagSpinloadStatuse="loadDone";		//loadDone with funcArgumnet = funcArgumnet1	title= DataLoadTest1 
		$scope.PagSpinloadStatuse="HttploadDone";		//HttploadDone with funcArgumnet = funcArgumnet2 title= HTTPLoadTest2
		
	},function(reason2){
		$log.info('Failed2: ' + reason2);
	})
	
	
}
//
var funcArgumnet2 = function() { 
	var deferred = $q.defer();	 
	var config = {				// configure the  request to site: www.filltext.com
	            params: {
	                'rows': 100,								//10-1000[Max]
	                'fname': '{firstName}',
	                'lname': '{lastName}',
	                'tel': '{phone|format}',
	                'address' : '{address}',
	                'state' : '{state}' ,
	                'city'  : '{city}',
	                'zip':'{zip}',
	//"fname": "Alfonso", "lname": "Zuniga","tel": "(502)014-7820", "address": "1332 Ac Ct", "city": "Saint Pauls", "state": "AZ", "zip": 70663 -->
	                
	                'delay' : 5,							//org 10-25 Secs[Max]
	                'callback': "JSON_CALLBACK"
	                	
	            }
	        };
	
	$http.jsonp("http://www.filltext.com", config, {})
	  .success(function(data) { 
	     deferred.resolve(data);
	  }).error(function(reason) {
		  deferred.reject(reason);
		 
	  });

	return deferred.promise;
	//return deferred ;

	}
//////
var funcArgumnet1 = function() {
	
	var doit = false ;
	var arData2 = [] ;
	var deferred = $q.defer();
	for (var i=0; i<1000; i++) {
		var rec ={name:"name"+i,value:"value"+i,doit:doit,price:i*100} ;
		arData2.push(rec);
	    dotit=!doit ;
	}
	// arData2 = null ;
   		doTimeWait =  $timeout(function () {  			 
	    $log.info( "Just wait as dely3....................."  + new Date());
	    if (arData2 != null )deferred.resolve(arData2);
	    if (arData2 == null )deferred.reject("no-data");
	    }, 8*1000);

	//return arData ;
	   return deferred.promise;
	  // return deferred ;
	}
///////////
$scope.numberOfPagesForDataAry=function(){
    return Math.ceil($scope.dataAry.length/$scope.pageSize);                
}
;
$scope.currentPage = 0;
$scope.pageSize = 20;
//////////////Pagination Functions
$scope.tablePagination2 = function () { 
	$scope.data2=loadStaticData();
	console.log("-2 UnitTestingCtlr*** tablePagination2 $scope.data2.length =" + $scope.data2.length ) ;
	$scope.numberOfPages=function(){
	    return Math.ceil($scope.data2.length/$scope.pageSize);                
	}
	$scope.actView="tablePagination2"
		console.log("-2 UnitTestingCtlr*** tablePagination2 invoked and set the scope to  tablePagination2.............") ;
	// $location.path('/unitTesting'); 
		
}


}) ; 
//*****   generic Filter for Pagination *****/
angular.module('track_wsws').filter('startFrom2', function() {
return function(input2, start2) {
    start2 = +start2; //
  
    return input2.slice(start2);
}
//*****   generic Filter for Pagination *****/
});
loadStaticData = function() {
arrayOfData=[];
var doit = false ;
for (var i=0; i<1000; i++) {
	var rec ={name:"name"+i,value:"value"+i,doit:doit,price:i*100} ;
    //$scope.data2.push(rec);
	arrayOfData.push(rec);
    dotit=!doit ;
}
return arrayOfData ;
}
//Make the Filter*****

/*Back directive:
angular.module('track_wsws').directive('backButton', function(){
    return {
      restrict: 'A',
 
      link: function(scope, element, attrs) {
        element.bind('click', goBack);
 
        function goBack() {
          history.back();
          scope.$apply();
        }
      }
    }
});
*/
////			});
