package org.photobooktrip.web.notes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class NotebookResource extends ResourceSupport {
	private String name;
//	private Link parent;
	private List<NotebookResource> children = new ArrayList<NotebookResource>();
	private List<NoteResource> notes = new ArrayList<NoteResource>();
	
	@JsonCreator
	public NotebookResource(@JsonProperty("name") String name) {
		this(name, null);
	}
	
	@JsonCreator
	public NotebookResource(@JsonProperty("name") String name, Long id) {
		this.name = name;
		if( id != null){
			this.add(new Link("/notebooks/"+id, "self"));
		}
	}
	
//	public void setParent(Link parent) {
//		this.parent = parent;
//	}
	
	public void addNotebook(NotebookResource notebook){
		this.children.add(notebook);
	}
	
	public void addNote(NoteResource note){
//		this.notes.add(new Link("/notes/"+noteId, "self"));
		this.notes.add(note);
	}
	
	public String getName() {
		return name;
	}
//	public Link getParent() {
//		return parent;
//	}
	
	public List<NoteResource> getNotes() {
		return notes;
	}
	
	public List<NotebookResource> getChildren() {
		return children;
	}
}



