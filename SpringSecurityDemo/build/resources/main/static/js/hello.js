angular.module('securapp', ['ngRoute', 'securapp.controllers'])
.config(function($routeProvider){

$routeProvider.when('/', {
    templateUrl: './views/home.html',
    controller: 'HomeController'
}).when('/login', {
    templateUrl: './views/login.html',
    controller: 'LoginController'
}).otherwise('/');

});

angular.module('securapp.controllers', [])
.controller('HomeController',['$rootScope', '$scope', '$http', function($rootScope, $scope, $http) {

    if($rootScope.authenticated) {
    $http.get('/token').then(function(response) {
        $http({
//            url : '/resource/',
            url : '/resource/',
            method : 'GET',
            headers: {
            'X-Auth-Token' : response.data.token
            }
            }).then(function(response) {
                $scope.greeting = response.data;
            });
    });
}
    //$scope.greeting = {id: '123', content: 'Hello From Angular'};
}])

.controller('LoginController', ['$scope', '$rootScope', '$http', '$location', function($scope, $rootScope, $http, $location){
    $scope.user = {name: '', password: ''};

    var authenticate = function(credentials, callback) {
        console.log(credentials);
        var headers = credentials ? {authorization: 'Basic '+btoa(credentials.name+':'+credentials.password)} : {};
        console.log(headers);
        $http.get('/user', {headers: headers})
        .then(function(resp){
            if(resp.data.name) {
                $rootScope.authenticated = true;
            }else {
                $rootScope.authenticated = false;
            }
            callback && callback();

        }, function(resp){
            $rootScope.authenticated = false;
            callback && callback();
        });
    };

    //authenticate();

    $scope.login = function(){
        authenticate($scope.user, function(){
            if($rootScope.authenticated) {
                $location.path("/");
                $scope.error = false;
            } else {
                $location.path("/login");
                $scope.error = true;
            }
        })
    };
}])

.controller('IndexController', ['$rootScope', '$scope', '$http', '$location', function($rootScope, $scope, $http, $location){
    $scope.logout = function() {
        console.log("logout function called");
        $http.post('/logout', {}).then(function(resp){
        console.log("/logout success");
        }, function(resp){
        console.log("/logout error");
        }).finally(function() {
            console.log("/logout finally")
            $rootScope.authenticated = false;
            $location.path('/');
        });
    };
}]);