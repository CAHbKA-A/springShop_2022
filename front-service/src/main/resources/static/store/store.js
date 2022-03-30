angular.module('market').controller('storeController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:5555/core/';
    const cartContextPath = 'http://localhost:5555/cart/';
    $scope.currentPage = 1;

    $scope.showProductInfo = function (productId) {
        $http.get(contextPath + 'api/v1/products/' + productId).then(function (response) {
            alert(response.data.description);
        });
    }


    $scope.addToCart = function (productId) {
        $http.get(cartContextPath + 'api/v1/cart/add/' + productId).then(function (response) {

        });
    }


    $scope.loadProducts = function (minPrice, maxPrice, textSearch) {

        $http.get(contextPath + 'api/v1/products',
            {
                params: {
                    min_price: minPrice,
                    max_price: maxPrice,
                    title: textSearch,

                }
            }
        ).then(function (response) {

            $scope.productsList = response.data;

        });
    }


    $scope.deleteProductById = function (id) {
        $http.delete(contextPath + 'api/v1/products' + id).then(function (response) {
            $scope.loadProducts();
        });
    }

    $scope.changePage = function (page) {

        $scope.currentPage = $scope.currentPage + page;
        if ($scope.currentPage < 1) {
            $scope.currentPage = 1;
        }
        $http.get(contextPath + 'api/v1/products',
            {
                params: {
                    p: $scope.currentPage
                }
            }
        ).then(function (response) {

            $scope.productsList = response.data;
            if($scope.productsList.length ==0){
                $scope.changePage(-1)

            }

        });
    }
    // $scope.filter = function () {
    //     console.log("filter");
    //     $http.post(contextPath + 'api/v1/products/filter', $scope.filterData).then(function (response) {
    //         $scope.productsList = response.data;
    //
    //     });
    // }


    $scope.loadProducts();

})
;