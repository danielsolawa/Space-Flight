application.factory('removeTouristService', function($resource){

    return $resource('api/flight/:id/remove-tourist', { id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

});