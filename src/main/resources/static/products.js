angular.module('app', []).controller('indexController', function ($scope, $http) {
    console.log('start');
    $http.get('http://localhost:8189/shop/api/v1/products').then(function (response){
        console.log(response);
    })




});