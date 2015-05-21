$(document).ready(function() {
    //console.log("ready");
    var $post_example = $('#post_status');
    /**
     * This is for the Submit button
     * It will trigger a ajax POST call to: api/v2/inventory
     * This will submit a item entry to our inventory database
     */
    $('#bSub').click(function(e) {
	//console.log("submit button has been clicked");
	e.preventDefault(); //cancel form submit
	var jsObj = {status:$('#status').val(),date_modification:$('#date_modification').val(),date_publication:$('#date_publication').val(),id_user:1,type:null}
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
    });
    $('#aimer').click(function(e){
    	e.preventDefault();
    	fAimer(1,1);
    });
    function fAimer(_id_user,_id_contenu){
    	var jsObj = {id_user:_id_user,id_contenu:_id_contenu}
    	, ajaxObj = {};
    	//console.log(jsObj);
    	ajaxObj = {
    	    type: "POST",
    	    url: "http://localhost:8080/StatusService/api/contenu/aimer",
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
    }
});



function upload(){
	var XHR = new XMLHttpRequest();
	var FD  = new FormData();
	var jsObj = {status:$('#status').val(),date_modification:$('#date_modification').val(),date_publication:$('#date_publication').val(),id_user:1,type:'image'};
	  // We define what will happen if the data are successfully sent
	  XHR.addEventListener('load', function(event) {
	    alert('Yeah! Data sent and response loaded.');
	  });

	  // We define what will happen in case of error
	  XHR.addEventListener('error', function(event) {
	    alert('Oups! Something goes wrong.');
	  });
	  // We setup our request
	  FD.append("file",document.getElementById("fichierUpload").files[0]);
	  FD.append("status",JSON.stringify(jsObj));
	  XHR.open('POST', 'http://localhost:8080/StatusService/api/status/update');	  
	  // We just send our FormData object, HTTP headers are set automatically
	  XHR.send(FD);
	  getMur();
}

function httpGet(theUrl)
{
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", theUrl, false );
    xmlHttp.send( null );
    return xmlHttp.responseText;
}
function getUser(id_user){
    var userJson=JSON.parse(httpGet('http://localhost:8080/StatusService/api/utilisateur/get_utilisateur?id_user='+id_user));
    var user=new Utilisateur(userJson.cle_user,userJson.compte_user);
    return user;
}
function getMur(){
	var tabContenu=JSON.parse(httpGet('http://localhost:8080/StatusService/api/contenu/murSempel'));
	var b64Data,contentType='image/png',blob;
	for(i=0;i<tabContenu.length;i++){
		if(tabContenu[i].contenu_binaire!=null){
			b64Data=tabContenu[i].contenu_binaire;
			blob=b64toBlob(b64Data.replace(/\s/g, ''),contentType,512);
		}
	}
	var blobUrl = URL.createObjectURL(blob);
	var fichier=blobToFile(blob,"image-test.png");

	window.location = blobUrl;
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


