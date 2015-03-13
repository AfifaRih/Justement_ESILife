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
	var jsObj = {status:$('#status').val(),date_modification:$('#date_modification').val(),date_publication:$('#date_publication').val(),id_user:1}
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