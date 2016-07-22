function config($stateProvider, $urlRouterProvider){

$urlRouterProvider.otherwise("/welcome");

 $stateProvider
        .state('welcome', {
            url: "/welcome",
            templateUrl: "/assets/views/welcome/welcome.html"
        })
}

angular
    .module('sairat')
    .config(config)