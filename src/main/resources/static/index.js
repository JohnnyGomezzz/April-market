angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.init = function () {
        $http.get(contextPath + '/api/v1/products')
            .then(function (response) {
                $scope.products = response.data;
                $scope.showCart;
                $scope.cartSum = 0;
            });
    };

    $scope.createNewProduct = function () {
        $http.post(contextPath + '/api/v1/products', $scope.newProduct)
            .then(function successCallback(response) {
                $scope.init();
                $scope.newProduct = null;
            }, function errorCallback(response) {
                console.log(response.data);
                alert('Error: ' + response.data.messages);
            });
    };

    $scope.clickOnProduct = function (product) {
        console.log(product);
    }

    $scope.showCart = function () {
            $http.get(contextPath + '/api/v1/cart')
                .then(function (response) {
                    $scope.items = response.data;
                    $scope.cartProductsSum();
                });
        };

    $scope.addProductToCart = function (productId) {
        $http({
                url: contextPath + '/api/v1/cart/add',
                method: 'GET',
                params: {
                    id: productId
                }
            }).then(function (response) {
                $scope.showCart();
                $scope.cartSum = response.data;
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
                    $scope.cartSum = response.data;
                });
        }

    $scope.clearCart = function (productId) {
        $http({
                url: contextPath + '/api/v1/cart/clear',
                method: 'GET'
            }).then(function (response) {
                $scope.showCart();
                $scope.cartSum = 0;
            });
    }

    $scope.cartProductsSum = function () {
        $http({
                url: contextPath + '/api/v1/cart/sum',
                method: 'GET'
            }).then(function (response) {
                $scope.cartSum = response.data;
            });
    }

    $scope.pingProduct = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/ping',
            method: 'GET',
            params: {
                id: productId,
                temp: 'empty'
            }
        }).then(function (response) {
            $scope.init();
        });
    }

    $scope.init();
});