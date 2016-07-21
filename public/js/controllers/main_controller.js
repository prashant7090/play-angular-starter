function MainCtrl($state){

    console.log("I am from angular Controller")
    $state.go('welcome')

}

angular
    .module('sairat')
    .controller('MainCtrl', MainCtrl)