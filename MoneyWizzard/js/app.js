/**
 * Created by pranay on 11/21/2016.
 */
var app = angular.module('app', ['ui.router']);

app.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {


    $("#toggleSidebar").click(function(e) {
        $('.ui.labeled.icon.sidebar').sidebar('toggle');
    });

    $stateProvider.state('home', {
        name: 'home',
        url: '/',
        templateUrl: 'pages/home.html',
        controller:"HomeController",
        data: {
            isLoggedIn: false
        }
    });
    $stateProvider.state('dashboard', {
        name: 'dashboard',
        url: '/dashboard',
        templateUrl: 'pages/dashboard.html',
        controller: 'DashboardController',
        data: {
            isLoggedIn: true
        }
    });
    $stateProvider.state('setting', {
        name: 'setting',
        url: '/setting',
        templateUrl: 'pages/setting.html',
        controller: 'SettingController'
    });
    $stateProvider.state('friends', {
        name: 'friends',
        url: '/friends',
        templateUrl: 'pages/friends.html',
        controller: 'FriendsController'
    });
    $stateProvider.state('groups', {
        name: 'groups',
        url: '/groups',
        templateUrl: 'pages/groups.html',
        controller: 'GroupsController'
    });

    $stateProvider.state('expenses', {
        name: 'expenses',
        url: '/expenses',
        templateUrl: 'pages/expenses.html',
        controller: 'ExpensesController'
    });
    $stateProvider.state('budgets', {
        name: 'budgets',
        url: '/budgets',
        templateUrl: 'pages/budgets.html',
        controller: 'BudgetsController'
    });
    $stateProvider.state('wallet', {
        name: 'wallet',
        url: '/wallet',
        templateUrl: 'pages/wallet.html',
        controller: 'WalletController'
    });

    $urlRouterProvider.otherwise('/');
}]);
app.controller("navBarController",['$scope','$location','homeService',function ($scope,$location,homeService) {
    $scope.loginData = {};

    $scope.signOut = function () {
        $scope.$root.isLoggedIn = false;

        localStorage.setItem('isLoggedIn', false);
        sessionStorage.setItem("isLoggedIn",false);
        //  $scope.$apply();
        $location.path('home');
        //$location.refresh();//path('home');
        setTimeout(function(){
            $scope.$apply();
        },1);
    };
}]);

app.run(['$rootScope', '$location',  function ($rootScope, $location) {
    $rootScope.$on("$stateChangeStart", function (e, toState, toParams, fromState, fromParams) {
        var isLoggedIn = sessionStorage.getItem("isLoggedIn");
        var path = toState.name;
        if((isLoggedIn==null || isLoggedIn=="false")){
            $rootScope.isLoggedIn = false;
        }else{
            $rootScope.isLoggedIn = true;
        }
        if((isLoggedIn==null || isLoggedIn=="false") && path!="home" ){
            $location.path("home");
        }else if(isLoggedIn!=null && isLoggedIn=="true" && path=="home"){
            e.preventDefault();
            $location.path("dashboard");
           // $location.reload();
            setTimeout(function(){
                $rootScope.$apply();
            },1);
        }
    });
}]);

