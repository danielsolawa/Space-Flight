application.factory('addTouristService', function($resource){

    return $resource('api/flight/:id/add-tourist', { id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

});