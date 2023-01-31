/**
 *  Access to the previously created module 'golfapp'
 */
 
 (function() {
	 var golfapp = angular.module('golfapp');
	 
	 golfapp.controller('updatePlayerController', function($scope, $http, $routeParams, $location) {
		
		$scope.getPlayersById = function() {
			$http.get("/golfstatrecorder/webapi/players/" + $routeParams.idPlayer)
			.then(function(response) {
				var players = response.data;
				if (players.length == 1) {
					$scope.player = players[0];
				} else {
					//TODO error message
				}				
			}, function(response) {
				console.log('error http GET players by id: ' + response.status);
			});
		}

		$scope.getPlayersById();
		
		$scope.updatePlayer = function() {
			$http.put("/golfstatrecorder/webapi/players", $scope.player)
			.then(function(response) {				
				$scope.updateStatus = 'update successful';			
			}, function(response) {
				$scope.updateStatus = 'error trying to update player';	
				console.log('error http PUT players: ' + response.status);
			});
		}


		$scope.deletePlayer = function() {
			$http.delete("/golfstatrecorder/webapi/players/" + $scope.player.idPlayer)
			.then(function(response) {				
				$scope.updateStatus = 'delete successful';	
				$scope.disableUpdate = true;
			}, function(response) {
				$scope.updateStatus = 'error trying to delete player';	
				console.log('error http DELETE players: ' + response.status);
			});
		}


			
		
	})

})()

