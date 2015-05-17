$(document).ready(function() {
	
	var scope = angular.element(document.getElementById(('controller'))).scope();
	
	//get mur
	scope.$apply(function () {
    	scope.getMur();
    });
});