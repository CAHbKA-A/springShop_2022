angular.module('market').controller('orderConfirmationController', function ($scope, $http, $location, $localStorage) {

    const contextPath = 'http://localhost:5555/core/';
    $scope.loadCart = function () {
        $http({
            url: 'http://localhost:5555/cart/api/v1/cart/' + $localStorage.webMarketGuestCartId,
            method: 'GET'
        }).then(function (response) {
            $scope.cart = response.data;
        });
    }


    // $scope.createOrder = function () {
    //     console.log('createOrder');
    //     $http({
    //         url: 'http://localhost:5555/core/api/v1/orders',
    //         method: 'POST',
    //         data: $scope.orderDetails
    //     }).then(function (response) {
    //         var orderId = response.data.value;
    //         $location.path('/order_pay/' + orderId);
    //     });
    // };


    $scope.createOrder = function () {
        console.log('createOrder');
        $http.post(contextPath + 'api/v1/orders', $scope.user)
            .then(function successCallback(response) { //HttpStatus.CREATED)
         //    $scope.loadCart();

             console.log(response.data.value);

        }, function errorCallback(response) {
            alert("error. не ввели адрес телефон или косяк на бэке")
        });

    };





    $scope.loadCart = function () {
        $http({
            url: 'http://localhost:5555/cart/api/v1/cart/' + $localStorage.GBGuestCartId,
            method: 'GET'
        }).then(function (response) {
            $scope.cart = response.data;
        });
    };

    // console.log()
    $scope.loadCart($scope.user);

});