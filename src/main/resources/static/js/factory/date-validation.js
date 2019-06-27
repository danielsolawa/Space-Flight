application.factory("dateValidationFactory", function() {
    var validate = {};
    var dateRegex = new RegExp("^\\d\\d\\d\\d-(0?[1-9]|1[0-2])-(0?[1-9]|[12][0-9]|3[01])$");
    var dateTimeRegex = new RegExp("^\\d\\d\\d\\d-(0?[1-9]|1[0-2])-(0?[1-9]|[12][0-9]|3[01]) (0?[0-9]|1[0-9]|2[0-3]):([0-9]|[0-5][0-9])$");


    validate.Date = function(date) {
        return dateRegex.test(date);
    };

    validate.DateTime = function(dateTime) {
        return dateTimeRegex.test(dateTime)
    };



    return validate;
});