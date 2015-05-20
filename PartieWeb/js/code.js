$(document).ready(function() {
	
	var scope = angular.element(document.getElementById(('controller'))).scope();
	
	//get mur
	scope.$apply(function () {
    	scope.getMur();
    });
    
    scope.currentUser = new UtilisateurCourant(1, "afifa", null, "ba_rih@esi.dz");
    
    function onSignIn(googleUser) {
	  var profile = googleUser.getBasicProfile();
	  scope.currentUser = new UtilisateurCourant(
	  		profile.getId(),
	  		profile.getName(),
	  		profile.getImageUrl(),
	  		profile.getEmail()
	  )
	  console.log('ID: ' + profile.getId());
	  console.log('Name: ' + profile.getName());
	  console.log('Image URL: ' + profile.getImageUrl());
	  console.log('Email: ' + profile.getEmail());
	}
	function envoyerToken(authResponse){
	
		var ajaxObj = {};
		//console.log(jsObj);
		ajaxObj = {
		    type: "POST",
		    url: "http://localhost:8080/StatusService/api/test/auth",
		    data: JSON.stringify(authResponse),
		    contentType:"application/json",
		    error: function(jqXHR, textStatus, errorThrown) {
			console.log("Error " + jqXHR.getAllResponseHeaders() + " " + errorThrown);
		    },
		    success: function(data) {
			console.log(data);
		    },
		    complete: function(XMLHttpRequest) {
			console.log( XMLHttpRequest.getAllResponseHeaders() );
		    },
		    dataType: "json" //request JSON
		};
		$.ajax(ajaxObj);
	}	
	
	var GoogleAuth=gapi.auth2.getAuthInstance();
	onSignIn(GoogleAuth.currentUser.get());
	var GoogleUser=GoogleAuth.currentUser.get();
	var authRsponse=GoogleUser.getAuthResponse();
	envoyerToken(authRsponse);
	var x=3+2;
});