/**
 * Created by prana on 12/4/2016.
 */

app.service('homeService', function() {


        this.loginUser = function (data) {
            var promise = $.ajax({
                url: "http://localhost:8080/MoneyWizzard/rest/person/authenticate",
                type: "POST",
                data: JSON.stringify(data),
                contentType: "application/json",
                dataType: "json"
            }).then(function (response) {
                return response;
            });
            return promise;
        };

    this.signUp = function (data) {
        var promise = $.ajax({
            url: "http://localhost:8080/MoneyWizzard/rest/person/create",
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