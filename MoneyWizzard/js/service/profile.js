/**
 * Created by prana on 12/6/2016.
 */

app.service('settingService', function() {
    /**
     * Function for Getting All urls
     * @returns {*}
     */
    this.updateUser = function (data) {
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