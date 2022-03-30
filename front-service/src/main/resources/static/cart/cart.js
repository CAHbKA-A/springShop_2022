angular.module('market').controller('cartController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:5555/cart/';
    const coreContextPath = 'http://localhost:5555/core/';

    $scope.loadCart = function () {
        $http.get(contextPath + 'api/v1/cart').then(function (response) {
            $scope.cart = response.data;
            console.log($scope.user)
        });
    }

    $scope.clearCart = function () {
        $http.get(contextPath + 'api/v1/cart/clear').then(function (response) {
            $scope.loadCart();
        });
    }


    $scope.deleteItemFromCart = function (id) {
        $http.get(contextPath + 'api/v1/cart/deleteItem/' + id).then(function (response) {
            $scope.loadCart();
        });
    }


    //todo сохранение в локальное хранилище браузера телефона и адреса. или подтягивать из бд
    $scope.createOrder = function () {

        $http.post(coreContextPath+'api/v1/orders', $scope.user).then(function successCallback(response) { //HttpStatus.CREATED)
            //reload или перейти  на страницу заказов

            $scope.loadCart($scope.user);


        }, function errorCallback(response) {
            alert("error. не ввели адрес телефон или косяк на бэке")
        });

    };

    $scope.setCountItem = function (productId, count) {
        $http.get(contextPath + 'api/v1/cart/SetCountItem?id=' + productId + '&count=' + count).then(function (response) {
            $scope.loadCart($scope.user);
        });
    }



    $scope.loadCart($scope.user);
});