
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



    }])
    .controller('flightAdd', ['flightService', '$location', function (flightService, $location) {
        var self = this;
        self.error = false;
        self.flight= {};
        self.errorMessage ="";

        self.add = function() {
            self.error = false;

            flightService.save(self.flight, function(){
                console.log("The flight has been added successfully.");
                $location.path("/");
            }, function(error) {
                console.log("An error has occurred.");
                self.errorMessage = error.data.message;
                self.error=true;
            });
        }
    }])
    .controller('flightUpdate', ['flightService', '$location', '$transition$',
        function(flightService, $location, $transition$){
            var self = this;
            self.error = false;
            self.errorMessage ="";
            self.id = 0 ;


            self.loadData = function(){
                self.id = $transition$.params().id;

                flightService.get({id: self.id}, function(response) {
                    self.flight = response;

                }, function(error) {
                    console.log("error");
                });
            }

            self.update = function() {
                self.error = false;

                flightService.update({id: self.id}, self.flight, function(){
                    console.log("The flight has been updated successfully.");
                    $location.path("/");
                }, function(error) {
                    console.log("An error has occurred.");
                    self.errorMessage = error.data.message;
                    self.error=true;
                });
            }




        }]);