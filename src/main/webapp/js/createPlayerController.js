/**
 *  Access to the previously created module 'golfapp'
 */
 
 (function() {
	 var golfapp = angular.module('golfapp');
	 
	 golfapp.controller('createPlayerController', function($scope, $http) {
		
		$scope.createPlayer = function() {
			$http.post("/golfstatrecorder/webapi/players", $scope.player)
			.then(function(response) {				
				$scope.createStatus = 'create successful';
				$scope.disableCreate = true;
			}, function(response) {
				$scope.createStatus = 'error trying to create player';	
				console.log('error http POST playerss: ' + response.status);
			});
		}
		
		$scope.clear = function() {
			$scope.player.firstName = '';
			$scope.player.lastName = '';
			$scope.player.city = '';
			$scope.player.state = '';
			$scope.disableCreate = false;
		}


			
		
	})

})()

