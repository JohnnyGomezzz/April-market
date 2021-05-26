angular.module('app').controller('ordersController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

    $scope.showOrders = function () {
        $http({
            url: contextPath + '/api/v1/orders',
            method: 'GET'
        }).then(function (response) {
            $scope.myOrders = response.data;
        });
    };

    $scope.showOrders();
});