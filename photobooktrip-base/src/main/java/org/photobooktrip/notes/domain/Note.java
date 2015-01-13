package org.photobooktrip.notes.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notes")
public class Note {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "note_id")
	private Long id;

	private String name;
	private String content;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "notebook_id")
	private Notebook notebook;

	@ManyToMany(cascade = { CascadeType.ALL })
	private Set<Tag> tags = new HashSet<Tag>();

	public Note() {
	}

	public Note(String name) {
		this.name = name;
		for (String s : this.name.split("\\s+")) {
			this.tags.add(new Tag(s));
		}
	}

	public Note(String name, Tag tag) {
		this.name = name;
		this.tags.add(tag);
	}

	public void addTag(Tag tag){
		if( tag != null){
			this.tags.add(tag);
		}
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Notebook getNotebook() {
		return this.notebook;
	}

	public void setNotebook(Notebook notebook) {
		this.notebook = notebook;
	}

	public Set<Tag> getTags() {
		return this.tags;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
