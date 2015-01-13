package org.photobooktrip.web.notes;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.photobooktrip.notes.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotesControler {

	@Autowired
	private NotesService notesService;

	@RequestMapping(value= "/notebooks", method=RequestMethod.POST)
	public HttpEntity<NotebookResource> createNotebook(@RequestParam(value = "name") String name) {
		NotebookResource notebook = new NotebookResource(name);
		Long id= notesService.createNotebook(notebook);
		
		Link link = linkTo(NotesControler.class).slash(id).withSelfRel();
		notebook.add(link);
		
		return new ResponseEntity<NotebookResource>(notebook, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/notebooks/{id}", method=RequestMethod.GET)
	public HttpEntity<NotebookResource> viewNotebook(@PathVariable Long noteId) {
		NotebookResource notebook = notesService.findById(noteId);
		return new ResponseEntity<NotebookResource>(notebook, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, value= "/notes")
	public HttpEntity<NoteVo> createNote(@RequestParam(value = "name") String name) {
		NoteVo note = new NoteVo(name);
		note.add(linkTo(methodOn(NotesControler.class).createNote(name))
				.withSelfRel());

		return new ResponseEntity<NoteVo>(note, HttpStatus.OK);
	}

}