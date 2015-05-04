var  MurControllers= angular.module('MurControllers', []);

	MurControllers.controller('MurCtrl', ['$scope',
		function($scope){
			 $scope.contenu = "";	
			 $scope.publier = function(text, content1){
			 	$scope.contenu = content1;
				alert('publier statut' + text + " "+content1);
				var contentType = /image.*/;
				if(content1.match(contentType)) alert('true');
				else alert('false');
		}
}]);

/*
murControllers.controller('MurCtrl', ['$scope', 'ServiceMur','ServiceStatus',
  function($scope, ServiceMur,ServiceStatus) {
    //$scope.contenu=ServiceMur.query();
     $scope.publi=function(ServiceStatus){
         
       /* ServiceStatus.save(
            {
                status:$('#zonePublier').val(),
                id_user:1,
                date_modification:3/5/2009,
                date_publication:3/4/2008
            }
        );
     }
  }]);

function bricoulage(detail){
    var d=new Date();
 var jsObj = {status:""+detail,date_modification:""+d,date_publication:""+d,id_user:1}
	, ajaxObj = {};
	//console.log(jsObj);
	ajaxObj = {
	    type: "POST",
	    url: "http://localhost:8080/StatusService/api/status/update",
	    data: JSON.stringify(jsObj),
	    contentType:"application/json",
	    error: function(jqXHR, textStatus, errorThrown) {
		console.log("Error " + jqXHR.getAllResponseHeaders() + " " + errorThrown);
	    },
	    success: function(data) {
		console.log(data);
	    },
	    complete: function(XMLHttpRequest) {
		//console.log( XMLHttpRequest.getAllResponseHeaders() );
	    },
	    dataType: "json" //request JSON
	};
	$.ajax(ajaxObj);   
    
}*/



