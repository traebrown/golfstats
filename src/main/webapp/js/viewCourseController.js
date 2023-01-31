/**
 *  Access to the previously created module 'golfapp'
 */
 
 (function() {
	 var golfapp = angular.module('golfapp');
	 
	 golfapp.controller('viewCourseController', function($scope, $http) {
		
		$scope.getAllCourses = function() {
			$http.get("/golfstatrecorder/webapi/players/course")
			.then(function(response) {
				$scope.courses = response.data;
				console.log('number of courses: ' + $scope.courses.length);
			}, function(response) {
				console.log('error http GET courses: ' + response.status);
			});
		}
		
		$scope.getAllCourses();
		
	})

})()


/*
$scope.courses = [
			{
				idVenue: 904,	        
				par: 72,
				courseName: "Brown Course" ,
				courseCity: "Jonesboro",
				courseState: "Ar"	        
			},
			];
*/