angular.module('app').controller('productInfoController', function ($scope, $http, $localStorage, $routeParams) {
    const contextPath = 'http://localhost:8189/market';

    $scope.loadProduct = function () {
        $http({
            url: contextPath + '/api/v1/products/' + $routeParams.productIdParam,
            method: 'GET'
        }).then(function (response) {
            $scope.prod = response.data;
        });
    };

    $scope.placeFeedback = function (message) {
        $http({
             url: contextPath + '/api/v1/feedback',
             method: 'POST',
             params: {
                 message: message
             }
         }).then(function (response) {
             $scope.showFeedback();
         });
    };

    $scope.showFeedback = function () {
        $http({
            url: contextPath + '/api/v1/feedback',
            method: 'GET'
        }).then(function (response) {
            $scope.feedbacks = response.data;
        });
    };

    $scope.showFeedback();
    $scope.loadProduct();
});