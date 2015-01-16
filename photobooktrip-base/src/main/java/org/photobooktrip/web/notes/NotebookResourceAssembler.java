package org.photobooktrip.web.notes;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.photobooktrip.notes.domain.Note;
import org.photobooktrip.notes.domain.Notebook;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.http.HttpEntity;

public class NotebookResourceAssembler extends
		ResourceAssemblerSupport<Notebook, NotebookResource> {

	public NotebookResourceAssembler() {
		super(NotesControler.class, NotebookResource.class);
	}

	public NotebookResource toResource(Notebook entity) {
		NotebookResource nbk = new NotebookResource(entity.getName());
		
		HttpEntity<NotebookResource> nbHttpEntity = methodOn(NotesControler.class).viewNotebook(entity.getId());
		nbk.add(linkTo(nbHttpEntity).withSelfRel());
		
		if( entity.getParent() != null){
			nbk.add(linkTo(nbHttpEntity).withRel("parent"));
		}
		
		for(Notebook c : entity.getChildrenNotebooks() ){
			nbk.addNotebook(toResource(c) );
		}
		
		for(Note n : entity.getNotes()){
			NoteResource note = new NoteResource(n.getName() );
			nbk.addNote(note);
			note.setContent(n.getContent().substring(0, 30));
			note.add( linkTo(methodOn(NotesControler.class).viewNote(n.getId())).withSelfRel() );
		}
		
		return nbk;
	}
}