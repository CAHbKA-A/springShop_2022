(function () {
        angular
            .module('market', ['ngRoute', 'ngStorage'])
            .config(config)
            .run(run);
            console.log('start');


        //роуты
        function config($routeProvider) {
            $routeProvider
                .when('/', {
                    templateUrl: 'welcome/welcome.html',
                    controller: 'welcomeController'
                })
                .when('/store', {
                    templateUrl: 'store/store.html',
                    controller: 'storeController'
                })
                .when('/cart', {
                    templateUrl: 'cart/cart.html',
                    controller: 'cartController'
                })
                .when('/order', {
                    templateUrl: 'order/order.html',
                    controller: 'orderController'
                })
                .otherwise({
                    redirectTo: '/'
                });
        }

    function run($rootScope, $http, $localStorage) {
        //при загрузе страницы проверяем, есть ли данные о юзере в локальном хранилище и не просрочен ли токен/
        if ($localStorage.ownUser) {
            try {
                let jwt = $localStorage.ownUser.token; //выдернули токен
                let payload = JSON.parse(atob(jwt.split('.')[1])); //разбили на состовляющие
                let currentTime = parseInt(new Date().getTime() / 1000); //текущее время

                if (currentTime > payload.exp) {  //проверяем не истекло ли время токена
                    console.log("Token is expired! Please login again.");
                    delete $localStorage.ownUser; //если истекло, вычищаем из хранилища и из хедера
                    $http.defaults.headers.common.Authorization = '';
                }
            } catch (e) {
            }

            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.ownUser.token; //если токен актуален, вставляем в хедер
        }
        //проверяем в локальном хранилище наличие id корзины(дефолтное, не привязанное к юзернейму. если нет, генерим и записываем
        if (!$localStorage.GBGuestCartId) {
            $http.get('http://localhost:5555/cart/api/v1/cart/default_uuid')
                .then(function successCallback(response) {
                    $localStorage.GBGuestCartId = response.data.value;
                });
        }
        console.log('end');
    }
})();

angular.module('market').controller('indexController', function ($rootScope, $scope, $http, $location, $localStorage) {

        //функция авторизации (нажали кнопку войти
        $scope.tryToAuth = function () {
            // console.log($scope.user);
            $http.post('http://localhost:5555/auth/auth', $scope.user) //адрес аутентификации. отправляем объект user.
                .then(function successCallback(response) { //если  авторизация прошла успешно (с бэка пришел ответ положительный)

                    if (response.data.token) { //выдергиваем из ответа токен.если получили токен
                        $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;//добавляем в дефолтный херед. чтобы браузер всегда отправлял токен
                        //кладем в локальное хранилище браузера, чтобы не терять при передапуске браузера или обновлении страницы.
                        //храниться в виде мапы ownUser .токен храниться с ключом username. !!в браузере надо влкючить хранилище (в index.html библиотеку ngStorage.min.js
                        $localStorage.ownUser = {username: $scope.user.username, token: response.data.token};
                        $scope.user.username = null;  //убираем за собой
                        $scope.user.password = null;
                    }
                }, function errorCallback(response) {//если с бэка пришел error
                });
        };

        //функция авторизации (нажали кнопку выйти
        $scope.tryToLogout = function () {
            $scope.clearUser();//вычищаем инфу о пользователе
            $scope.user = null;
        };

        $scope.clearUser = function () { //вычищаем пользователя из браузера
            delete $localStorage.ownUser; //удаялем локальню переменую  из хронилища браузера
            $http.defaults.headers.common.Authorization = '';//очищаем заголовок (удаляем токен)
        };


        //функционал проверки статуса юзера (залогинен или нет). проверяем по наличию перемнной в локальнмо хранилище браузера
        $scope.isUserLoggedIn = function () {

            if ($localStorage.ownUser) {
                return true;
            } else {
                return false;
            }
        };

        //функционал запроса своего логина (проверка авторизации)
        $scope.authCheck = function () {
            $http.get('http://localhost:5555/core/auth_check').then(function (response) {
                alert(response.data.value); //высплывающее окно с полученой строкой
            });
        };



    // $scope.loadCart = function () {
    //     $http.get(contextPath + 'api/v1/cart').then(function (response) {
    //         $scope.cart = response.data;
    //         //  console.log($scope.user)
    //     });
    // }
});
