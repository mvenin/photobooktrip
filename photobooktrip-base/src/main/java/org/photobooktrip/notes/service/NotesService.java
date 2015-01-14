package org.photobooktrip.notes.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.photobooktrip.notes.domain.Note;
import org.photobooktrip.notes.domain.Notebook;
import org.photobooktrip.web.notes.NoteResource;
import org.photobooktrip.web.notes.NotebookResource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NotesService {
	
	@PersistenceContext
	private EntityManager em;

	public long createNotebook(NotebookResource notebook) {
		Notebook e = new Notebook(notebook.getName());
		Notebook kid = new Notebook("Kid");
		e.addNotebook(kid );
		kid.addNotebook(new Notebook("Minor Kid") );
		
		em.persist(e);
		return e.getId();
	}

	public NotebookResource findById(long noteId) {
		Notebook e = em.find( Notebook.class, noteId);
		NotebookResource nbk = new NotebookResource(e.getName(), e.getId());
		
		if( e.getParent() != null){
			nbk.add(new Link("/notebooks/"+e.getParent().getId(), "parent"));
		}
		
		for(Notebook c : e.getChildrenNotebooks() ){
			nbk.addNotebook( new NotebookResource(c.getName(), c.getId()) );
		}
		
		for(Note n : e.getNotes()){
			nbk.addNote(new NoteResource(n.getName(), n.getId()));
		}
		return nbk;
	}

}
