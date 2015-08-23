/** preWSWSTestCtlr.js
 * For Separate Files angular.module("track_wsws") /* angular.module("name", ["dependencyA", "dependencyB"]) then angular.module("name").controller or angular.module("name").service 
 */
//
angular.module('track_wsws').controller('pickerCtlr' ,  function ($scope, $http,$location,ModalForm3Service,AddRunItemService,$log) 
 {
			$http.get('/wsws/preWSWSTest').success(function(data) {
				$scope.wswsPreTestLis = data;
				$scope.buttonIsDisabled = true;

			}).error(function(data, status) {
				console.log('Error ' + data)
			});
			//Function to enable or Disable the RunTest Button according to check box event
			/*
			 * wsId:4, wsName:"makeOneASyncrEmpWS", doIt:false, processMode:"Asynchronous", description:"Asynchronous Employees Webservice Example", $$hashKey:"008", doForm:false, wsRunMemorizedId:""})
			 */
			$scope.addTorun = function(wsSelected) {
			console.log("The input item = " + wsSelected.toSource()) ; 
			var indexToRun = $scope.wswsPreTestLis.indexOf(wsSelected); //indexOf(item);
			console.log("The indexToRun =" + indexToRun.toSource()) ; 	
			console.log("The Selected item from indexToRun =" + $scope.wswsPreTestLis[indexToRun].toSource()) ; 
			//Call Form to update wsRunMemorizedId and check doForm
			wsSelected.doForm=false ;
			wsSelected.runId=""; //Match Java KnownWebServices property
			console.log("The updtaed item for Form input =" + wsSelected.toSource()) ;
			///////7/////
			var retFormPromise  = ModalForm3Service.openByCaller(wsSelected,"Add") ; //form3WorkType= "Add" | "Edit" | "Delete" but Edit and Add are the same just diff titles look @ Ternary operators in ModalForm3.js
			/// Temp Test		var retFormPromise  = ModalForm3Service.openByCaller(wsSelected,"Delete") ; //form3WorkType= "Edit" | "Delete" 
			 retFormPromise.then(function(retItem) {
			  alert('Success return retFormPromise: ' + retItem.toSource());				//-1 Success
			  $log.info(">>>>>>>>>>>We can use the updated here retFormPromise: " + retItem.toSource());	
			  //Reset 
			  wsSelected.doIt=false ;
			  if (retItem.doForm)AddRunItemService.addrunItem(retItem);
			}, function(reason) {
			  alert('Failed return retFormPromise: ' + reason.toSource());						//-2 Fail
			}, function(notifyMsg) {
			  alert('Got notification: ' + notifyMsg.toSource());							//-3 else notify progress
			});
			 ///////7/////
		
			}
				
			
			//Function to enable or Disable the RunTest Button according to check box event
			$scope.disableEnbleRunButton = function() {
				$scope.buttonIsDisabled = true;
				for (var i = $scope.wswsPreTestLis.length - 1; i >= 0; i--) {
					if ($scope.wswsPreTestLis[i].doIt == true) {
						$scope.buttonIsDisabled = false;
					}
					console.log('The buttonIsDisabled is :'
							+ $scope.buttonIsDisabled);
				}
			}

			//Function to post the RunTest
			$scope.runTest = function() {
				//Remove the doIt = false from list and submit   
				for (var i = $scope.wswsPreTestLis.length - 1; i >= 0; i--) {
					if ($scope.wswsPreTestLis[i].doIt == false) {
						console.log('Removeing '
								+ $scope.wswsPreTestLis[i].toSource());
						$scope.wswsPreTestLis.splice(i, 1);
					}
				}

				$http.post('/wsws/runWSWSTest', $scope.wswsPreTestLis).success(
						function(data) {
							$location.path('/lisAvlWSWS');
						}).error(function(data, status) {
					console.log('Error ' + data)
				})

			}

});