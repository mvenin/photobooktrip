package org.photobooktrip.notes.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "notebooks")
public class Notebook {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "notebook_id")
	private Long id;

	@Column(name = "name")
	private String name;
	
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "parent_id")
	private Notebook parent;
	
	@OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<Notebook> childrenNotebooks = new ArrayList<Notebook>();

	@OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true, mappedBy = "notebook")
	private List<Note> notes = new ArrayList<Note>();

	public Notebook() {
	}

	public Notebook(String name) {
		this.name = name;
	}
	
	public Notebook getParent() {
		return parent;
	}

	public void addNotebook(Notebook notebook) {
		if (notebook != null) {
			if( notebook.parent != null ){ 
				notebook.parent.childrenNotebooks.remove(notebook);
			}
			notebook.parent = this;
			this.childrenNotebooks.add(notebook);
		}
	}
	
	public void addNote(Note note) {
		if (note != null) {
			if( note.getNotebook() != null){
				note.getNotebook().getNotes().remove(note);
			}
			note.setNotebook(this);
			this.notes.add(note);
		}
	}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public List<Notebook> getChildrenNotebooks() {
		return childrenNotebooks;
	}
	
	public List<Note> getNotes() {
		return this.notes;
	}

	public String toString() {
		return "Notebook [id=" + this.id + ", name=" + this.name + ", notes="
				+ this.notes + "]";
	}
}
