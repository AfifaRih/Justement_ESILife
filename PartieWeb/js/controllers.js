var  MurControllers= angular.module('MurControllers', []);

	MurControllers.controller('MurCtrl', ['$scope',
		function($scope){
			 $scope.contenu = "";	
			 $scope.file;
			 $scope.adr = "192.168.173.238";
			
			$scope.publier = function(text, content){
	 		      alert('publier');
	 		      $scope.file = content;
			      alert('publier statut' + text + " "+content);
			      var contentType = /image.*/;
			      if(content.type.match(contentType)) alert('true');
			      else alert('false');


			      var XHR = new XMLHttpRequest();
			      var FD  = new FormData();
			      var jsObj = {id_user:1,status:text,date_publication:"1/1/2001",date_modification:"2/2/2002",type:"le     walou"};
			      // We define what will happen if the data are successfully sent
			      XHR.addEventListener('load', function(event) {
				  alert('Yeah! Data sent and response loaded.');
			      });

			      // We define what will happen in case of error
			      XHR.addEventListener('error', function(event) {
				  alert('Oups! Something goes wrong.');
			      });
			      // We setup our request
			      FD.append("file",content);
			      FD.append("status",JSON.stringify(jsObj));
			      XHR.open('POST', 'http://'+$scope.adr+':8080/StatusService/api/status/update');
			      // We just send our FormData object, HTTP headers are set automatically
			      XHR.send(FD);
			  }
			
			$scope.modifier = function(_id_contenu, _new_contenu){
				alert('modifier '+_id_contenu + " "+_new_contenu);
				var jsObj = {new_contenu:_new_contenu,id_contenu:_id_contenu}
			      , ajaxObj = {};
			      ajaxObj = {
					  type: "POST",
					  url: "http://"+$scope.adr+":8080/StatusService/api/status/modifier",
					  data: JSON.stringify(jsObj),
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
			
			$scope.commenter = function(id, text){
				alert('commenter id '+id+' text '+text);
			}
			
			$scope.aimer = function(id, bool){
				alert('aimer id '+id+' bool '+bool);
			}
			
			$scope.supprimer = function(_id_status){
				alert('supprimer '+_id_status);
			      var jsObj = {id_status:_id_status}
			      , ajaxObj = {};
			      ajaxObj = {
				  type: "POST",
				  url: "http://"+$scope.adr+":8080/StatusService/api/status/supprimer",
				  data: JSON.stringify(jsObj),
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
			
			$scope.suivre = function(_id_followed){
				alert('suivre '+_id_followed);
			      var jsObj = {id_follower:2,id_followed:_id_followed}
			      , ajaxObj = {};
			      ajaxObj = {
				  type: "POST",
				  url: "http://"+$scope.adr+":8080/StatusService/api/utilisateur/suivre",
				  data: JSON.stringify(jsObj),
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
			
			$scope.moderer = function(_contenu_id, _accepter){
				alert('moderer id '+_contenu_id+' bool '+_accepter);
				var jsObj = {accepter: _accepter, contenu_id:_contenu_id}
			      , ajaxObj = {};
			      ajaxObj = {
					  type: "POST",
					  url: "http://"+$scope.adr+":8080/StatusService/api/contenu/moderer",
					  data: JSON.stringify(jsObj),
					  contentType:"application/json",
					  error: function(jqXHR, textStatus, errorThrown) {
					      console.log("Error " + jqXHR.getAllResponseHeaders() + " " + errorThrown);
					      alert('error');
					  },
					  success: function(data) {
					      console.log(data);
					      alert('success');
					  },
					  complete: function(XMLHttpRequest) {
					      console.log( XMLHttpRequest.getAllResponseHeaders() );
					  },
					  dataType: "json" //request JSON
				      };
			      $.ajax(ajaxObj);
			}
            $scope.getMur=function(){
            	$scope.contenu = [
            		new Contenu(12, "15-14-2015", "15-14-2015", "contenu_text1", "84", "afifa", $scope.file, "aucun", true),
            		new Contenu(12, "15-14-2015", "15-14-2015", "contenu_text2", "84", "afifa", new File([""], "filename"), "aucun", true)
            	];
                //retourne une liste d'étudiant 
            }
			$scope.getContenuAModerer=function(){
			//retourne un tableau des contenus a moderer
			}
			$scope.getListUtilisateurLike=function(id_contenu){
			//retoune un tableau des utilisateurs qui aime le contenu
			}
			$scope.getListCommentaire=function(id_contenu){
			//retourne la list de commentaires du contenu
			}
}]);


function Contenu(contenu_cle,date_publication,date_modification,contenu_text,
                  cle_utilisateur, nom_utilisateur,contenu_fichier,contenu_type,contenu_accepter){
            this.statutId=contenu_cle;
            this.datePub=date_publication;
            this.date_modification=date_modification;
            this.contentText=contenu_text;
            this.userId=cle_utilisateur;
			this.userName=nom_utilisateur;
            this.contentMedia=contenu_fichier;
            this.contenuType=contenu_type;
            this.contenuAccepter=contenu_accepter;
}
function Utilisateur(utilisateur_cle,nom_utilisateur){
	this.utilisateur_cle=utilisateur_cle;
	this.nom_utilisateur=nom_utilisateur;
}
function Commentaire(contenu_commentaire,date_commentaire,utilisateur){
	this.contenu_commentaire=contenu_commentaire;
	this.date_commentaire=date_commentaire;
	this.utilisateur=utilisateur;
}
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



