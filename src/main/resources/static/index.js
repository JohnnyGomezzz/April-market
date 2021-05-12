angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.cartSum = 0;

    $scope.loadPage = function (page) {
        $http({
            url: contextPath + '/api/v1/products',
            method: 'GET',
            params: {
                p: page
            }
        }).then(function (response) {
            $scope.productsPage = response.data;

            let minPageIndex = page - 2;
            if (minPageIndex < 1) {
                minPageIndex = 1;
            }

            let maxPageIndex = page + 2;
            if (maxPageIndex > $scope.productsPage.totalPages) {
                maxPageIndex = $scope.productsPage.totalPages;
            }

            $scope.paginationArray = $scope.generatePagesIndexes(minPageIndex, maxPageIndex);

            console.log("PAGE FROM BACKEND")
            console.log($scope.productsPage);
        });
    };

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.createNewProduct = function () {
        $http.post(contextPath + '/api/v1/products', $scope.newProduct)
            .then(function successCallback(response) {
                $scope.loadPage(1);
                $scope.newProduct = null;
            }, function errorCallback(response) {
                console.log(response.data);
                alert('Error: ' + response.data.messages);
            });
    }

    $scope.showCart = function (page) {
        $http({
            url: '/market/api/v1/cart',
            method: 'GET'
        }).then(function (response) {
            $scope.cartDto = response.data;
        });
    };

    $scope.addProductToCart = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/add/' + productId,
            method: 'GET'
        }).then(function (response) {
            $scope.showCart();
        });
    }

    $scope.deleteFromCart = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/delete',
            method: 'GET',
            params: {
                id: productId
            }
        }).then(function (response) {
            $scope.showCart();
        });
    }

    $scope.deleteProductFromCart = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/deleteall',
            method: 'GET',
            params: {
                id: productId
            }
        }).then(function (response) {
            $scope.showCart();
        });
    }

    $scope.clearCart = function () {
        $http({
            url: contextPath + '/api/v1/cart/clear',
            method: 'GET'
        }).then(function (response) {
            $scope.showCart();
        });
    }

    $scope.loadPage(1);
    $scope.showCart();
});