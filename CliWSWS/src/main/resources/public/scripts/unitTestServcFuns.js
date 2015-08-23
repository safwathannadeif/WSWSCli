/*unitTestServcFuns:: app:track_wsws" ng-controller="UnitTestingCtlr */
//var testAllApp =angular.module('testSpinModalApp', ['ngRoute','spinModalServiceApp','ui.bootstrap','spinModalFactoryApp']); //['ngRoute', 'ui.bootstrap'])
//var workApp =angular.module('track_wsws',['spinModalFactoryApp'] );
//workApp.service('unitTestServcFuns' ,function($log, $http,spinFactory) {   //'spinModalFactoryApp'
	angular.module('track_wsws').service('unitTestServcFuns',function($log, $http,spinFactory,$q,$rootScope,$timeout) {   

this.loadData = function() {
arData=[];
var doit = false ;
for (var i=0; i<1000; i++) {
	var rec ={name:"name"+i,value:"value"+i,doit:doit,price:i*100} ;
	arData.push(rec);
    dotit=!doit ;
}
return arData ;
}
/////////// spinJob
this.testSpin3= function(funcTocall,title,timeout)
{
	return(spinFactory.spinJob(funcTocall,title,timeout)) ;
}
///\\\
this.testSpin2= function(funcTocall)
{
	spinFactory.startSpin("XXXXX",29);
	var deferred = $q.defer();
	$rootScope.$on('itmXOutToSpanner',function(event,data){ 
		deferred.reject("timeOutHappend") ;	
		$log.info("testSpin2  deferred.reject--timeOutHappend ---" + new Date()) ;
	});
		
	$log.info("testSpin2 before calling funcTocall() **********" + new Date()) ;
	var promise  = funcTocall() ;
	promise.then(function(dataA) { 
	     deferred.resolve(dataA);
	     spinFactory.endSpin() ;
	  }),(function(reason) {
		  deferred.reject(reason);
		  spinFactory.endSpin() ;
	  }) 
		
	return deferred.promise ;
	//return deferred  ; 
}
//

this.tsetHttp1= function()
{
///spinnnerService.startSpin("get:www.filltext.com",timeoutx);
var deferred = $q.defer();	 
var timeoutx = 15 ;	
	spinFactory.startSpin("get:www.filltext.com",timeoutx);
	
$log.info("Testing $http.jsonp  www.filltext.com with Timeout:" + timeoutx.toSource()) ;
var config = {				// configure the  request to site: www.filltext.com
            params: {
                'rows': 100,								//10-1000[Max]
                'fname': '{firstName}',
                'lname': '{lastName}',
                'tel': '{phone|format}',
              
//"fname": "Alfonso", "lname": "Zuniga","tel": "(502)014-7820", "address": "1332 Ac Ct", "city": "Saint Pauls", "state": "AZ", "zip": 70663 -->
                
                'delay' : 20,							//org 10-25 Secs[Max]
                'callback': "JSON_CALLBACK"
                	
            }
        };
//var deferred = $q.defer();
$rootScope.$on('itmXOutToSpanner',function(event,data){ 	
	 deferred.reject("SpinnerTimeOut");
	 return deferred.promise;
});
$http.jsonp("http://www.filltext.com", config, {})
  .success(function(data) { 
     deferred.resolve(data);
     spinFactory.endSpin();
  }).error(function(reason) {
	  deferred.reject(reason);
	  spinFactory.endSpin();
  });

return deferred.promise;

/*$http.jsonp("http://www.filltext.com", config, {})	//http://www.filltext.com testing site to fill-in Data 
.success(function(data) {
   // $scope.rows = data;
	 rows = data;
spinFactory.endSpin();
	 $log.info("$http.jsonp Done:" + rows.toSource()) ;
	 return rows;
});
*/
};})
	


