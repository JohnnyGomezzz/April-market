angular.module('app').controller('productsController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

    $scope.isUserLoggedIn = function () {
        if ($localStorage.aprilMarketCurrentUser) {
            return true;
        } else {
            return false;
        }
    };

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

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.showOrders = function () {
        $http({
            url: contextPath + '/api/v1/orders',
            method: 'GET'
        }).then(function (response) {
            $scope.myOrders = response.data;
        });
    };

    if ($scope.isUserLoggedIn()) {
        $scope.showOrders();
    }

    $scope.loadPage(1);
});