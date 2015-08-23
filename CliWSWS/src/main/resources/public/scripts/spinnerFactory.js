//spinnerFactory.js spinModalFactoryApp spinModalFactoryApp
var spinModalFactoryApp =angular.module('spinModalFactoryApp', ['ui.bootstrap']) ;

spinModalFactoryApp.factory('spinFactory' ,function ($q,$modal,$log,$timeout,$rootScope, $interval,$injector) { 
	var spinFactoryIns ={} ;
	spinFactoryIns.timeoutSpinx = 20 ;
	spinFactoryIns.titleSpinx = " "  ;
	var modalOpend=false ;	
	var timeOutE = false ;
	var modalInsFactory ;
	var clocker ;
	///\\\
	spinFactoryIns.spinJob= function(funcTocall,title,timeout)
	{
		spinFactoryIns.startSpin(title,timeout);
		var deferred = $q.defer();
		$rootScope.$on('itmXOutToSpanner',function(event,data){ 
			deferred.reject("timeOutHappend") ;	
			$log.info("testSpin2  deferred.reject--timeOutHappend ---" + new Date()) ;
		});
			
		$log.info("testSpin2 before calling funcTocall() **********" + new Date()) ;
		var promise  = funcTocall() ;
		promise.then(function(dataA) { 
		     deferred.resolve(dataA);
		     spinFactoryIns.endSpin() ;
		  }),(function(reason) {
			  deferred.reject(reason);
			  spinFactoryIns.endSpin() ;
		  }) 
			
		return deferred.promise ;
	}
	///\\\
	spinFactoryIns.init = function()
	{
		$log.info( "***  spinFactoryIns--init  ..."   + new Date());
	}
	spinFactoryIns.recevSpinEventsX = function($scope)
		{
		$log.info( "*** RECV/Directv SpinnerService--recevSpinEvents Called .."  + new Date());
		
		$scope.$on("StartSpinStartSpin"), function(event,args){
				$log.info( "*** DIRECTVONONON   SpinnerService--recevSpinEvents StartSpinStartSpin ..." +event.toSource()  + new Date());
				spinFactoryIns.startSpin("tset1",8);
			}
		//$scope.$on("EndSpinEndSpin"), function(event,args){
		//		$log.info( "*** DIRECTV SpinnerService--recevSpinEvents EndSpinEndSpin ..."   + new Date());
		//		spinFactoryIns.endSpin();
		//	}
			
		} ;
////\\\\
		spinFactoryIns.publishStart = function()
		{
			
			$rootScope.$broadcast("StartSpinStartSpin"," " ) ; 
			 $log.info( "*** YY spinFactoryIns --publishStart Done FOR StartSpinStartSpin ..."  + new Date());
			 /*var deferred = $q.defer();
			 this.startSpin("tset1",8);
			 deferred.promise.then(function(result) {
		           return result ;
		        }, function(error) {
		        	 return error ;
		        });*/
		/*	 $rootScope.$on("StartSpinStartSpin"), function(event,args){
					$log.info( "*** XX SpinnerService--recevSpinEvents StartSpinStartSpin ..." +event.toSource()  + new Date());
					spinFactoryIns.startSpin("tset1",8);
				}; 
		*/
		} ;
		spinFactoryIns.publishEnd = function()
		{
			$rootScope.$broadcast('EndSpinEndSpin',' ') ; 
			 $log.info( "*** YY spinFactoryIns --publishEnd Done ... EndSpinEndSpin"  + new Date());
			/* $rootScope.$on("EndSpinEndSpin"), function(event,args){
					$log.info( "*** XX SpinnerService--recevSpinEvents EndSpinEndSpin ..."   + new Date());
					spinFactoryIns.endSpin();
			 };
			 */
		}
	 
////\\\\
	spinFactoryIns.startSpin = function(titleSpini,timeoutSpinx)
	{
		spinFactoryIns.titleSpinx = titleSpini  ;
		spinFactoryIns.timeoutSpinx = timeoutSpinx*1000 ;	//convert to MiSeconds
		//			timeOutInp = timeoutSpinx*1000;
		$log.info( "1  lSpinnerService--startSpin..."   + new Date());
		$log.info( "2  lSpinnerService--startSpin--spinnnerServ Started ..."  + new Date());
		     modalInsFactory = $modal.open( {
   	 backdrop: 'static',			//to Block background
		      templateUrl: 'ShowBlockSpinner.html',    //by-Id ShowBlockSpinner.html from html:ShowBlockSpinner.html
		      controller: ModalSpinnerFactoryInscCtlr,
		      resolve: {
		       whatIsRunFunc: function () {
		          //return spinFactoryIns.getTitlex();
		          return spinFactoryIns ;
		      }
		        } });
		   modalOpend=true ;	
		   doTimeOut =  $timeout(function () {
			//  alert("tImeOut Occured") ;
		  //    modalInsFactory.close('closing');
			  modalOpend=false ;
			  timeOutE=true ;
			  $rootScope.$broadcast('itmXOutToSpanner',' ') ; 
			//  $rootScope.$emit('itmXOutToSpannerXXXX',' ') ;
			  $log.info( "3  lSpinnerService--startSpin--spinnnerServ/$broadcast TimeOut-Occured** ..."  + new Date());
			  
		    }, spinFactoryIns.getTimeoutSpinx());

		   modalInsFactory.result
		   .then( function done() {
			   $log.info( " modalInsFactory.result done  ..."  + new Date());
		   })
		   .catch( function notDone(err) {
			   $log.info( " modalInsFactory.result notDone RRROR!  ..."+ err  + " " +new Date());
		   })
		   .finally( function noMatter(){
			   $log.info( " modalInsFactory.result noMatter "+ new Date());
			   $timeout.cancel(doTimeOut); 
			   modalOpend=false ;	
		   } );
		   
		}  ;
		
////\\\\
	spinFactoryIns.getTitlex = function()
	{
		$log.info( "spinFactoryIns.getTitlex ...Called return="+ spinFactoryIns.titleSpinx  + "   " +new Date());	
		return spinFactoryIns.titleSpinx  ;
	}
	spinFactoryIns.getTimeoutSpinx = function()
	{
		$log.info( "spinFactoryIns.getTimeoutSpinx ...Called return="+ spinFactoryIns.timeoutSpinx + "   " +new Date());	
		return spinFactoryIns.timeoutSpinx  ;
	}
	
	spinFactoryIns.endSpin = function() 
	{
		$log.info( "************************************************* spinFactoryIns.endSpin Called ..."  + new Date());	
		$log.info( "4  lSpinnerService--OpenByCaller--spinnnerServ endSpin ..."  + new Date());
		 //if it Happend check the instance
		 if ( !modalOpend  ) {
		 $log.info( "5  lSpinnerService--OpenByCaller--spinnnerServ endSpin, Modal Alreday Closedby itself... return...."  + new Date());
		 return ;
		 }
		 $timeout.cancel(doTimeOut);
		 $interval.cancel(clocker) ;
		modalInsFactory.close('closingByCaller') ;
		return ;
	}
	spinFactoryIns.getTimeOutE= function() { return timeOutE};
	
	return spinFactoryIns ;
	
} ) ;
var ModalSpinnerFactoryInscCtlr = function ($scope, $modalInstance,$interval ,whatIsRunFunc,$log) {
	//$log.info( "ModalSpinnerFactoryInscCtlr input .whatIsRunFunc = " + whatIsRunFunc.toSource() );	
	$scope.runningWhat= whatIsRunFunc.getTitlex() ;
	 $scope.ok = function () {
	    $modalInstance.close("OkClosed");
	  };

	  $scope.cancel = function () {
	    $modalInstance.dismiss("Cancled");
	  };
	//////
	  var countup = 0; 
	  var alertMsgs = [] ;
	  $scope.alertMsgs = alertMsgs ;
	  var totTimeOut = whatIsRunFunc.getTimeoutSpinx()/1000 ;
	  $scope.disply=countup+"/"+totTimeOut ;
	  
	  $scope.getTimeOutE = false ; //timeOutE
	  
	    
	  clocker = $interval ( function(){
		  countup++ ;
		  $scope.disply=countup+"/"+totTimeOut ; //$scope.$apply()
		  $log.info( "ModalSpinnerFactoryInscCtlr $scope.getTimeOutE value = " + $scope.getTimeOutE ) ;
	  	}
	  ,1000, totTimeOut+1); //every 100Msecs Refresh the display
	  //////
	  $scope.$on('itmXOutToSpanner',function(event,data){  
		  /////
		  $scope.alertMsgs.push({msg: 'TimeOut Occured',type:'warning'}); //Active the alert for Time Out
		  $scope.getTimeOutE = true;
	  });
	   $scope.closeAlert = function(index) {
		                  // $scope.alertMsgs.splice(index, 1);
		                	 $scope.alertMsgs = {} ;		//Clear the Alert and Close it by Empty the alertMsgs array 
		                	 
		                	 $modalInstance.dismiss("Cancled");
		                 };
		$scope.$on('$destroy', function() {
		                     // Make sure that the interval is destroyed too
			$interval.cancel(clocker) ;
		});
	  } ;
/*
	$scope.alertMsgs.push({msg: 'warning Another alert!',type:'warning'}); 
    $scope.alertMsgs.push({msg: 'info Another alert!',type:'info'}); 
	$scope.alertMsgs.push({msg: 'danger Another alert!',type:'danger'}); 
	$scope.alertMsgs.push({msg: 'success Another alert!',type:'success'}); 
*/