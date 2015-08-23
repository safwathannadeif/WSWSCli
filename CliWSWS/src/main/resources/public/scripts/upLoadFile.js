//Modal Opend event  :	http://stackoverflow.com/questions/19999501/angularui-bootstrap-modal-open-event
var apFileUpLoad =angular.module('upLoadFileApp', []) ;
var winId=0 ;
var OpenWindow  ;

apFileUpLoad.controller('upLoadFileCtlr',  function($scope,$window){
	    $scope.actView="" ;
	    
	    $scope.resetFile = function(){   
		    $scope.actView="" ;   
		   // console.log("resetFile Done Without Applu ...") ;
		   // $scope.$apply() ;
	    };
	   
	    $scope.$on("fileSelected", function (event, args) { 
	    	//console.log("Event   fileSelected " + args.name ) ;
	    	$scope.theFile=args ;
	    	$scope.actView="fileView" ;
	    });	
	  
	    $scope.readAndDisply = function()
        {
        	readFile(true) ;  //  && when ($scope.fileContent != null) | $scope.newWinDispl() ;
        	var listensendReadComplt = $scope.$on("sendReadComplt", function($event,args){
        		//console.log("Event listensendReadComplt args =" +  args.name.toSource()) ;
        	    //$event.stopPropagation(); //One recev stop it up word to avoid other window from repeating the same action !! Not Working
        		newWinDispl() ;
        		listensendReadComplt = null ;
        	}) ;
        }
	    
	    readFile = function (sendReadComplt)
		{
		$scope.dodisplay=true ;
		var reader = new FileReader();
		
		reader.onload = function(e) {
        //console.log(this.result); // this.result is the read file as an ArrayBuffer. HTML API
		$scope.fileContent= this.result ; 
		if (sendReadComplt) $scope.$emit("sendReadComplt",$scope.theFile);
		//if (sendReadComplt) $scope.$broadcast("sendReadComplt",$scope.theFile);
         };
         
        reader.onerror = function(e) {
          console.log(e);
        };
        reader.readAsText($scope.theFile);   //$scope.files[0]
		}	
	    //
	    newWinDispl = function ()
		{
	   	winId++ ;
	   if (winId > 1) { OpenWindow.close() ; OpenWindow=null; }
       OpenWindow = $window.open("data:text/html,"+ encodeURIComponent( $scope.fileContent), winId, "width=800,height=600,resizable=1,scrollbars=1");
       OpenWindow.onload = function(e) {
          // console.log("Event  Window Opend  %%%%%%%%%%%%%%%%%%%%%%" ) ;
          // $scope.$apply() ;
   		 } };
	    
	});
apFileUpLoad.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            //var model = $parse(attrs.fileModel);
            //var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                   // modelSetter(scope, element[0].files[0]);
                    scope.$emit("fileSelected", element[0].files[0] ) ;
                });
            });
        }
    };
}]);


       
    

