$(document).ready(function(){
    var elements = $('td[title="status"]');
    for( i=0; i<elements.length; i++ ){
    	var statusIs = elements[i].innerHTML;
    	var onlyNumberId = elements[i].id.slice("6");
    	var realId = "#"+onlyNumberId;
    	if(statusIs == "lost")
    		{
    			$(realId).attr("disabled","disabled");
    		}
    	else{
    		$(realId).removeAttr("disabled");
    	}
    }
});
$(":button").click(function() {
	var isbn = this.id;
	var uri = "/library/v1/books/"+isbn+"?status=lost";
	var btnDisable = "#"+isbn;
	var statusS = "#status"+isbn;
	alert('About to report lost on ISBN ' + isbn);
	
	$.ajax({
		  url: uri,
		  type: 'PUT',
		  success: function(data) {
			  $(btnDisable).attr("disabled","disabled");
			    $(statusS).text("lost");
		  }
		});
});