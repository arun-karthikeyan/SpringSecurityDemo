angular.module('securapp', [ 'ngRoute', 'securapp.controllers' ]).config(function($routeProvider) {

	$routeProvider.when('/', {
		templateUrl : './views/home.html',
		controller : 'HomeController'
	}).otherwise('/');

});

angular.module('securapp.controllers', [])
.controller('IndexController',['$rootScope', '$http', '$location', '$route', '$scope',function($rootScope, $http, $location, $route, $scope) {

    $scope.tab = {selection: 0};
	$http.get('user/').then(function(response) {
		if (response.data.name) {
			$rootScope.authenticated = true;
		} else {
			$rootScope.authenticated = false;
		}
	}, function() {
		$rootScope.authenticated = false;
	});

	$scope.credentials = {};

	$scope.logout = function() {
		$http.post('logout', {}).finally(function() {
			$rootScope.authenticated = false;
			$location.path("/");
		});
	}

}]).controller('HomeController', function($http, $scope) {
	$http.get('resource/').then(function(response) {
		$scope.greeting = response.data;
	})
});
