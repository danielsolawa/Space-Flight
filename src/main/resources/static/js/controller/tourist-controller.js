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




    }]);