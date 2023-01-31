/**
 *  Access to the previously created module 'golfapp'
 */
 
 (function() {
	 var golfapp = angular.module('golfapp');
	 
	 golfapp.controller('createScorecardController', function($scope, $http) {
	
		$scope.createScorecard = function() {
			$http.post("/golfstatrecorder/webapi/players/scorecard/createScorecard", $scope.scorecard)
			.then(function(response) {				
				$scope.createStatus = 'create successful';
				$scope.disableCreate = true;
			}, function(response) {
				$scope.createStatus = 'error trying to create scorecard';	
				console.log('error http POST scorecard: ' + response.status);
			});
		}
		
		$scope.clear = function() {
			$scope.scorecard.idPlayer = '';
			$scope.scorecard.idVenue = '';
			$scope.scorecard.toPar = '';
			$scope.scorecard.score = '';
			$scope.disableCreate = false;
		}

			
		
	})

})()

