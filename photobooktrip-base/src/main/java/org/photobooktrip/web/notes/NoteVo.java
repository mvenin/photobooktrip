package org.photobooktrip.web.notes;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NoteVo extends ResourceSupport {
	private Long uid;
	private String name;

	@JsonCreator
	public NoteVo(@JsonProperty("uid") Long uid, @JsonProperty("name") String name) {
		this.uid = uid;
		this.name = name;
	}
	
	public NoteVo(String name) {
		this(null, name);
	}

	public String getName() {
		return name;
	}
	
	public Long getUid() {
		return uid;
	}

}

