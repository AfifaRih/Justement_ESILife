var  MurControllers= angular.module('MurControllers', []);

	MurControllers.controller('MurCtrl', ['$scope',
		function($scope){
			 $scope.contenu = "";	
			 $scope.file;
			 $scope.adr = "192.168.173.206";
			 $scope.currentUser;
			 $scope.contenuMod;
			
			$scope.publier = function(text, content){
			      alert('publier statut' + text + " "+content);
			      var realType = null;
			      if(content!=null)
			      	realType = content.type;


			      var XHR = new XMLHttpRequest();
			      var FD  = new FormData();
			      var time = new Date().getTime();
			      var jsObj = {id_user: $scope.currentUser.utilisateur_cle,status:text,date_publication:time,date_modification:time,type:realType};
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
			
			$scope.commenter = function(_id_status, _commentaire){
				alert('commenter id '+_id_status+' text '+_commentaire);
				var time = new Date().getTime();
				var jsObj = {id_contenu:_id_status, id_user:$scope.currentUser.utilisateur_cle, commentaire:_commentaire, date_publication: time}
			      , ajaxObj = {};
			      ajaxObj = {
				  type: "POST",
				  url: "http://"+$scope.adr+":8080/StatusService/api/contenu/commenter",
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
			
			$scope.aimer = function(_id_status, _bool){
				alert('aimer id '+_id_status+' bool '+_bool);
				var jsObj = {id_contenu:_id_status, id_user:$scope.currentUser.utilisateur_cle, bool:_bool}
			      , ajaxObj = {};
			      ajaxObj = {
				  type: "POST",
				  url: "http://"+$scope.adr+":8080/StatusService/api/contenu/aimer",
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
            	//retourne une liste de contenu
                var tabJsonContenu=JSON.parse(httpGet('http://'+$scope.adr+':8080/StatusService/api/contenu/murSempel'));
                var tabContenu = [],fichierBinaire,contentType='image/png',blob;
                for(i=0;i<tabJsonContenu.length;i++){
                    if(tabJsonContenu[i].contenu_binaire!=null){
                        //fichierBinaire=b64toBlob(tabJsonContenu[i].contenu_binaire.replace(/\s/g, ''),contentType,512);
                        //alert(fichierBinaire);
                        fichierBinaire=tabJsonContenu[i].contenu_binaire.replace(/\s/g, '');
                        //fichierBinaire=blobToFile(blob,"image-test.png");
                    }else{
                        fichierBinaire=null;
                    }
                    var user = $scope.getUser(tabJsonContenu[i].contenu_cle_utilisateur);
                    tabContenu.push( new Contenu(tabJsonContenu[i].contenu_cle,
                    							new Date(tabJsonContenu[i].contenu_date_publication),
                                                new Date(tabJsonContenu[i].contenu_date_modification),tabJsonContenu[i].contenu_text,
                                                user.utilisateur_cle, user.nom_utilisateur,fichierBinaire,
                                                tabJsonContenu[i].contenu_type,tabJsonContenu[i].contenu_accepter));
                }
                $scope.contenu = tabContenu;
                return tabContenu;
            	/*$scope.contenu = [
            		new Contenu(12, "15-14-2015", "15-14-2015", "contenu_text1", 84, "afifa", $scope.file, "aucun", true),
            		new Contenu(12, "15-14-2015", "15-14-2015", "contenu_text2", 20, "afifa", new File([""], "filename"), "aucun", true)
            	];*/
                //retourne une liste d'étudiant 
            }
			$scope.getContenuAModerer=function(){
			//retourne un tableau des contenus a moderer
				var tabJsonContenu=JSON.parse(httpGet('http://'+$scope.adr+':8080/StatusService/api/moderateur/contenu'));
                var tabContenu = [],fichierBinaire,contentType='image/png',blob;
                for(i=0;i<tabJsonContenu.length;i++){
                    if(tabJsonContenu[i].contenu_binaire!=null){
                        //fichierBinaire=b64toBlob(tabJsonContenu[i].contenu_binaire.replace(/\s/g, ''),contentType,512);
                        //alert(fichierBinaire);
                        fichierBinaire=tabJsonContenu[i].contenu_binaire.replace(/\s/g, '');
                        //fichierBinaire=blobToFile(blob,"image-test.png");
                    }else{
                        fichierBinaire=null;
                    }
                    var user = $scope.getUser(tabJsonContenu[i].contenu_cle_utilisateur);
                    tabContenu.push( new Contenu(tabJsonContenu[i].contenu_cle,
                    							new Date(tabJsonContenu[i].contenu_date_publication),
                                                new Date(tabJsonContenu[i].contenu_date_modification),tabJsonContenu[i].contenu_text,
                                                user.utilisateur_cle, user.nom_utilisateur,fichierBinaire,
                                                tabJsonContenu[i].contenu_type,tabJsonContenu[i].contenu_accepter));
                }
                $scope.contenuMod = tabContenu;
                return tabContenu;
			}
			$scope.getListUtilisateurLike=function(id_contenu){
			alert('id_contenu '+id_contenu);
			/*	return [
					new Utilisateur(45, "Utilisateur 1"),
					new Utilisateur(20, "Utilisateur 2")
				];
			//retoune un tableau des utilisateurs qui aime le contenu*/
			//retoune un tableau des utilisateurs qui aime le contenu
                var tabJsonUsers=JSON.parse(httpGet('http://'+$scope.adr+':8080/StatusService/api/contenu/utilisateur_like?id_contenu='+id_contenu));
               var tabUsers = [];
                for(i=0;i<tabJsonUsers.length;i++){
                    tabUsers.push(new Utilisateur(tabJsonUsers[i].cle_user,tabJsonUsers[i].compte_user));
                }
                return tabUsers;
			
			}
			$scope.getListCommentaire=function(id_contenu){
				/*return [
					new Commentaire("commentaire 1", "17-02-2014", new Utilisateur(454, "Utilisateur"))
				];*/
			//retourne la list de commentaires du contenu
			//retourne la list de commentaires du contenu
                var tabJsonCommentaire=JSON.parse(httpGet('http://'+$scope.adr+':8080/StatusService/api/contenu/commentaire?id_contenu='+id_contenu));
                var tabCommentaire = [];
                for(i=0;i<tabJsonCommentaire.length;i++){
                	
                    tabCommentaire.push(new Commentaire(tabJsonCommentaire[i].commentaireText,
                                                        new Date(tabJsonCommentaire[i].date_publication),
                                                        $scope.getUser(tabJsonCommentaire[i].cle_utilisateur)));
                }
                return tabCommentaire;
			}
			$scope.getUser=function(id_user){
				var userJson=JSON.parse(httpGet('http://'+$scope.adr+':8080/StatusService/api/utilisateur/get_utilisateur?id_user='+1));
				    var user=new Utilisateur(userJson.cle_user,userJson.compte_user);
				    return user;
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
function UtilisateurCourant(utilisateur_cle,nom_utilisateur, url_image, mail_utilisateur){
	this.utilisateur_cle=utilisateur_cle;
	this.nom_utilisateur=nom_utilisateur;
	this.url_image = url_image;
	this.mail_utilisateur = mail_utilisateur;
}
function httpGet(theUrl)
{
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", theUrl, false );
    xmlHttp.send( null );
    return xmlHttp.responseText;
}
function b64toBlob(b64Data, contentType, sliceSize) {
    contentType = contentType || '';
    sliceSize = sliceSize || 512;

    var byteCharacters = atob(b64Data);
    var byteArrays = [];

    for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {
        var slice = byteCharacters.slice(offset, offset + sliceSize);

        var byteNumbers = new Array(slice.length);
        for (var i = 0; i < slice.length; i++) {
            byteNumbers[i] = slice.charCodeAt(i);
        }

        var byteArray = new Uint8Array(byteNumbers);

        byteArrays.push(byteArray);
    }

    var blob = new Blob(byteArrays, {type: contentType});
    return blob;
}
function blobToFile(theBlob, fileName){
    theBlob.lastModifiedDate = new Date();
    theBlob.name = fileName;
    return theBlob;
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



