tinymce.init({
    selector: "h1.editable",
    inline: true,
    toolbar: "undo redo",
    menubar: false
});  

tinymce.init({
    selector: "div.editable",
    inline: true,
    plugins: [],
    toolbar: ""
});

$(document).ready( function () {
	
$("#notebooks").on("click","a", function(e){
	console.log(e);
	e.preventDefault();
	
	 $.ajax({
		 url: e.currentTarget.href,
		 success: function(data) {
		 	console.log(e);
		 },
		 error: function(a,b) {
			 console.log(b);
		 },
		 complete: function(a,b){
			 console.log(b);
		 }
	 });
	
});

	$('#newNotebookDlg #submitBtn').on('click', function(e){
		var notebook = $('#newNotebook').val();
		
	   $.ajax({
	      url: 'notebooks',
	      type: 'GET',
	      dataType: 'json',
	      data: {
	         name: notebook
	      },
	      success: function(data) {
	    	  var href = data._links.self.href;
	    	  var elm='<a href="'+ href+ '"><span><i class="glyphicon glyphicon-book"></i></span> '+ notebook + '</a><br/>';
	  		$('#notebooks').append(elm);
	      },
	      error: function(a,b) {
	         $('#errorMsgDiv').html('<p>An error has occurred</p>');
	      },
	      complete: function(a,b){
	         $('#newNotebookDlg').modal('hide');
	      }
	      
	   });
		
	});
	
	$('#newTagDlg #submitBtn').on('click', function(e){
		//var elm='<a href="#"><i class="glyphicon glyphicon-tag"></i>'+ $('#newTag').val() + '</a><br/>';
		//$('#tags').append(elm);
		//$('#newTagDlg').modal('hide');
		
		var tag = $('#newTag').val();
		
		 $.ajax({
	      url: 'tags',
	      type: 'POST',
	      dataType: 'json',
	      data: {
	         name: tag
	      },
	      success: function(data) {
	    	  	var elm='<a href="#"><span><i class="glyphicon glyphicon-book"></i></span> '+ tag + '</a><br/>';
	  			$('#tags').append(elm);
	      },
	      error: function(a,b) {
	         $("#errorMsgDiv").append( $("#errorMsgTpl").html() );
	      },
	      complete: function(a,b){
	         $('#newTagDlg').modal('hide');
	      }
	      
	   });
		
	});
	
});

