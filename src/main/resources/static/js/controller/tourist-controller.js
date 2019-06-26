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



    }]);