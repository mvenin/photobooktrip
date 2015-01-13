package org.photobooktrip.notes.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.photobooktrip.notes.domain.Note;
import org.photobooktrip.notes.domain.Notebook;
import org.photobooktrip.web.notes.NoteVo;
import org.photobooktrip.web.notes.NotebookResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NotesService {
	
	@PersistenceContext
	private EntityManager em;

	public Long createNotebook(NotebookResource notebook) {
		Notebook entity = new Notebook(notebook.getName());
		em.persist(entity);
		return entity.getId();
	}

	public NotebookResource findById(Long noteId) {
		Notebook e = em.find( Notebook.class, noteId);
		NotebookResource vo = new NotebookResource(e.getName(), e.getId());
		for(Note n : e.getNotes()){
			vo.addNoteLink(n.getId());
		}
		return vo;
	}

}
