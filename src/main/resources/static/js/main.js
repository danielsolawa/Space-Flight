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
            });

        $urlRouterProvider.otherwise('/');

        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
        $httpProvider.defaults.headers.common['Accept'] = 'application/json';
        $locationProvider.html5Mode(false);
    }]);