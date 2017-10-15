/**
 * Created by prana on 12/7/2016.
 */

app.service('expenseService', function() {


    this.addExpense = function (data) {
        var promise = $.ajax({
            url: "http://localhost:8080/MoneyWizzard/rest/person/expense",
            type: "POST",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json"
        }).then(function (response) {
            return response;
        });
        return promise;
    };


    this.getExpenses = function (data) {
        var promise = $.ajax({
            url: "http://localhost:8080/MoneyWizzard/rest/person/"+data+"/expense",
            type: "GET",
            data: JSON.stringify(data),
            //contentType: "application/json",
            dataType: "json"
        }).then(function (response) {
            return response;
        });
        return promise;
    };
});