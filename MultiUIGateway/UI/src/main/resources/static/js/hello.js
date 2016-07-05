angular.module('securapp', [ 'ngRoute' ])
  .config(function($routeProvider, $httpProvider) {

    $routeProvider.when('/', {
      templateUrl : '/ui/views/home.html',
      controller : 'HomeController'
    }).otherwise('/');

    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

  })
  .controller('HomeController',['$http', '$scope', function($http, $scope) {
    var self = this;
  }])
  .controller('IndexController', ['$scope', '$http', '$rootScope', function($scope, $http, $rootScope){
    tab = {selection: 0};
    $scope.login = function(){
        $http.get('token').then(function(resp){
            $rootScope.authenticated = true;
            var reqParam = {url: 'resource', method: 'GET', headers: {'X-Auth-Token': resp.data.token}};
            $http(reqParam).then(function(resp){
                $scope.greeting = resp.data;
            });
        });
    };

    $scope.logout = function(){
        $http.post("/logout",{}).finally(function(){
            $rootScope.authenticated = false;
        });
    };
  }]);