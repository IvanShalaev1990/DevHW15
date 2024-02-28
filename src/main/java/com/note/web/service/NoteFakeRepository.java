package com.note.web.service;

import com.note.web.entity.Note;
import com.note.web.exception.NoteNotFoundException;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Component
@Data
public class NoteFakeRepository {

    private static final Logger log = Logger.getLogger(NoteFakeRepository.class.getName());
    private List<Note> notes = new ArrayList<>();

    public Note add(Note note) {
        note.setId(UUID.randomUUID());
        this.notes.add(note);
        log.info("New note was added...");
        log.info(note.getId().toString());
        return note;
    }

    public List<Note> listAll() {
        log.info("A copy of all notes was send");
        return List.copyOf(this.notes);
    }

    public Note getById(UUID id) throws NoteNotFoundException {
        isIDNotNull(id);
        return this.notes.stream()
                .filter(it -> it.getId().equals(id))
                .findFirst().orElseThrow(() -> new NoteNotFoundException(id));
    }

    public void deleteById(UUID id) throws NoteNotFoundException {
        this.notes.remove(getById(id));
    }

    public void update(Note note) throws NoteNotFoundException {
        Note noteForUpdate = getById(note.getId());
        noteForUpdate.setTitle(note.getTitle());
        noteForUpdate.setContent(note.getContent());
    }

    private void isIDNotNull(UUID id) throws NoteNotFoundException {
        if (id == null) {
            throw new NoteNotFoundException();
        }
    }

}
