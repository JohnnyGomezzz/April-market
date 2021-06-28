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

    $scope.saveMessage = function () {
         $http({
             url: contextPath + '/api/v1/feedback',
             method: 'POST',
             params: {
             prodId: $routeParams.productIdParam,
             message: $scope.message
             }
        }).then(function successCallback(response) {
            $scope.loadFeedback();
            }, function errorCallback(response) {
                 console.log(response.data);
                 alert('Error: ' + response.data.messages);
        });
    }

    $scope.loadFeedback = function () {
        $http({
            url: contextPath + '/api/v1/feedback',
            method: 'GET',
            params: {
                productId: $routeParams.productIdParam
            }
        }).then(function (response) {
            $scope.feedbacks = response.data;
        });
    };

    $scope.showFeedback = function () {
        $http({
            url: contextPath + '/api/v1/feedback' + $routeParams.productIdParam,
            method: 'GET',
        }).then(function (response) {
            $scope.feedback = response.data;
        });
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.aprilMarketCurrentUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.loadFeedback();
    $scope.loadProduct();
});