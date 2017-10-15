/**
 * Created by prana on 12/8/2016.
 */

app.service('walletService', function() {

    this.getCurrentWalletBalance = function (data) {
        var promise = $.ajax({
            url: "http://localhost:8080/MoneyWizzard/rest/person/"+data+"/balance",
            type: "GET",
            //data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json"
        }).then(function (response) {
            return response;
        });
        return promise;
    };

    this.addMoney = function (data) {
        var promise = $.ajax({
            url: "http://localhost:8080/MoneyWizzard/rest/person/wallet",
            type: "POST",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json"
        }).then(function (response) {
            return response;
        });
        return promise;
    };



    this.getAccountHistory = function (data) {

        var promise = $.ajax({
            url: "http://localhost:8080/MoneyWizzard/rest/person/"+data+"/accountHistory",
            type: "GET",
            //data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json"
        }).then(function (response) {
            return response;
        });
        return promise;
    };

    this.transferMoney = function (data) {
        var promise = $.ajax({
            url: "http://localhost:8080/MoneyWizzard/rest/person/wallet/transferMoney",
            type: "POST",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json"
        }).then(function (response) {
            return response;
        });
        return promise;
    };

});