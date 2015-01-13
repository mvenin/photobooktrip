package org.photobooktrip.web.notes;

import org.photobooktrip.notes.domain.Notebook;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class NotebookResourceAssembler extends
		ResourceAssemblerSupport<Notebook, NotebookResource> {

	public NotebookResourceAssembler() {
		super(NotesControler.class, NotebookResource.class);
	}

	public NotebookResource toResource(Notebook person) {
		NotebookResource resource = null;
		createResourceWithId(person.getId(), person);											 
		// do further mapping
		return resource;
	}
}