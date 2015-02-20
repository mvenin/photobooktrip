package org.photobooktrip.web.notes;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.photobooktrip.notes.domain.Notebook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notebooks")
public class NotesControler {

	@Autowired
	private NotesServiceBF notesService;
	
	@RequestMapping(method=RequestMethod.GET)
	public HttpEntity<NotebookList> allNotebooks() {
		List<Notebook> allNotebooks = notesService.findAllNotebooks();
		return new ResponseEntity<NotebookList>(new NotebookList(allNotebooks) , HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.POST)
	public HttpEntity<NotebookResource> createNotebook(@RequestParam(value = "name") String name) {
		NotebookResource notebook = new NotebookResource(name);
		long id= notesService.createNotebook(notebook);
		
		notebook.add(linkTo(methodOn(NotesControler.class).viewNotebook(id)).withSelfRel());
		return new ResponseEntity<NotebookResource>(notebook, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/{notebookId}", method=RequestMethod.GET)
	public HttpEntity<NotebookResource> viewNotebook(@PathVariable Long notebookId) {
		NotebookResource notebook = notesService.findById(notebookId);
		return new ResponseEntity<NotebookResource>(notebook, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/notes/{noteId}", method=RequestMethod.GET)
	public HttpEntity<NoteResource> viewNote(@PathVariable Long noteId) {
		NoteResource note = notesService.findNoteById(noteId);
		note.add(linkTo(methodOn(NotesControler.class).viewNote(noteId)).withSelfRel());
		return new ResponseEntity<NoteResource>(note, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, value= "/{notebookId}/notes")
	public HttpEntity<NoteVo> createNote(@RequestParam(value = "name") String name) {
		NoteVo note = new NoteVo(name);
		note.add(linkTo(methodOn(NotesControler.class).createNote(name))
				.withSelfRel());
		return new ResponseEntity<NoteVo>(note, HttpStatus.OK);
	}
	

}

class NotebookList {
	List<Notebook> allNotebooks;
	
	public NotebookList(List<Notebook> allNotebooks) {
		this.allNotebooks = allNotebooks;
	}

	public List<Notebook> getAllNotebooks() {
		return allNotebooks;
	}
	
	public void setAllNotebooks(List<Notebook> allNotebooks) {
		this.allNotebooks = allNotebooks;
	}
	
}

