angular.module('app').controller('registrationController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

    $scope.tryToReg = function () {
        $http.post(contextPath + '/api/v1/users/register', $scope.newUser)
            .then(function successCallback(response) {
                alert('Регистрация прошла успешно. Залогиньтесь.');
                $scope.newUser = null;
                window.location.href = '#!/';
            }, function errorCallback(response) {
                console.log(response.data);
                alert('Error: ' + response.data.messages);
            });
    };

});