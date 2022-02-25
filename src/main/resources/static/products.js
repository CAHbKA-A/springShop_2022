//'ngStorage' - подключени модуля для локального хранилища
angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {
        //включаем функции $scope -для переменных, $http-для обработки хтмл, $localStorage - для локального хранения
        console.log('start');

 /* Функции*/


       //функция авторизации (нажали кнопку войти
        $scope.tryToAuth = function () {
            $http.post('http://localhost:8189/shop/auth', $scope.user) //адрес аутентификации. отправляем объект user.
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

        $scope.clearUser = function () { //вычиoаем пользователя из браузера
            delete $localStorage.ownUser; //удаялем локальню переменую  из хронилища браузера
            $http.defaults.headers.common.Authorization = '';//очищаем заоголовок (уалем токен)
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
            $http.get('http://localhost:8189/shop/auth_check').then(function (response) {
                alert(response.data.value); //высплывающее окно с полученой строкой
            });
        };


        $scope.loadProducts = function () {
            $http.get('http://localhost:8189/shop/api/v1/products').then(function (response) {
                $scope.productsList = response.data;
            });
        }


        $scope.showProductInfo = function (id) {
            $http.get('http://localhost:8189/shop/api/v1/products/' + id).then(function (response) {
                //вспывающее окно
                alert(response.data.description);
            });
        }


        $scope.deleteProductById = function (id) {
            $http.delete('http://localhost:8189/shop/api/v1/products/' + id).then(function (response) {
                //обновляем
                $scope.loadProducts();
            });
        }


        $scope.loadCart = function () {
            $http.get('http://localhost:8189/shop/api/v1/cart').then(function (response) {
                $scope.cart = response.data;

            });
        }


        $scope.deleteItemFromCart = function (id) {
            $http.get('http://localhost:8189/shop/api/v1/cart/deleteItem/' + id).then(function (response) {
                $scope.loadCart();
            });
        }


        $scope.addToCart = function (productId) {
            $http.get('http://localhost:8189/shop/api/v1/cart/add/' + productId).then(function (response) {
                $scope.loadCart();
            });
        }

        $scope.setCountItem = function (productId, count) {
            $http.get('http://localhost:8189/shop/api/v1/cart/SetCountItem?id=' + productId + '&count=' + count).then(function (response) {
                $scope.loadCart();
            });
        }


        $scope.clearCart = function () {
            $http.get('http://localhost:8189/shop/api/v1/cart/clear').then(function (response) {
                $scope.loadCart();
            });
        }


        $scope.createOrder = function () {
            $http.post('http://localhost:8189/shop/api/v1/orders').then(function (response) {
                $scope.loadCart();
            });
        }





        $scope.createOrder = function () {

            $http.post('http://localhost:8189/shop/api/v1/orders', $scope.user)
                .then(function successCallback(response) { //HttpStatus.CREATED)
                //reload или перейти  на страницу заказов
                }, function errorCallback(response) {
                    alert("need to login")
                });
        };



        /*действия при подключении JS*/

//при загрузе страницы проверяем,еcть ли данные о юзере в локальном хранилище и не просрочен ли токен/
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


//вызываем список продуктов
        $scope.loadProducts();
//вызываем корзину
        $scope.loadCart();

        console.log('end');
    }
)