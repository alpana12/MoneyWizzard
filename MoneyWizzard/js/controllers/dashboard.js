/**
 * Created by prana on 11/21/2016.
 */

app.controller('DashboardController', ['$scope','dashBoardService','$location', function($scope,dashBoardService,$location) {

    function init() {
        $scope.getReports();
    }


    $scope.signOut = function () {
        localStorage.setItem('isLoggedIn', false);
        sessionStorage.setItem("isLoggedIn",false);
        $scope.$root.isLoggedIn = false;
        $scope.$apply();
        $location.refresh();// path('home');
        $scope.$apply();
    };

    $scope.getReports = function(){
        var user = JSON.parse(localStorage.getItem("user"));

        dashBoardService.getReports(user.id).then(function (response) {
            var data = response.objectList[0];
            var numAnim = new CountUp("totalRemainingBudget", 0, 435);
            numAnim.start();
            numAnim = new CountUp("expense", 0, data.expenses);
            numAnim.start();
            numAnim = new CountUp("youOwe", 0, data.youOwe);
            numAnim.start();
            numAnim = new CountUp("youAreOwed", 0, data.youAreOwed);
            numAnim.start();

            var userCatExp=[{"name":"No expenses tracked yet","y":1}];
            if(data.categoryExpenses!=null && data.categoryExpenses.length > 0){
                userCatExp=[];
                for(var i=0;i< data.categoryExpenses.length; i++ ){
                    var exp = {};
                    exp.name = data.categoryExpenses[i].category;
                    exp.y = data.categoryExpenses[i].amount;
                    userCatExp.push(exp);
                }
            }
            Highcharts.chart('container', {
                chart: {
                    type: 'column'
                },
                title: {
                    text: 'Monthly Expense'
                },
                /*subtitle: {
                 text: 'Source: WorldClimate.com'
                 },*/
                xAxis: {
                    categories: [
                        'Jan',
                        'Feb',
                        'Mar',
                        'Apr',
                        'May',
                        'Jun',
                        'Jul',
                        'Aug',
                        'Sep',
                        'Oct',
                        'Nov',
                        'Dec'
                    ],
                    crosshair: true
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: 'Amount in $'
                    }
                },
                tooltip: {
                    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                    pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>${point.y:.1f}</b></td></tr>',
                    footerFormat: '</table>',
                    shared: true,
                    useHTML: true
                },
                plotOptions: {
                    column: {
                        pointPadding: 0.2,
                        borderWidth: 0
                    }
                },
                series: [{
                    name: 'Amount',
                    data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4]

                }]
            });

            Highcharts.chart('categoryPieChart', {
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false,
                    type: 'pie'
                },
                title: {
                    text: 'Category wise expenses'
                },
                tooltip: {
                    pointFormat: '{series.name}: <b>${point.y:.1f}</b>'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: false
                        },
                        showInLegend: true
                    }
                },
                series: [{
                    name: 'Amount',
                    colorByPoint: true,
                    data: userCatExp
                }]
            });
        });
    };

    init();
}]);
