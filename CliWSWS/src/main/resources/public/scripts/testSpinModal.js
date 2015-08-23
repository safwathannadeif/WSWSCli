/*  testSpinModal.html , testSpinModal.js ,  testSpinModalApp , testSpinModalCtlr testSpinModal() 
* spinModalService.js , spinModalServiceApp, spinnnerService ,startSpin(), endSpin()  				*/ 

var testAllApp =angular.module('testSpinModalApp', ['ngRoute','spinModalServiceApp','ui.bootstrap','spinModalFactoryApp']); //['ngRoute', 'ui.bootstrap']); 
/*************** Config Global App *****************************
testAllApp.config(function($httpProvider) {
        $httpProvider.interceptors.push(function($q, spinnnerService) {
                return {
                    'request': function(iHttpReq) {
                        spinnnerService.startSpin();
                        return iHttpReq || $q.when(iHttpReq);
                    },
                    'response': function(oHttpResp) {
                        spinnnerService.endSpin() ;
						 
                        return oHttpResp || $q.when(oHttpResp);  //single value or promise as HttpResp return, covering err case in the same Http/Req/Resp
                    }
                };
            });
        });
************** Config Global App *******************************/

			
//angular.module('testSpinModalApp').controller('testSpinModalCtlr', function ($scope,$location, $log, $http, $timeout, spinnnerService,spinFactory) {
angular.module('testSpinModalApp').controller('testSpinModalCtlr', function ($scope,$location, $log, $http, $timeout, spinFactory) { 
/**** 		testHttp Gen-TestCase *****/
var rows ={} ;
var doTimeOutByCaller ;
var testHttp = function(timeoutx)
{
	//spinnnerService.startSpin("get:www.filltext.com",timeoutx);
	spinFactory.startSpin("get:www.filltext.com",timeoutx);
	$log.info("Testing $http.jsonp  www.filltext.com with Timeout:" + timeoutx.toSource()) ;
	var config = {				// configure the  request to site: www.filltext.com
                params: {
                    'rows': 1000,								//10-1000[Max]
                    'fname': '{firstName}',
                    'lname': '{lastName}',
                    'tel': '{phone|format}',
                  
//"fname": "Alfonso", "lname": "Zuniga","tel": "(502)014-7820", "address": "1332 Ac Ct", "city": "Saint Pauls", "state": "AZ", "zip": 70663 -->
                    
                    'delay' : 4,							//org 10-25 Secs[Max]
                    'callback': "JSON_CALLBACK"
                    	
                }
            };

            $http.jsonp("http://www.filltext.com", config, {})	//http://www.filltext.com testing site to fill-in Data 
                    .success(function(data) {
                       // $scope.rows = data;
						 rows = data;
				 spinFactory.endSpin();
						 $log.info("$http.jsonp Done:" + rows.toSource()) ;
                    });
}	
/**** 		testHttp Gen-TestCase *****/
$scope.testTimeout=10 ;
$scope.testHttpWithSpin = function()
	{	testHttp($scope.testTimeout) ; }

$scope.testSpinModal = function()
	{	
	spinFactory.startSpin("Testing SpinnnerService...",$scope.testTimeout);
	doTimeOutByCaller =  $timeout(function () {
		spinFactory.endSpin() ;
		// $log.info( "B Caller  will call to close..."  + new Date());
    }, 7000);
	$log.info( "B Caller execut CloseByCaller ..."  + new Date());
	};
/////
	$scope.testSpinFactory = function()
	{
		spinFactory.startSpin("Title_1111",50) ; //function(titleSpini,timeoutSpinx)
		var titleOx =spinFactory.getTitlex(); 
		$log.info( "Caller spinFactoryIns.getTitlex execut resturn=" + titleOx +  "   " +new Date());	
		var timeOx =spinFactory.getTimeoutSpinx() ; 
		$log.info( "Caller spinFactoryIns.getTitlex execut resturn=" + timeOx  + "   " +new Date());	
		
	}
/////
$scope.$on("$destroy", function( ) {$timeout.cancel( doTimeOutByCaller );  } )	//CleanUp the Event if the scope is gone

} 
) ;
  
  
   