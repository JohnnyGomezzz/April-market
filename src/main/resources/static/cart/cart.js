angular.module('app').controller('cartController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

    $scope.showCart = function (page) {
        $http({
            url: contextPath + '/api/v1/cart',
            method: 'GET'
        }).then(function (response) {
            $scope.cartDto = response.data;
            $scope.cartSum = 0;
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
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.aprilMarketCurrentUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.placeOrder = function (address, phone) {
        $http({
             url: contextPath + '/api/v1/orders',
             method: 'POST',
             params: {
                 address: address,
                 phone: phone
             }
         }).then(function (response) {
             $scope.showCart();
             alert('Заказ успешно оформлен');
         });
    };

    $scope.showCart();
});