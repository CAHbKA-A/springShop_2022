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
        $http.get(cartContextPath + 'api/v1/cart/' + $scope.GBGuestCartId + '/add/' + productId).then(function (response) {

        });
    }


    $scope.loadProducts =   function (pageIndex = 1) {
        $http({
            url: contextPath + 'api/v1/products',
            method: 'GET',
            params: {
                p: pageIndex,
                // min_price: $scope.filter ? $scope.filter.min_price: null,
                 min_price: $scope.filter ? $scope.filter.minPrice: null,
                max_price: $scope.filter ? $scope.filter.maxPrice: null,
                title: $scope.filter ? $scope.filter.textSearch: null

            }
        }).then(function (response) {
            $scope.productsPage = response.data;
            $scope.generatePagesList($scope.productsPage.totalPages);
        });
        console.log($scope.filter);
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
            if ($scope.productsList.length == 0) {
                $scope.changePage(-1)

            }

        });
    }


    $scope.generatePagesList = function (totalPages) {
        out = [];
        for (let i = 0; i < totalPages; i++) {
            out.push(i + 1);
        }
        $scope.pagesList = out;
    }


    $scope.loadProducts();

})
;