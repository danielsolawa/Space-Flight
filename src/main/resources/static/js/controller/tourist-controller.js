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



    }]);