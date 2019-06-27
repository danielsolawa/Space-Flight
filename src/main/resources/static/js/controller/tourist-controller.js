application
    .controller('tourists',[ 'touristService',  function(touristService){
        var self = this;


        self.loadData = function(){

            touristService.get(function(response) {
                self.tourists = response.tourists;
            }, function(error) {
                console.log("error");
            });
        }

        self.editTourist = function () {
            console.log("yoyo");
        }

        self.deleteTourist = function (id) {
            touristService.delete({id: id}, function() {
                self.loadData();
            }, function(error) {
                console.log("error");
            })
        }



    }])
    .controller('touristAdd', ['touristService', '$location', function(touristService, $location){
        var self = this;
        self.error = false;
        self.tourist= {};
        self.errorMessage ="";

        self.add = function() {
            self.error = false;

            touristService.save(self.tourist, function(){
                console.log("The tourist has been added successfully.");
                $location.path("/tourist");
            }, function(error) {
                console.log("An error has occurred.");
                self.errorMessage = error.data.message;
                self.error=true;
            });
        }




    }])
    .controller('touristUpdate', ['touristService', '$location', '$transition$',
        function(touristService, $location, $transition$){
        var self = this;
        self.error = false;
        self.errorMessage ="";
        self.id = 0 ;


        self.loadData = function(){
            self.id = $transition$.params().id;

            touristService.get({id: self.id}, function(response) {
                self.tourist = response;

            }, function(error) {
                console.log("error");
            });
        }

        self.update = function() {
            self.error = false;

            touristService.update({id: self.id}, self.tourist, function(){
                console.log("The tourist has been updated successfully.");
                $location.path("/tourist");
            }, function(error) {
                console.log("An error has occurred.");
                self.errorMessage = error.data.message;
                self.error=true;
            });
        }




    }])
    .controller('touristDetails', ['touristService', 'removeTouristService', '$location', '$transition$',
        function(touristService, removeTouristService, $location, $transition$){
            var self = this;

            self.id = 0 ;
            self.tourist = {};

            self.loadData = function(){
                self.id = $transition$.params().id;

                touristService.get({id: self.id}, function(response) {
                    self.tourist = response;

                }, function(error) {
                    console.log("error");
                });
            }

            self.removeFromList = function(flightId){

                removeTouristService.update({id: flightId}, self.id, function(){
                    self.loadData();
                    self.success = true;
                    self.message = "The flight has been removed successfully!";
                }, function(error){
                    console.log("error");
                });
            }



        }])
    .controller('addFlight', ['flightService', 'touristService', 'addTouristService', '$location', '$transition$',
        function(flightService, touristService, addTouristService, $location, $transition$){
            var self = this;
            self.error = false;
            self.errorMessage ="";
            self.id = 0 ;
            self.flights = [];
            self.tourist = {};



            self.loadData = function(){
                self.id = $transition$.params().id;

                touristService.get({id: self.id}, function(response) {
                    self.tourist = response;

                    flightService.get( function(response) {
                        self.flights = response.flights;
                        filterFlights(self.tourist.flights, self.flights);


                    }, function(error) {
                        console.log("error");
                    });

                }, function(error) {
                    self.error = true;
                    self.errorMessage = "Tourist with the given id was not found.";
                });







            }

            self.addFlight = function(flightId, seatsAvailable){
                if(seatsAvailable == 0){
                    self.error = true;
                    self.errorMessage = "There are no available seats in this flight. Please choose another one.";

                    return;
                }

                addTouristService.update({id: flightId}, self.id, function(){
                    $location.path("/tourist-details/" + self.id);
                }, function(error){
                    console.log(error);
                });

            }

            var filterFlights = function (touristFlights, flights){
                var set = new Set();

                for(var i = 0; i < touristFlights.length; i++){
                    set.add(touristFlights[i].id);
                }

                var list = [];

                for(var i = 0; i < flights.length; i++){
                    if(!set.has(flights[i].id)){
                        list.push(flights[i]);
                    }
                }


                self.flights = list;
            }



        }]);