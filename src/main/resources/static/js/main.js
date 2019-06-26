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
            });

        $urlRouterProvider.otherwise('/');

        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
        $httpProvider.defaults.headers.common['Accept'] = 'application/json';
        $locationProvider.html5Mode(false);
    }]);