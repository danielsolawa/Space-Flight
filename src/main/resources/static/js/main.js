var application = angular.module('space-flight', ['ngResource', 'ui.router']);

application.config(['$httpProvider', '$locationProvider', '$stateProvider', '$urlRouterProvider',
    function ($httpProvider, $locationProvider, $stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home',{
                url: '/',
                views: {
                    'main': {
                        templateUrl: 'home.html',
                        controller: 'flights',
                        controllerAs: 'controller'
                    }
                }
            })
            .state('tourist', {
                url: '/tourist',
                views: {
                    'main': {
                        templateUrl: 'tourists.html',
                        controller: 'tourists',
                        controllerAs: 'controller'
                    }
                }
            })
            .state('tourist-add', {
                url: '/tourist-add',
                views: {
                    'main': {
                        templateUrl: 'tourist-add.html',
                        controller: 'touristAdd',
                        controllerAs: 'controller'
                    }
                }
            })
            .state('tourist-update', {
                url: '/tourist-update/{id}',
                views: {
                    'main': {
                        templateUrl: 'tourist-update.html',
                        controller: 'touristUpdate',
                        controllerAs: 'controller'
                    }
                }
            })
            .state('tourist-details', {
                url: '/tourist-details/{id}',
                views: {
                    'main': {
                        templateUrl: 'tourist-details.html',
                        controller: 'touristDetails',
                        controllerAs: 'controller'
                    }
                }
            })
            .state('add-flight', {
                url: '/tourist/{id}/add-flight',
                views: {
                    'main': {
                        templateUrl: 'add-flight.html',
                        controller: 'addFlight',
                        controllerAs: 'controller'
                    }
                }
            })
            .state('flight-add', {
                url: '/flight-add',
                views: {
                    'main': {
                        templateUrl: 'flight-add.html',
                        controller: 'flightAdd',
                        controllerAs: 'controller'
                    }
                }
            })
            .state('flight-update', {
                url: '/flight-update/{id}',
                views: {
                    'main': {
                        templateUrl: 'flight-update.html',
                        controller: 'flightUpdate',
                        controllerAs: 'controller'
                    }
                }
            })
            .state('flight-details', {
                url: '/flight-details/{id}',
                views: {
                    'main': {
                        templateUrl: 'flight-details.html',
                        controller: 'flightDetails',
                        controllerAs: 'controller'
                    }
                }
            })
            .state('add-tourist', {
                url: '/flight/{id}/add-tourist',
                views: {
                    'main': {
                        templateUrl: 'add-tourist.html',
                        controller: 'addTourist',
                        controllerAs: 'controller'
                    }
                }
            });

        $urlRouterProvider.otherwise('/');

        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
        $httpProvider.defaults.headers.common['Accept'] = 'application/json';
        $locationProvider.html5Mode(false);
    }]);