angular.module('market').controller('orderController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:5555/core/';

    $scope.loadOrders = function () {
        $http.get(contextPath+'api/v1/orders').then(function (response) {
            $scope.orderList = response.data;
            console.log($scope.orderList);
        });
    }

    $scope.orderPay = function (orderId) {

        $http.get(contextPath+'order_pay/'+orderId).then(function (response) {
            console.log(orderId);
          //  $scope.orderList = response.data;
        });
    };


    // $scope.checkOut = function (orderId) {
    //     console.log('checkOut'+orderId);
    //     $location.path('/order_confirmation');
    // }
    //
    // $scope.disabledCheckOut = function () {
    //     alert("Для оформления заказа необходимо войти в учетную запись");
    // }


    $scope.loadOrders();

});

