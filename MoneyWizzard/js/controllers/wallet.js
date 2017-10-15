/**
 * Created by prana on 12/8/2016.
 */

app.controller('WalletController', ['$scope','$location','walletService','friendsService', function($scope,$location,walletService,friendsService) {

    $scope.init = function(){
        var user = JSON.parse(localStorage.getItem("user"));
        walletService.getCurrentWalletBalance(user.id).then(function (response) {
            if(response.status == 200){
                if(response.objectList == null || response.objectList==undefined || response.objectList.length <=0){
                    $scope.walletAmmount = 0;
                }else{
                    $scope.walletAmmount = response.objectList[0];
                }
                $scope.$apply();
            }
        });

        walletService.getAccountHistory(user.id).then(function (response) {
            if(response.status == 200){
                $scope.accountHistory = response.objectList;
                if($scope.accountHistory !=null && $scope.accountHistory.length > 0){
                    for(var i=0;i<$scope.accountHistory.length;i++){
                        var date = new Date($scope.accountHistory[i].date);
                        $scope.accountHistory[i].date = (date.getMonth()+1)+"/"+date.getDate()+"/"+date.getFullYear();
                    }
                }
                $scope.$apply();
            }
        });

        friendsService.getFriends(user.id).then(function (response) {
            if(response.status == 200){
                //response.objectList.push({"id":"123","personName":"aaa"});
                $scope.map = [];
                response.objectList.forEach(function(item) {
                    //$scope.selectedFriend[i] = item.id;
                    $scope.map.push({name:item.personName,id:item.id});
                    $("#emails").append($("<option data-id="+item.id+">"+item.personName+"</option>"));
                });
                $scope.friends = response.objectList;
                $scope.$apply();
            }
        });


    };

    $scope.addMoney = function(){
        var data = {};
        data.accountBalance = $scope.wallet.amount;
        data.personId = (JSON.parse(localStorage.getItem("user"))).id;
        walletService.addMoney(data).then(function (response) {
            if(response.status == 200){
                $("#addsuccessfull").show();
                //$("#addsuccessfull").css("top","0px");
                $scope.init();
            }
        });

    };

    $scope.transferMoney = function(){
        for(var i = 0;i<$scope.map.length;i++){
            if($scope.map[i].name==$("#selectedFriend").val()){
                var user = JSON.parse(localStorage.getItem("user"));
                var data = {};
                data.amount = $scope.transfer.amount;
                data.recieverPersonId = $scope.map[i].id;
                data.senderPersonId = user.id;
                data.comment = $scope.transfer.comment;
                walletService.transferMoney(data).then(function (response) {
                    if(response.status == 200){
                        $("#transfersuccessfull").show();
                        //$("#transfersuccessfull").css("top","0px");
                        $scope.init();
                    }
                });
                break;
            }
        }

    };

    $scope.closeTransferSuccessModal = function () {
        $("#transfersuccessfull").hide();
    };
    $scope.closeSuccessModal = function () {
        $("#addsuccessfull").hide();
    };

    $scope.closeAddMoneyModal = function () {
        $("#addMoneyModal").hide();
    };

    $scope.openAddMoneyModal = function () {
        $("#addMoneyModal").show();
    };



    $scope.init();
}]);
