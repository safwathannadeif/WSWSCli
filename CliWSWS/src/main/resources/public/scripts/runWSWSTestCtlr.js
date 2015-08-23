/** preWSWSTestCtlr.js
 * For Separate Files angular.module("track_wsws") /* angular.module("name", ["dependencyA", "dependencyB"]) then angular.module("name").controller or angular.module("name").service 
 */
//

var wswsRunTestLis =[] ;
resetItem = function(itemi)
{
	itemi.action2.Edit = false ;
	itemi.action2.Delete = false ; 
	console.log("reset Called OK..............");
}

angular.module('track_wsws').service('AddRunItemService', function ($location,$log) {
	this.addrunItem = function (itemToRun) {
		//The Item coming with 
	    delete itemToRun.doIt;
	    var action2 = {Edit:false, Delete:false};
	    itemToRun.action2 ;
		wswsRunTestLis.push(itemToRun);
		$location.path('/runWSWSTest');
		
	}
	
}) ;

angular.module('track_wsws').controller('runWSWSTestCtlr' ,  function ($scope, $http,$location,$log,ModalForm3Service) 
 {
	$scope.isRun= function()
	{
		var goRun = true ;
		if (wswsRunTestLis.length == 0) goRun =false ;
		return !goRun ;
	}
	$scope.wswsRunTestLis=wswsRunTestLis ;
			//Function to enable or Disable the RunTest Button according to check box event
			$scope.disableEnbleRunButton = function() {
				$scope.buttonIsDisabled = true;
				for (var i = $scope.wswsRunTestLis.length - 1; i >= 0; i--) {
					if ($scope.wswsPreTestLis[i].doIt == true) {
						$scope.buttonIsDisabled = false;
					}
					console.log('The buttonIsDisabled is :'
							+ $scope.buttonIsDisabled);
				}
			}
			$scope.addTest = function()
			{
				$location.path('/pickerRef');
			}
			//Function to Edit  Item
			$scope.Edit = function(servToEdit) {
//				Reset 
				resetItem(servToEdit);	
				var retFormPromise  = ModalForm3Service.openByCaller(servToEdit,"Edit") ; //form3WorkType= "Add" | "Edit" | "Delete" but Edit and Add are the same just diff titles look @ Ternary operators in ModalForm3.js
				 retFormPromise.then(function(retItem) {
				  alert('Edit****Success return retFormPromise: ' + retItem.toSource());				//-1 Success
				  $log.info("Edit>>>>>>>>>>>We can use the updated here retFormPromise: " + retItem.toSource());	
				
				  if (retItem.doForm){
					 alert('Edit>>>>>	Updated Success return retFormPromise: ' + retItem.toSource());
					 var indxTodel =  wswsRunTestLis.indexOf(servToEdit) ;
					 wswsRunTestLis.splice(indxTodel,1) ; //Remove at the index					;
					 wswsRunTestLis.splice(indxTodel,0,retItem) ; //add updated item in the same index
					 //delete myJSONObject.regex;
					// wswsRunTestLis.push(retItem) ;
					  
				  }
				  if (!retItem.doForm){
					  alert('Edit>>>>>	NO Updated has been Cancled retFormPromise: ' + retItem.toSource());
				  }
				}, function(reason) {
				  alert('Failed return retFormPromise: ' + reason.toSource());						//-2 Fail
				}, function(notifyMsg) {
				  alert('Got notification: ' + notifyMsg.toSource());							//-3 else notify progress
				});	
				
			}
			
			//Function to Delete   Item
			$scope.Remove = function(servToDelete) {
				//Reset 
				 resetItem(servToDelete);	
				var retFormPromise  = ModalForm3Service.openByCaller(servToDelete,"Delete") ; //form3WorkType= "Add" | "Edit" | "Delete" but Edit and Add are the same just diff titles look @ Ternary operators in ModalForm3.js
				 retFormPromise.then(function(retItem) {
				  alert('Success return retFormPromise: ' + retItem.toSource());				//-1 Success
				  $log.info(">>>>>>>>>>>We can use the updated here retFormPromise: " + retItem.toSource());	
	
				  if (retItem.doForm){
					  alert('Delete/remove to be implemneted  Success return retFormPromise: ' + retItem.toSource());
					  var indxToremove =  wswsRunTestLis.indexOf(servToDelete) ;
					  wswsRunTestLis.splice(indxToremove,1) ; //Remove at the index		
				  }
				  
				  if (!retItem.doForm)alert('NO Updated has been Cancled retFormPromise: ' + retItem.toSource());
				}, function(reason) {
				  alert('Failed return retFormPromise: ' + reason.toSource());						//-2 Fail
				}, function(notifyMsg) {
				  alert('Got notification: ' + notifyMsg.toSource());							//-3 else notify progress
				});	
				 
			}
			
			
			
			//Function to post the RunTest
			$scope.runTheTest = function() {
				//Clean Records    
				for (var i = $scope.wswsRunTestLis.length - 1; i >= 0; i--) {
					$log.info('Before delete item ' +  wswsRunTestLis[i].toSource());
					 delete   $scope.wswsRunTestLis[i].action2 ;
					 delete   $scope.wswsRunTestLis[i].doForm ;
					 delete   $scope.wswsRunTestLis[i].title ;
					 delete   $scope.wswsRunTestLis[i].form3WorkType ;
					 $log.info('after delete item ' +  wswsRunTestLis[i].toSource());
					}
			
				$log.info('$scope.wswsRunTestLis.length ' +  $scope.wswsRunTestLis.length ) ;
				$http.post('/wsws/runWSWSTest', $scope.wswsRunTestLis).success(
						function(data) {
							wswsRunTestLis =[] ;
							$location.path('/lisAvlWSWS');
						}).error(function(data, status) {
					console.log('***Error ' + data)
				})

			}

});