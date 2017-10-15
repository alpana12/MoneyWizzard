/**
 * Created by prana on 12/7/2016.
 */

app.controller('ExpensesController', ['$scope','expenseService','groupService','$location', function($scope,expenseService,groupService,$location) {

    $scope.init = function(){
        var user = JSON.parse(localStorage.getItem("user"));
        groupService.getGroups(user.id).then(function (response) {
            if(response.status == 200){
                if(response.objectList!=null && response.objectList.length > 0){
                    $scope.groups = response.objectList;
                    $scope.$apply();
                }
            }
        });

        expenseService.getExpenses(user.id).then(function (response) {
            if(response.status == 200){
                $scope.expenses = response.objectList;
                if($scope.expenses !=null && $scope.expenses.length > 0){
                    for(var i=0;i<$scope.expenses.length;i++){
                        var date = new Date($scope.expenses[i].date);
                        $scope.expenses[i].date = (date.getMonth()+1)+"/"+date.getDate()+"/"+date.getFullYear();
                    }
                }
            }
        });
    };

    $scope.categories=[
        {id:'1', name:'Food and drinks'},
        {id:'2', name:'Shopping'},
        {id:'3', name:'Health'},
        {id:'4', name:'Groceries'},
            {id:'5', name:'Travel'},
        {id:'6', name:'Rent'},
        {id:'7', name:'Misc'}
        ];

    $scope.addPeronalExpense=function () {
        var user = JSON.parse(localStorage.getItem("user"));
        var data ={};
        data.personId = user.id;
        data.expenseCategoryId = $scope.selectedCategory.id;
        data.location =$scope.personal.location;
        data.expenseType = "Personal";
        data.amount = $scope.personal.amount;
        data.comment = $scope.personal.description;
        /*var persons = [];
        data.person = persons;*/

        var value = expenseService.addExpense(data).then(function(response){
            if(response.status == 200){
                $("#addsuccessfull").show();
                //$("#addsuccessfull").css("top","0px");
                $scope.init();
            }
        });
    }

    $scope.addGroupExpense=function () {
        var user = JSON.parse(localStorage.getItem("user"));
        var data ={};
        data.personId = user.id;
        data.expenseCategoryId = $scope.selectedCategoryGroup.id;
        data.location =$scope.group.location;
        data.expenseType = "Group";
        data.amount = $scope.group.amount;
        data.comment = $scope.group.description;
        data.person = $scope.groupMembers;
        data.groupId = $scope.selectedGroup.id ;
        var value = expenseService.addExpense(data).then(function(response){
            if(response.status == 200){
                $("#groupaddsuccessfull").show();
                //$("#groupaddsuccessfull").css("top","0px");
                $scope.init();
            }
        });
    };

    $scope.populateFriends = function (data) {
        $scope.groupMembers = $scope.selectedGroup.personObjs;
    };

    $scope.closeSuccessModal = function () {
        $("#addsuccessfull").hide();
    };

    $scope.closeGroupSuccessModal = function () {
      $("#groupaddsuccessfull").hide();
    };
    $scope.init();

}]);