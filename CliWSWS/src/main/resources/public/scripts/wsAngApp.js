/**
 * http://localhost:8888/#/lisAvlWSWS
 * Help: http://mgcrea.github.io/angular-strap/#/navbars
*/
/*
 * Read : <!-- [hugeApplication Notes
		http://briantford.com/blog/huuuuuge-angular-apps 
		http://henriquat.re/modularizing-angularjs/modularizing-angular-applications/modularizing-angular-applications.html	
		hugeApplication Notes]
 /*
  * wsAngApp.js
  *  appTest =angular.module('track_wsws', ['ngRoute', 'ui.bootstrap','xConfirmModalModule']);
  *  'xConfirmModalModule', 
  *  
	$routeProvider.when('/servTest2', { 
		templateUrl: 'views/TestConfirmModal_J.html', 
        controller: 'testCtlr2'  
    });
	//servTest3 for TestModalForm3
	//
	// $routeProvider.when('/servTest3', { 
	//	templateUrl: 'views/TestModalForm3.html', 
    //    controller: 'TestModalForm3Ctlr'  
    //});
		
  */
var wsAngApp = angular.module('track_wsws', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute',
    'ui.bootstrap' ,
    'xModalForm3',
    'upLoadFileApp',
    'spinModalServiceApp',
    'paginatingAppTest',
    'testSpinModalApp',
    'spinModalFactoryApp',
    'TestAlertApp',
]);

	console.log('Loading wsAngApp Loaded Module track_wsws:' + new Date()); 
/*****			HeaderController Not Used 
// function HeaderController($scope, $location) 
//{
//	var i = 0 ;
//	resti = function(){
//		i = 0 ;
//	}
//    $scope.isActive = function (viewLocation) { 
//    	
//    	console.log("Loading wsAngApp Loaded isActive function in=" + viewLocation + " $location.path=" + $location.path() + " " + new Date() + "NUM=" + i);
//    	i++ ;
//    	if (viewLocation === $location.path())
//    		{
//    		resti() ;
//    		return true ;
//    		}
//       // return viewLocation === $location.path();
//    };
//}
//			HeaderController Not Used 
*****/
//State -- $stateProvider--  http://joelhooks.com/blog/2013/07/22/the-basics-of-using-ui-router-with-angularjs/
//http://www.sitepoint.com/creating-stateful-modals-angularjs-angular-ui-router/
//http://stackoverflow.com/questions/21023763/difference-between-angular-route-and-angular-ui-router


	
/* Configure  The $routeProvider resources   */	
wsAngApp.config(function ($routeProvider) {
	$routeProvider.when('/', {
        templateUrl: 'views/start.html',
        controller: ''
    })
    
    $routeProvider.when('/tst', {		
      templateUrl: 'views/tst.html', 
   // templateUrl: 'views/TestConfirmModal_J.html', 
        controller: 'tstCtrl'
    })
    
    $routeProvider.when('/lisAvlWSWS', {
        templateUrl: 'views/lisAvlWSWS.html',
        controller: 'lisAvlWSWSCtrl'
    })
    $routeProvider.when('/runWSWSTest', {  
        templateUrl: 'views/runWSWSTest.html',
        controller: 'runWSWSTestCtlr'
        
    })
  
	/* ref #/pickerRef */
	$routeProvider.when('/pickerRef', {   	
		templateUrl: 'views/picker.html', 
        controller: 'pickerCtlr'  
    });
	//	New App: 
	$routeProvider.when('/testUpLoadFile', { 
			templateUrl: 'views/upLoadFile.html' ,
	        controller: 'upLoadFileCtlr'  
	 });
	
	$routeProvider.when('/PaginationTesting1', { 
		templateUrl: 'views/paginationTesting1.html' ,
        controller: 'paginationTestCtlr'  
 });
	 $routeProvider.when('/unitTesting', { 
			templateUrl: 'views/UnitTesting.html',
	        controller: 'UnitTestingCtlr'  
	    });
	 /*  testSpinModal.html , testSpinModal.js ,  testSpinModalApp , testSpinModalCtlr testSpinModal() */
	 $routeProvider.when('/testSpinModal', { 
			templateUrl: 'views/testSpinModal.html',
	        controller: 'testSpinModalCtlr'  
	    });	
	//TsetAlertConfirm.html TestAlertApp TestAlertCtrl
	 $routeProvider.when('/testAlertConfirm', { 
			templateUrl: 'views/TsetAlertConfirm.html',
	        controller: 'TestAlertCtrl'  
	    });	

});
//New Config for interceptors: Not Working
/*Use spinModalFactoryApp spinFactory.js --> sto get pinFactory as dependency*/
////		module.factory('sessionRecoverer', ['$q', '$injector', function($q, $injector) {  
//angular.module('track_wsws').config(function ($provide) {
//$provide.factory('SpinHttpInterceptor', function ($q,spinFactory ) {
// *see http://www.webdeveasy.com/interceptors-in-angularjs-and-useful-examples/
// http://codingsmackdown.tv/blog/2013/01/02/using-response-interceptors-to-show-and-hide-a-loading-widget/
/*************** comOut agian 
angular.module('track_wsws').factory('SpinHttpInterceptor',['$q', '$injector', '$rootScope', function ($q,$injector,$rootScope ) { 
	var spinFactory ;
return {
	
'request': function(iHttpReq) {
	 //var spinFactory = $injector.get('spinFactory');
        ///		spinFactory.startSpin("Title1",22);   //Extract Title and time from Request
//Return the iHttpReq or wrap it in a promise if blank.
	spinFactory = spinFactory || $injector.get('spinFactory');
	
	//spinFactory.recevSpinEvents() ;
	spinFactory.publishStart();
	//$rootScope.$broadcast("StartSpinStartSpin","  ") ;
	//$rootScope.$emit("StartSpinStartSpin",' ') ;
	console.log('SpinHttpInterceptor request sent  StartSpinStartSpin>>>>>> 1<<<<<<<<<<<<<<<<<:' + new Date()); 
		return iHttpReq || $q.when(iHttpReq); 
		},
//On request failure
'requestError': function (rejection) {
    // console.log(rejection); // Contains the data about the error on the request.
    // Return the promise rejection.
	//$rootScope.$broadcast("EndSpinEndSpin") ;
	spinFactory = spinFactory || $injector.get('spinFactory');
	spinFactory.publishEnd();
	console.log('SpinHttpInterceptor request sent  StartSpinStartSpin>>>>>> 2 <<<<<<<<<<<<<<<<<:' + new Date()); 
    return $q.reject(rejection);
  },
'response': function(oHttpResp) {
	//var spinFactory = $injector.get('spinFactory');
       //			spinFactory.endSpin() ;
	spinFactory = spinFactory || $injector.get('spinFactory');
	
	//$rootScope.$broadcast("EndSpinEndSpin") ;
	spinFactory.publishEnd();
	//spinFactory = $injector.get('spinFactory');
	
	console.log('SpinHttpInterceptor response sent  EndSpinEndSpin>>>>>> 3<<<<<<<<<<<<<<<<<:' + new Date()); 
	   return oHttpResp || $q.when(oHttpResp);  //single value or promise as HttpResp return, covering err case in the same Http/Req/Resp
	   } ,
//On response failture
'responseError': function (rejection) {
    // console.log(rejection); // Contains the data about the error.
    // Return the promise rejection.
	//var spinFactory = $injector.get('spinFactory');
	//spinFactory.endSpin() ;
	//$rootScope.$broadcast("EndSpinEndSpin") ;
	console.log('SpinHttpInterceptor response sent  EndSpinEndSpin>>>>>> 4<<<<<<<<<<<<<<<<<:' + new Date()); 
	spinFactory = spinFactory || $injector.get('spinFactory');
	spinFactory.publishEnd();
    return $q.reject(rejection);
  }
}
}
]); //})
//Add the interceptor to the $httpProvider.
wsAngApp.config(function($httpProvider) {
$httpProvider.interceptors.push('SpinHttpInterceptor');

});
wsAngApp.run(function($rootScope,spinFactory)
{
	//spinFactory.init() ;
	//spinFactory.recevSpinEvents();
	//$rootScope.$on("StartSpinStartSpin"), function(event,args){
	//$log.info( "*** wsangAppXX SpinnerService--recevSpinEvents Called .."  + new Date());
	}
);*/
/*************** comOut agian ***************/
////New Config for interceptors:

/* Configure  The $$httpProvider  interceptors globally to do the Spin-Wait   
/* known problem with angluar for interceptors using http: Error: [$injector:cdep] Circular dependency found: $modal <- spinnnerService <- $http <- $compile
 * http://newscentral.exsees.com/item/caf410990410592241760080ffc333ac-e2415077e75df4f3b31ffe2df0eade97-- one artical from many found in google for the same issue
 */
/*
 wsAngApp.config(function($httpProvider) {
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
  */
/* Configure  The $$httpProvider  interceptors globally to do the Spin-Wait   */

 /* 
 var testApp2 = angular.module('test2', ['track_wsws']) ;
  
console.log('Loading wsAngApp Loaded Module test2:' + new Date());

 testApp2.config(function ($routeProvider) {
	$routeProvider.when('/servTest2', { 
		templateUrl: 'views/TestConfirmModal_J.html', 
        controller: 'testCtlr2'  
    });
});*/

angular.module("track_wsws")
.directive('bsActiveLink', ['$location', function ($location) {
return {
    restrict: 'A', //use as attribute 
    replace: false,
    link: function (scope, elem) {
        //after the route has changed
    	console.log("Runing link wsAngApp bsActiveLink .... " + new Date() + "****") ;
        scope.$on("$routeChangeSuccess", function () {
            var hrefs = ['/#' + $location.path(),
                         '#' + $location.path(), //html5: false
                         $location.path()]; //html5: true
            angular.forEach(elem.find('a'), function (a) {
                a = angular.element(a);
                if (-1 !== hrefs.indexOf(a.attr('href'))) {
                    a.parent().addClass('active');
                } else {
                    a.parent().removeClass('active');   
                };
            });     
        });
    }
}
}]);
/*
angular.module("track_wsws")
.directive('scopeForSpinner', ['spinFactory', function (spinFactory) {
    return {
        restrict: "A",
        link: function ($scope, element) {
            

        	spinFactory.recevSpinEventsX($scope);

        	//spinFactory.onRequestEnded(scope);
        }
    };
}]);
*/
		