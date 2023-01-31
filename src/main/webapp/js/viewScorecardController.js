/**
 *  Access to the previously created module 'golfapp'
 */
 
 (function() {
	 var golfapp = angular.module('golfapp');
	 
	 golfapp.controller('viewScorecardController', function($scope, $http) {
		
		$scope.getAllScorecards = function() {
			$http.get("/golfstatrecorder/webapi/players/scorecard")
			.then(function(response) {
				$scope.scorecards = response.data;
				console.log('number of scorecards: ' + $scope.scorecards.length);
			}, function(response) {
				console.log('error http GET scorecards: ' + response.status);
			});
		}
		
		$scope.getAllScorecards();
		
		
		
	})

})()

/*

$scope.scorecards = [
			{
				idPlayer: 912,	        
				idVenue: 904,
				scoreCardId: 84,
				toPar: -1,
				score: 71,
				createDateTime: "2022-12-01 11:10:22.0"	        
			},
			];
			
	*/