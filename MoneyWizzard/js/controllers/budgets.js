/**
 * Created by prana on 12/8/2016.
 */

app.controller('BudgetsController', ['$scope','$location','budgetService', function($scope,$location,budgetService) {

    // $scope.months = ['Jan', 'Feb','Mar','Apr','May','Jun','Jul','Aug','Sept','Oct','Nov','Dec'];
    $scope.months = [1,2,3,4,5,6,7,8,9,10,11,12];
    $scope.years = ['2016','2017','2018','2019','2020','2021','2021','2022','2023','2024','2026'];

    $scope.init = function(){
        budgetService.getBudgets((JSON.parse(localStorage.getItem("user"))).id).then(function (response) {
            if(response.status == 200){
                $scope.budgets = response.objectList;
                $scope.$apply();
            }
        });
    };

    $scope.addBudget = function(){
        //$scope.$apply();
        var data = {};
        data.year = $scope.budget.year;
        data.month = $scope.budget.month;
        data.amount = $scope.budget.amount;
        data.personId = (JSON.parse(localStorage.getItem("user"))).id;
        budgetService.addBudget(data).then(function (response) {
            if(response.status == 200){
                $("#addsuccessfull").show();
                //$("#addsuccessfull").css("top","0px");
                $scope.init();
            }
        });
    };

    $scope.closeModal = function () {
        $("#addsuccessfull").hide();
    };
    $scope.init();
}]);
