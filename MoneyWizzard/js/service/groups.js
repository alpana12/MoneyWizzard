/**
 * Created by prana on 12/7/2016.
 */

app.service('groupService', function() {

    this.addGroup = function (data) {
        var promise = $.ajax({
            url: "http://localhost:8080/MoneyWizzard/rest/group",
            type: "POST",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json"
        }).then(function (response) {
            return response;
        });
        return promise;
    };

    this.getGroups = function (data) {
        var promise = $.ajax({
            url: "http://localhost:8080/MoneyWizzard/rest/person/"+data+"/group",
            type: "GET",
            //data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json"
        }).then(function (response) {
            return response;
        });
        return promise;
    };
});