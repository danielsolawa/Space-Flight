
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

        self.editFlight = function () {
            console.log("yoyo");
        }

        self.deleteFlight = function (id) {
            flightService.delete({id: id}, function() {
                self.loadData();
            }, function(error) {
                console.log("error");
            })
        }



    }]);