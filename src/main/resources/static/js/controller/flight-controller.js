
application
    .controller('flights',[ 'flightService',  function(flightService){
        var self = this;


        self.loadData = function(){

            flightService.get(function(response) {
                self.flights = response.flights;
            }, function(error) {
                console.log("error");
            });
        }



    }]);