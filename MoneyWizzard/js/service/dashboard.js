/**
 * Created by prana on 12/6/2016.
 */

/**
 * Created by prana on 12/4/2016.
 */

app.service('dashBoardService', function() {

    this.getReports = function (data) {
        var promise = $.ajax({
            url: "http://localhost:8080/MoneyWizzard/rest/person/"+data+"/reports",
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