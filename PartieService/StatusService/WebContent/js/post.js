$(document).ready(function() {
    //console.log("ready");
    var $post_example = $('#post_status');
    /**
     * This is for the Submit button
     * It will trigger a ajax POST call to: api/v2/inventory
     * This will submit a item entry to our inventory database
     */
    $('#bSub').click(function(e) {
    	alert("begin bsub");
	//console.log("submit button has been clicked");
	e.preventDefault(); //cancel form submit
	alert("begin bsub1");
	var jsObj = {status:$('#status').val(),date_modification:$('#date_modification').val(),date_publication:$('#date_publication').val(),id_user:1}
	, ajaxObj = {};
	//console.log(jsObj);
	alert("begin bsub2");
	ajaxObj = {
	    type: "POST",
	    url: "http://localhost:7000/StatusService/api/status/update",
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
	alert("begin bsub3");
	$.ajax(ajaxObj);
	alert("begin bsub4");
    });
    alert("begin bsub5");
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
    	    url: "http://localhost:7000/StatusService/api/contenu/aimer",
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

$('#com').click(function(e){
	e.preventDefault();
	var jsObj = {id_user:1,id_contenu:0,commentaire:$('#commentaire').val()}
	, ajaxObj = {};
	ajaxObj = {
	    type: "POST",
	    url: "http://localhost:7000/StatusService/api/contenu/commenter",
	    data: JSON.stringify(jsObj),
	    contentType:"application/json",
	    error: function(jqXHR, textStatus, errorThrown) {
		console.log("Error " + jqXHR.getAllResponseHeaders() + " " + errorThrown);
	    },
	    success: function(data) {
		console.log(data);
	    },
	    complete: function(XMLHttpRequest) {
	    },
	    dataType: "json" //request JSON
	};
	$.ajax(ajaxObj);
});

function upload(){
	var XHR = new XMLHttpRequest();
	var FD  = new FormData();
	var jsObj = {id_user:1,id_contenu:"chalalalallalallalalal"};
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
	  XHR.open('POST', 'http://localhost:7000/StatusService/api/test/upload');	  
	  // We just send our FormData object, HTTP headers are set automatically
	  XHR.send(FD);
}

