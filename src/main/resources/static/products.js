angular.module('app', []).controller('indexController', function ($scope, $http) {
        console.log('start');


        //без функции
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
//вызываем функцию
        $scope.loadProducts();

    }
)
;