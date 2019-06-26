application.factory('flightService', function($resource){

    return $resource('api/flight/:id', { id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

});