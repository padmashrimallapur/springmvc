angular.module('ngBoilerplate.account', ['ui.router' , 'ngResource'])
.config(function($stateProvider) {
    $stateProvider.state('login', {
        url:'/login',
        views: {
            'main': {
                templateUrl:'account/login.tpl.html',
                controller: 'LoginCtrl'
            }
        },
        data : { pageTitle : "Login" }
    })
    .state('register', {
            url:'/register',
            views: {
                'main': {
                    templateUrl:'account/register.tpl.html',
                    controller: 'RegisterCtrl'
                }
            },
            data : { pageTitle : "Registration" }
            }
    );
})
.factory("sessionService", function($http, $base64){
      var session = {};
      session.login = function(data){
        return http.$post("/webapp/login",  "username=" + data.name + "&password=" + data.password ,{

        headers: {'Content-Type' : 'application/x-www-form-urlencoded'}
        }).then(function(data){

          alert("login is successfull");
          localStorage.setItem("session", data);
        }, function(data){
            alert("error in log in");
        });

      };

      session.logout = function (){
          localStorage.removeItem("session") ;
      };

      session.isLoggedIn = function(){
        return localStorage.getItem("session")!== null;
      };
      return session;
})
.factory("accountService", function($resource){
    var service = {};
    service.register = function(account, success, failure){
      var Account = $resource("/webApp/rest/accounts");
      Account.save({}, account, success, failure);

    };

    service.userExists = function(account, success, failure){
        var Account = $resource("/webApp/rest/accounts");
        var data = Account.get({name:account.name}, function(){
            var accounts = data.accounts;
            if(accounts.length !== 0){
                success(accounts[0]);
            }
            else
            {
                failure();
            }
        },

        failure
        );
    };
    return service;

})
.controller("LoginCtrl", function($scope, sessionService, accountService, $state) {
        $scope.login = function() {
        accountService.userExists($scope.account, function(account){
          sessionService.login(account);
          $state.go("home");
        },
        function(){
            alert("error in the login");
        });

        };
})
.controller("RegisterCtrl", function($scope, sessionService, $state, accountService){

    $scope.register = function(){
        accountService.register($scope.account,
        function(returnedData){
           sessionService.login(returnedData);
           $state.go("home");
        },
        function(){
        alert("Error in registering the user");

        });
    };

});