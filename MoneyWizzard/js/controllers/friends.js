/**
 * Created by prana on 12/6/2016.
 */

app.controller('FriendsController', ['$scope','$location','friendsService', function($scope,$location,friendsService) {

    $scope.init = function() {

        friendsService.getFriends((JSON.parse(localStorage.getItem("user"))).id).then(function (response) {
            if(response.status == 200){
                console.log(response);
                $scope.friends = response.objectList;
                $scope.$apply();
            }
        });
    };

    $scope.openAddFriendModal = function () {
        $("#addFriendModal").show();//modal("show");
    };
    $scope.hideAddFriendModal = function () {
        $("#addFriendModal").hide();//modal("show");
    };
$scope.closeSuccessModal = function () {
  $("#addsuccessfull").hide();
};
    $scope.searchPerson = function () {

        var value = friendsService.searchPerson($scope.search.emailId).then(function(response){
            if(response.status == 200){
                var userId = JSON.parse(localStorage.getItem("user")).id;
                if(userId != response.objectList[0].id){
                    $scope.search.id = response.objectList[0].id;
                    $scope.search.name = response.objectList[0].personName;
                    $scope.$apply();
                }

            }
        });

    };

    $scope.addFriend = function () {

        var value = friendsService.addFriend(JSON.parse(localStorage.getItem("user")).id,$scope.search.id).then(function(response){
            if(response.status == 200){
                $("#addFriendModal").modal("hide");
                $('body').removeClass('modal-open');
                $('.modal-backdrop').remove();
                $("#addsuccessfull").show();
                $scope.init();
            }
        });

    };

    $scope.init();
}]);