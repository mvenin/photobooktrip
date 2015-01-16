package org.photobooktrip.web.notes;

import java.util.Random;

import org.photobooktrip.notes.domain.Note;
import org.photobooktrip.notes.domain.Notebook;
import org.photobooktrip.notes.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotesServiceBF {

	@Autowired
	private NotesService notesService;
	
	private NotebookResourceAssembler notebookAssembler = new NotebookResourceAssembler();
	
	private void addTestData(Notebook entity){
		Notebook kid = new Notebook("Kid");
		entity.addNotebook(kid );
		kid.addNotebook(new Notebook("Minor Kid") );
		
		for (int i = 0; i < 5; i++) {
			Note note = new Note("Note Title " +i);
			entity.addNote(note);
			note.setContent(generateNoteContent());
		}	
	}
	
	private String generateNoteContent() {
		Random seq = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 100; i++) {
			if( i % 5 == 0){
				sb.append(" ");				
			} else {
				sb.append( (char)('a' + seq.nextInt(26)) );
			}
		}
		return sb.toString();
	}
	
	public long createNotebook(NotebookResource notebook) {
		Notebook entity = new Notebook(notebook.getName());
		addTestData(entity);//FIXME
		
		notesService.createNotebook(entity);
		return entity.getId();
	}

	public NotebookResource findById(long notebookId) {
		Notebook entity = notesService.findNotebookById(notebookId);
		NotebookResource nbr = notebookAssembler.toResource(entity);
		return nbr;
	}

	public NoteResource findNoteById(Long noteId) {
		Note entity = notesService.findNoteById(noteId);
		NoteResource note = new NoteResource(entity.getName() );
		note.setContent(entity.getContent());
		return note;
	}

	
}
