/**
 * Created by prana on 11/21/2016.
 */

app.controller('SettingController', ['$scope','$location','settingService', function($scope,$location,settingService) {

    function init() {
        var user = JSON.parse(localStorage.getItem("user"));
        $scope.name = user.personName;
        $scope.phoneNumber = user.phoneNumber;
        $scope.emailId = user.emailId;
        $scope.userId = user.id;
        setTimeout(function(){
            $scope.$root.$apply();
        },1);
    }


    $scope.updateProfile = function(){
        var user = {};
        user.id = $scope.userId;
        user.personName = $scope.name;
        user.phoneNumber = $scope.phoneNumber;
        user.emailId = $scope.emailId;
        console.log(user);
        if($scope.password != "" && $scope.password !=null && $scope.password != undefined  ){
            user.password = $scope.password;
        }
        settingService.updateUser(user).then(function (response) {
            if(response.status == 200){
                $("#profileupdate").show();
                //$("#profileupdate").css("top","0px");
                localStorage.setItem("user",JSON.stringify(response.objectList[0]));
                $scope.button = true;
                init();
            }else{
                alert(response.message);
            }
        });
    };

    $(document).ready(function () {
        init();
    });

    $scope.closeModal = function () {
        $("#profileupdate").hide();
    };

}]);
