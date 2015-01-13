package org.photobooktrip.web.notes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class NotebookResource extends ResourceSupport {
	private String name;
	private Link parent;
	private List<Link> children = new ArrayList<Link>();
	private List<Link> notes = new ArrayList<Link>();
	
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
	
	public void addNoteLink(long noteId){
		this.notes.add(new Link("/notes/"+noteId, "self"));
	}
	
	public void addNotebook(Link notebookId){
		this.children.add(new Link("/notebooks/"+notebookId, "child"));
	}
	
	public String getName() {
		return name;
	}
	public Link getParent() {
		return parent;
	}
	
	public List<Link> getNotes() {
		return notes;
	}
	
	public List<Link> getChildren() {
		return children;
	}
}



