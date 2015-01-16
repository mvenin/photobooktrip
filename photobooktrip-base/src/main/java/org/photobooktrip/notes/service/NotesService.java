package org.photobooktrip.notes.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.photobooktrip.notes.domain.Note;
import org.photobooktrip.notes.domain.Notebook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NotesService {
	
	@PersistenceContext
	private EntityManager em;

	public long createNotebook(Notebook e) {
		em.persist(e);
		return e.getId();
	}

	public Notebook findNotebookById(long noteId) {
		return  em.find( Notebook.class, noteId);
	}

	public Note findNoteById(Long noteId) {
		return  em.find( Note.class, noteId);
	}

}
