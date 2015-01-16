// Simple JavaScript Templating
// John Resig - http://ejohn.org/ - MIT Licensed
(function(){
  var cache = {};
 
  this.tmpl = function tmpl(str, data){
    // Figure out if we're getting a template, or if we need to
    // load the template - and be sure to cache the result.
    var fn = !/\W/.test(str) ?
      cache[str] = cache[str] ||
        tmpl(document.getElementById(str).innerHTML) :
     
      // Generate a reusable function that will serve as a template
      // generator (and which will be cached).
      new Function("obj",
        "var p=[],print=function(){p.push.apply(p,arguments);};" +
       
        // Introduce the data as local variables using with(){}
        "with(obj){p.push('" +
       
        // Convert the template into pure JavaScript
        str
          .replace(/[\r\t\n]/g, " ")
          .split("<%").join("\t")
          .replace(/((^|%>)[^\t]*)'/g, "$1\r")
          .replace(/\t=(.*?)%>/g, "',$1,'")
          .split("\t").join("');")
          .split("%>").join("p.push('")
          .split("\r").join("\\'")
      + "');}return p.join('');");
   
    // Provide some basic currying to the user
    return data ? fn( data ) : fn;
  };
})();
      
var TemplateEngine = tmpl;

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
	
$('#newNoteBtn').on("click", function(e){
	 e.preventDefault();
	 
	 $.ajax({
		 url: e.currentTarget.href,
		 success: function(data) {
			$("h1.editable").html(data.name)
			$("div.editable").html(data.name)
		 },
		 error: function(a,b) {
			 console.log(b);
		 },
		 complete: function(a,b){
			 console.log(b);
		 }
	 });
	 
});

	
$("#notesContainer").on("click","a", function(e){
	e.preventDefault();
	
	$.ajax({
		 url: e.currentTarget.href,
		 success: function(data) {
			$("h1.editable").html(data.name)
			$("div.editable").html(data.name)
		 },
		 error: function(a,b) {
			 console.log(b);
		 },
		 complete: function(a,b){
			 console.log(b);
		 }
	 });
});

$("#notebooks").on("click","a", function(e){
	e.preventDefault();
	
	$("#notebooks a").each(function(i, e){
		$(e).removeClass('btn btn-primary btn-sm active');
	});
	
	$(this).addClass('btn btn-primary btn-sm active');
	$("#searchTopInput").val($(this).text());
	
	function onSuccess(data){
		$("#notesContainer").empty();
		for (var i in data.notes) {
	 		var note = data.notes[i];
	 		
	 		var noteHref = '#';
	 		for (var j in note.links) {
	 		  var link = note.links[j];
	 		  if( link.rel === 'self'){
	 			 noteHref = link.href;
	 		  }	 		  
	 		}
	 		
		 	var noteDiv = TemplateEngine('noteTileTpl', {
		 		href: noteHref,
		 		title: note.name,
		 	    content: note.content
		 	});
		 	$("#notesContainer").append( noteDiv );
	 	
	 	}
	}
	
	 $.ajax({
		 url: e.currentTarget.href,
		 success: function(data) {
		 	onSuccess(data);
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

