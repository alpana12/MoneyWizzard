/**
 * Created by prana on 12/6/2016.
 */

app.service('friendsService', function() {

    this.searchPerson = function (data) {
        var promise = $.ajax({
            url: "http://localhost:8080/MoneyWizzard/rest/person/"+data,
            type: "GET",
            //data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json"
        }).then(function (response) {
            return response;
        });
        return promise;
    };

    this.addFriend = function (personId,friendId) {
        var promise = $.ajax({
            url: "http://localhost:8080/MoneyWizzard/rest/person/"+personId+"/friend/"+friendId,
            type: "POST",
            //data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json"
        }).then(function (response) {
            return response;
        });
        return promise;
    };

    this.getFriends = function (personId) {
        var promise = $.ajax({
            url: "http://localhost:8080/MoneyWizzard/rest/person/"+personId+"/friends",
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