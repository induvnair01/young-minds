var app=angular.module('myApp');
app.controller('cartCntrl', cartCntrl);

function cartCntrl($scope, $modal,$rootScope,$location) {
	  
	  
	$scope.redirectToHome=function()
	{
		console.log('hi');
		$location.path("/successPage");
	}
	
	  $scope.user="Name";
	    $scope.phoneNumbr = /^\+?\d{2}[- ]?\d{3}[- ]?\d{5}$/;
	    
	$scope.login=function()
	{
		console.log('hi');
		$location.path("/successPage");
	}
	    
}
 app.directive('passwordValidate', function() {
    return {
        require: 'ngModel',
        link: function(scope, elm, attrs, ctrl) {
            ctrl.$parsers.unshift(function(viewValue) {

                scope.pwdValidLength = (viewValue && viewValue.length >= 8 ? 'valid' : undefined);
                scope.pwdHasLetter = (viewValue && /[A-z]/.test(viewValue)) ? 'valid' : undefined;
                scope.pwdHasNumber = (viewValue && /\d/.test(viewValue)) ? 'valid' : undefined;

                if(scope.pwdValidLength && scope.pwdHasLetter && scope.pwdHasNumber) {
                    ctrl.$setValidity('pwd', true);
                    return viewValue;
                } else {
                    ctrl.$setValidity('pwd', false);                    
                    return undefined;
                }

            });
        }
    };
});