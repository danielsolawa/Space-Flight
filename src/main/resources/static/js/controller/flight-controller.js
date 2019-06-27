
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




        }])
       .controller('flightDetails', ['flightService', 'removeTouristService', '$location', '$transition$',
    function(flightService,removeTouristService,  $location, $transition$){
        var self = this;
        self.error = false;
        self.errorMessage ="";
        self.id = 0 ;
        self.flight = {};


        self.loadData = function(){
            self.id = $transition$.params().id;

            flightService.get({id: self.id}, function(response) {
                self.flight = response;

            }, function(error) {
                console.log("error");
            });
        }

        self.addToList = function(seatsAvailable){
            console.log("here!");
            if(seatsAvailable === 0){
                console.log("here!");
                self.error = true;
                self.errorMessage = "There are no available seats in this flight. Please choose another one.";
                return;
            }

            $location.path("/flight/" + self.id + "/add-tourist");
        }

        self.removeFromList = function(touristId){

            removeTouristService.update({id: self.id}, touristId, function(){
               self.loadData();
               self.success = true;
               self.message = "The tourist has been removed successfully!";
            }, function(error){
                console.log("error");
            });
        }


    }])
    .controller('addTourist', ['flightService', 'touristService', 'addTouristService', '$location', '$transition$',
        function(flightService, touristService, addTouristService, $location, $transition$){
            var self = this;
            self.error = false;
            self.errorMessage ="";
            self.id = 0 ;
            self.tourists = [];
            self.flight = {};



            self.loadData = function(){
                self.id = $transition$.params().id;

                flightService.get({id: self.id}, function(response) {
                    self.flight = response;

                    touristService.get( function(response) {
                        self.tourists = response.tourists;
                        filterTourists(self.flight.tourists, self.tourists);


                    }, function(error) {
                        console.log("error");
                    });

                }, function(error) {
                    self.error = true;
                    self.errorMessage = "Flight with the given id was not found.";
                });







            }

            self.addTourist = function(touristId){

                console.log("adding tourist.");
                addTouristService.update({id: self.id}, touristId, function(){
                    $location.path("/flight-details/" + self.flight.id);
                }, function(error){
                    console.log("error");
                });

            }

            var filterTourists = function(flightTourists, tourists){
                var set = new Set();

                for(var i = 0; i < flightTourists.length; i++){
                    set.add(flightTourists[i].id);
                }

                var list = [];

                for(var i = 0; i < tourists.length; i++){
                    if(!set.has(tourists[i].id)){
                        list.push(tourists[i]);
                    }
                }


                self.tourists = list;
            }



        }]);