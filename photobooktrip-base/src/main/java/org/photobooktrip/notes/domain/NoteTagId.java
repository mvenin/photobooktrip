package org.photobooktrip.notes.domain;

import java.io.Serializable;

public class NoteTagId implements Serializable {
	private static final long serialVersionUID = 1L;

	private long noteId;
	private long tagId;

	public long getNoteId() {
		return noteId;
	}

	public void setNoteId(long noteId) {
		this.noteId = noteId;
	}

	public long getTagId() {
		return tagId;
	}

	public void setTagId(long tagId) {
		this.tagId = tagId;
	}

}