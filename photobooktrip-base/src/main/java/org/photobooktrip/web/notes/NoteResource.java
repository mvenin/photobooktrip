package org.photobooktrip.web.notes;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NoteResource extends ResourceSupport {
	private String name;
	private String content;
	
	@JsonCreator
	public NoteResource(@JsonProperty("name") String name, Long id) {
		this.name = name;
		if( id != null){
			this.add(new Link("/notes/"+id, "self"));
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}