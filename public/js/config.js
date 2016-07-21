function config($stateProvider, $urlRouterProvider){



 $stateProvider
        .state('welcome', {
            url: "/welcome",
            templateUrl: "views/welcome/welcome.html"
        })
}

angular
    .module('sairat')
    .config(config)