/**
 *  Create a new module named 'golfapp'
 */


(function() {
	let golfapp = angular.module('golfapp', ['ngRoute']);
	
	golfapp.config(function($routeProvider) {
          $routeProvider
          .when("/viewPlayers", {
            templateUrl : "viewPlayers.html",
            controller : "viewPlayerController"
          })
          .when("/updatePlayer/:idPlayer", {
            templateUrl : "updatePlayer.html",
            controller: "updatePlayerController"
          })
          .when("/viewCourses", {
            templateUrl : "viewCourses.html",
            controller : "viewCourseController"
          })
          .when("/viewScorecards", {
            templateUrl : "viewScorecards.html",
            controller : "viewScorecardController"
          })
          .when("/createPlayer", {
            templateUrl : "createPlayer.html",
            controller: "createPlayerController"
          })
          .when("/createScorecard", {
            templateUrl : "createScorecard.html",
            controller: "createScorecardController"
          })
          .when("/stack", {
			templateUrl : "stack.html"
          })
           .when("/resume", {
			templateUrl : "resume.html"
          })
           .when("/main", {
			templateUrl : "main.html"
          })
          .otherwise({
			  templateUrl : "main.html"
          });
        });
 
})()