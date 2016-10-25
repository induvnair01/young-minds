angular.module('myApp', [ 'ngRoute', 'ui.bootstrap', 'ionic' ]).config(config);
function config($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'homePage.html',
		controller : 'myCntrl'
	});

	$routeProvider.when('/register', {
		templateUrl : 'register.html',
		controller : 'myRegisterCntrl'
	});
	$routeProvider.when('/successPage', {
		templateUrl : 'successPage.html',
		controller : 'successCntrl'

	});

	$routeProvider.when('/viewWallet', {
		templateUrl : 'walletView.html',
		controller : 'walletCntrl'

	});
	$routeProvider.when('/viewCart', {
		templateUrl : 'myCartView.html',
		controller : 'cartCntrl'

	});
	$routeProvider.when('/viewProducts', {
		templateUrl : 'productView.html',
		controller : 'productCntrl'

	});
}
