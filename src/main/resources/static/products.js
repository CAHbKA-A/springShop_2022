angular.module('app', []).controller('indexController', function ($scope, $http) {
        console.log('start');


        //без функции. пример запроса
        /*   $http.get('http://localhost:8189/shop/api/v1/products').then(function (response){
              //вывод в кносоль json
               // console.log(response.data);
               //переменная. кладем в нее json .  let - объявление переменной
               //let products = response.data;
               //console.log(products[0].title)
               //создаем переменную productsList и кладем в скоуп, чтобы потом достать на html. в переменную кладем json
               $scope.productsList = response.data;
       */

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


// получаем cartsList. пока по всем юзерам
        $scope.loadCarts = function () {
            $http.get('http://localhost:8189/shop/api/v1/carts').then(function (response) {
                $scope.cartList = response.data;
            });
        }
//функция добавления в корзину

        $scope.addProductToCartById = function (id) {
            $http.get('http://localhost:8189/shop/api/v1/products/add-to-cart/' + id).then(function (response) {
               $scope.loadCarts();
            });
        }

//Удаление из корзины
         $scope.deleteProductFromCartById = function (id) {
            $http.delete('http://localhost:8189/shop/api/v1/carts/' + id).then(function (response) {
                //обновляем
                $scope.loadCarts();
            });
        }


        //
        // //функция добавления в корзину2/
        //
        // $scope.add = function (id) {
        //     $http.get('http://localhost:8189/shop/api/v1/products/add-to-cart2/' + id).then(function (response) {
        //         $scope.loadCarts();
        //     });
        // }
        //







//вызываем функцию (список продуктов)
        $scope.loadProducts();
        $scope.loadCarts();
        console.log('end');
    }
)