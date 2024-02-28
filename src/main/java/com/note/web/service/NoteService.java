package com.note.web.service;

import com.note.web.entity.Note;
import com.note.web.exception.NoteNotFoundException;

import java.util.List;
import java.util.UUID;

public interface NoteService {
    Note add(Note note);

    List<Note> listAll();

    Note getById(UUID id) throws NoteNotFoundException;

    void deleteById(UUID id) throws NoteNotFoundException;

    void update(Note note) throws NoteNotFoundException;
}
