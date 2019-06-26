application.factory('touristService', function($resource){

    return $resource('api/tourist/:id', { id: '@_id'}, {
        update: {
            method: 'PUT'
        }
    });

});