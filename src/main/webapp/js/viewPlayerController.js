/**
 *  Access to the previously created module 'golfapp'
 */
 
 (function() {
	 var golfapp = angular.module('golfapp');
	 
	 golfapp.controller('viewPlayerController', function($scope, $http, $location) {
		
		$scope.getAllPlayers = function() {
			$http.get("/golfstatrecorder/webapi/players")
			.then(function(response) {
				$scope.players = response.data;
				console.log('number of players: ' + $scope.players.length);
			}, function(response) {
				console.log('error http GET players: ' + response.status);
			});
		}
		
		$scope.goToUpdateView = function(idPlayer) {
			console.log('idPlayer: ' + idPlayer);
			$location.path('/updatePlayer/' + idPlayer);
		}
		
	//	$scope.getAllPlayers();

		
		
		
		
		
	})

})()

/*
$scope.players = [
			{
				idPlayer: 12,	        
				firstName: "Trae",
				lastName: "Brown" ,
				city: "Searcy",
				state: "Ar"	        
			},
			{
				idPlayer: 19,	        
				firstName: "David",
				lastName: "Driver" ,
				city: "Searcy",
				state: "Ar"	
			},
			{
				idPlayer: 32,	        
				firstName: "Alex",
				lastName: "Lately" ,
				city: "Wynne",
				state: "Ar"		        
			},
			];

*/