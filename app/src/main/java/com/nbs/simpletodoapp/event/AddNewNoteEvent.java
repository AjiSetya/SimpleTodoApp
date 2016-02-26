package com.nbs.simpletodoapp.event;

import com.nbs.simpletodoapp.db.Note;

/**
 * Created by Sidiq on 26/02/2016.
 */
public class AddNewNoteEvent {
    Note note;

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }
}
