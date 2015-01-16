package org.photobooktrip.web.notes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class NotebookResource extends ResourceSupport {
	
	private String name;
	
	private List<NotebookResource> children = new ArrayList<NotebookResource>();
	private List<NoteResource> notes = new ArrayList<NoteResource>();
	
	public NotebookResource(){}
	
	@JsonCreator
	public NotebookResource(@JsonProperty("name") String name) {
		this.name = name;
	}
	
	
	public void addNotebook(NotebookResource notebook){
		this.children.add(notebook);
	}
	
	public void addNote(NoteResource note){
		this.notes.add(note);
	}
	
	public String getName() {
		return name;
	}
	
	public List<NoteResource> getNotes() {
		return notes;
	}
	
	public List<NotebookResource> getChildren() {
		return children;
	}
}



