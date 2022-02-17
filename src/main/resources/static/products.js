angular.module('app', []).controller('indexController', function ($scope, $http) {
        console.log('start');



        $scope.loadProducts = function () {
            $http.get('http://localhost:8189/shop/api/v1/products').then(function (response) {
                $scope.productsList = response.data;
            });
        }



        $scope.showProductInfo = function (id) {
            $http.get('http://localhost:8189/shop/api/v1/products/' + id).then(function (response) {
                //вспывающее окно
                alert(response.data.description);
            });
        }


        $scope.deleteProductById = function (id) {
            $http.delete('http://localhost:8189/shop/api/v1/products/' + id).then(function (response) {
                //обновляем
                $scope.loadProducts();
            });
        }


        $scope.loadCart = function () {
            $http.get('http://localhost:8189/shop/api/v1/cart').then(function (response) {
                $scope.cart = response.data;
            });
        }


        // $scope.deleteProductFromCartById = function (id) {
        //     $http.get('http://localhost:8189/shop/api/v1/cart/delete/' + id).then(function (response) {
        //         $scope.loadCarts();
        //     });
        // }


        $scope.addToCart = function (productId) {
            $http.get('http://localhost:8189/shop/api/v1/cart/add/' + productId).then(function (response) {
                $scope.loadCart();
            });
        }



//вызываем функцию (список продуктов)
        $scope.loadProducts();
        $scope.loadCart();
        console.log('end');
    }
)