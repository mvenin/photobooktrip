package org.photobooktrip.notes.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="notebooks")
public class Notebook
{
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="notebook_id")
  private Long id;
  
  @Column(name="name")
  private String name;
  
  @OneToMany(cascade={javax.persistence.CascadeType.ALL}, orphanRemoval=true, mappedBy="notebook")
  private List<Note> notes = new ArrayList();
  
  public Notebook() {}
  
  public Notebook(String name)
  {
    this.name = name;
  }
  
  public void addNote(Note note)
  {
    if (note != null)
    {
      note.setNotebook(this);
      this.notes.add(note);
    }
  }
  
  public Long getId()
  {
    return this.id;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public List<Note> getNotes()
  {
    return this.notes;
  }
  
  public String toString()
  {
    return "Notebook [id=" + this.id + ", name=" + this.name + ", notes=" + this.notes + "]";
  }
}
