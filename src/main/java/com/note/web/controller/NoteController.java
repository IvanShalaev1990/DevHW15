package com.note.web.controller;

import com.note.web.entity.Note;
import com.note.web.exception.NoteNotFoundException;
import com.note.web.service.NoteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Validated
@Controller
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;
    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }
    @GetMapping("/list")
    public ModelAndView noteList() {
        ModelAndView result = new ModelAndView("note/list");
        result.addObject("notes", noteService.listAll());
        return result;
    }
    @PostMapping("/delete")
    public String deleteNoteById(
            @Valid
            @NotNull @RequestParam("id") UUID id) throws NoteNotFoundException {
        noteService.deleteById(id);
        return "redirect:/note/list";
    }
    @GetMapping("/edit")
    public ModelAndView editNoteById(
            @Valid
            @NotNull @RequestParam("id") UUID id) throws NoteNotFoundException {
        ModelAndView result = new ModelAndView("note/edit");
        result.addObject("note", noteService.getById(id));
        return result;
    }
    @PostMapping("/edit")
    public ModelAndView updateNote(
            @Valid
            @NotNull @RequestParam(value="id") UUID id,
            @NotNull @RequestParam(value="title") String title,
            @RequestParam(value="content") String content) throws NoteNotFoundException {
        Note note = Note.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
        noteService.update(note);
        return noteList();
    }
}
