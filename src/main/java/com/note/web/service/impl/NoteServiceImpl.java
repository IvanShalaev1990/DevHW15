package com.note.web.service.impl;

import com.note.web.entity.Note;
import com.note.web.exception.NoteNotFoundException;
import com.note.web.service.NoteFakeRepository;
import com.note.web.service.NoteService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NoteServiceImpl implements NoteService {

    private NoteFakeRepository noteRepository;
    @Autowired
    public NoteServiceImpl(NoteFakeRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Note add(Note note) {
        return noteRepository.add(note);
    }

    @Override
    public List<Note> listAll() {
        return noteRepository.listAll();
    }

    @Override
    public Note getById(UUID id) throws NoteNotFoundException {
        return noteRepository.getById(id);
    }

    @Override
    public void deleteById(UUID id) throws NoteNotFoundException {
        noteRepository.deleteById(id);
    }

    @Override
    public void update(Note note) throws NoteNotFoundException{
        noteRepository.update(note);
    }
    @PostConstruct
    public void init() {
        add(Note.builder()
                .title("First")
                .content("First content")
                .build());
        add(Note.builder()
                .title("Second")
                .content("Second content")
                .build());
        add(Note.builder()
                .title("Third")
                .content("Third content")
                .build());
        add(Note.builder()
                .title("Forth")
                .content("Forth content")
                .build());
        add(Note.builder()
                .title("Fifth")
                .content("Fifth content")
                .build());
    }
}
