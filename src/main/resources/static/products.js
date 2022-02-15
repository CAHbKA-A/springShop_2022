angular.module('app', []).controller('indexController', function ($scope, $http) {
        console.log('start');


// создаем функцию упаковки ответного data в переменную productsList
        $scope.loadProducts = function () {
            $http.get('http://localhost:8189/shop/api/v1/products').then(function (response) {
                $scope.productsList = response.data;
            });
        }


//функция получения одного объекта по id
        $scope.showProductInfo = function (id) {
            $http.get('http://localhost:8189/shop/api/v1/products/' + id).then(function (response) {
                //вспывающее окно
                alert(response.data.description);
            });
        }

//функция удаления одного продукта по id
        $scope.deleteProductById = function (id) {
            $http.delete('http://localhost:8189/shop/api/v1/products/' + id).then(function (response) {
                //обновляем
                $scope.loadProducts();
            });
        }


//Удаление из корзины
        $scope.deleteProductFromCartById = function (id) {
            $http.delete('http://localhost:8189/shop/api/v1/carts/' + id).then(function (response) {
                //обновляем
                $scope.loadCarts();
            });
        }


        //функция добавления в корзину2/

        $scope.addProductToCartById = function (id) {
            $http.get('http://localhost:8189/shop/api/v1/products/add-to-cart2/' + id).then(function (response) {
                $scope.loadCarts();
            });
        }


// получаем cartsList. пока по всем юзерам
        $scope.loadCarts = function () {
            $http.get('http://localhost:8189/shop/api/v1/carts/').then(function (response) {

                $scope.cartList = response.data;

            });
        }


//вызываем функцию (список продуктов)
        $scope.loadProducts();
        $scope.loadCarts();
        console.log('end');
    }
)