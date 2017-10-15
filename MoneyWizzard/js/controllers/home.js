/** * Created by prana on 12/3/2016.
 */
//var homeControllers = angular.module('homeControllers', []);
app.controller('HomeController', ['$scope','homeService','$location', function($scope,homeService,$location) {
    $scope.login = function () {
        var user = {};
        user.emailId = $scope.loginData.emailId;
        user.password = $scope.loginData.password;
        var value = homeService.loginUser(user).then(function(response){
            if(response.status == 200){
                $scope.$root.isLoggedIn = true;
                sessionStorage.setItem("isLoggedIn",true);
                localStorage.setItem("user",JSON.stringify(response.objectList[0]));
                $("#loginModal").modal("hide");
                $('body').removeClass('modal-open');
                $('.modal-backdrop').remove();
                $scope.$apply();
                $location.path('dashboard');
                $scope.$apply();
            }
            else{
                $("#status").html("Invalid credentials! Please try again");
            }
        });
    };
    $scope.signUp = function () {
        var user = {};
        user.personName = $scope.name;
        user.emailId = $scope.emailId;
        user.password = $scope.password;
        var value = homeService.signUp(user).then(function(response){
            if(response.status == 200){
                $("#signUpStatus").html("Welcome to the team!");
                $("#signUpModal").modal("show");
                $("#signUpModal").css("top","0px");
            }
            else{
                $("#signUpStatus").html("Invalid credentials! Please try again");
            }
        });
    };
}]);