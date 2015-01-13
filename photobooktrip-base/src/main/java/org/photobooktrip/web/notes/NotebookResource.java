package org.photobooktrip.web.notes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NotebookResource extends ResourceSupport {
	private Long uid;
	private String name;
	
	@JsonProperty("notes")
	private List<NoteVo> notes = new ArrayList<NoteVo>();
	
	@JsonCreator
	public NotebookResource(@JsonProperty("uid") Long uid, @JsonProperty("name") String name) {
		this.uid = uid;
		this.name = name;
	}
	public NotebookResource(String name) {
		this(null,name);
	}
	
	public String getName() {
		return name;
	}
	
	public Long getUid() {
		return uid;
	} 
	
	public List<NoteVo> getNotes() {
		return notes;
	}
	
}



