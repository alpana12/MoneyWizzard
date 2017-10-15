/**
 * Created by prana on 12/7/2016.
 */

app.controller('GroupsController', ['$scope','$location','friendsService','groupService', function($scope,$location,friendsService,groupService) {

    $scope.init = function() {
        var user = JSON.parse(localStorage.getItem("user"));
        friendsService.getFriends(user.id).then(function (response) {
            if(response.status == 200){
                console.log(response);
                //response.objectList.push({"id":"123","personName":"aaa"});
                $scope.friends = response.objectList;
                //$scope.$apply();
            }
        });

        groupService.getGroups(user.id).then(function (response) {
            if(response.status == 200){
                if(response.objectList!=null && response.objectList.length > 0){
                    $scope.groups = response.objectList;
                    $scope.$apply();
                }
            }
        });

        Highcharts.chart('graph1', {
            title: {
                text: 'Monthly Group Expense',
                x: -20 //center
            },
            xAxis: {
                categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
                    'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
            },
            yAxis: {
                title: {
                    text: 'Categories'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080 '
                }]
            },
            tooltip: {
                valueSuffix: '$'
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'middle',
                borderWidth: 0
            },
            series: [{
                name: 'Boston Friends',
                data: [27.0, 36.9, 59.5, 14.5, 38.2, 21.5, 25.2, 66.5, 53.3, 38.3, 33.9, 49.6]
            }, {
                name: 'Room-mates',
                data: [40.2, 30.8, 25.7, 11.3, 27.0, 22.0, 34.8, 44.1, 20.1, 14.1, 18.6, 22.5]
            }, {
                name: 'Class group',
                data: [55.9, 30.6, 23.5, 48.4, 23.5, 37.0, 48.6, 57.9, 44.3, 39.0, 23.9, 11.0]
            }
            ]});
    };

    /*$scope.records = [{
        name:'a', age:'x'},
        {name:'b', age:'y'
        },{name:'c',age:'c'}
    ];*/

    $scope.addGroup = function () {
        var data = {};
        data.name = $scope.grpname;

        var person = [];
        $('#friendList :selected').each(function(i, selected){
            person.push({"id":selected.getAttribute('data-id'),"personName":$(selected).val()});
        });
        var user = JSON.parse(localStorage.getItem("user"));
        person.push({"id":user.id,"personName":user.personName});
        data.personObjs = person;
        groupService.addGroup(data).then(function (response) {
            if(response.status == 200){
                /*$("#addsuccessfull").modal("show");
                $("#addsuccessfull").css("top","0px");
                $("#createGroupModal").modal("hide");
                $('body').removeClass('modal-open');
                $('.modal-backdrop').remove();*/
               $("#createGroupModal").hide();
                $("#addsuccessfull").show();
                $scope.init();
            }
        });
    };

    $scope.populateExpenseModal = function (data) {
        $scope.expDetails = data;
        //$scope.$apply();
    };

    $scope.closeSuccessModal = function () {
      $("#addsuccessfull").hide();
    };

    $scope.openAddGroupModal = function () {
        $("#createGroupModal").show();
    };

    $scope.closeModal= function(){
        $("#createGroupModal").hide();
    }
    $scope.init();
}]);