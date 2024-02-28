package com.note.web.service;

import com.note.web.entity.Note;
import com.note.web.exception.NoteNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;



@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NoteFakeRepositoryTest {

    @Autowired
    private NoteFakeRepository noteFakeRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
    @Order(1)
    @Test
    void testThatMethodAddCreateNoteCorrectly(){
        Assertions.assertThat(noteFakeRepository.add(Note.builder()
                        .title("NoteTwo")
                        .content("NoteTwo")
                        .build()))
                .isNotNull();
    }
    @Order(2)
    @Test
    void testThatMethodListAllReturnAllNotesCorrectly(){
        noteFakeRepository.add(Note.builder()
                .title("NoteOne")
                .content("NoteOne")
                .build());
        Assertions.assertThat(noteFakeRepository.listAll())
                .isNotNull()
                .hasSize(1);
    }
    @Order(3)
    @Test
    void testThatMethodGetByIdWorkCorrectly() throws NoteNotFoundException {
        Note note = noteFakeRepository.add(Note.builder()
                .title("NoteOne")
                .content("NoteOne")
                .build());
        Assertions.assertThat(noteFakeRepository.getById(note.getId()))
                .isNotNull()
                .isEqualTo(note);
    }
    @Order(4)
    @Test
    void testThatMethodGetByIdThrowException() {
        Assertions.assertThatThrownBy(() -> noteFakeRepository.getById(null))
                .isInstanceOf(NoteNotFoundException.class);
    }
    @Order(5)
    @Test
    void testThatMethodDeleteByIdDeleteNoteCorrectly() throws NoteNotFoundException {
        Note forDelete = noteFakeRepository.add(Note.builder()
                .title("NoteOne")
                .content("NoteOne")
                .build());
        noteFakeRepository.deleteById(forDelete.getId());
        Assertions.assertThat(noteFakeRepository.listAll())
                .isNotNull()
                .isEmpty();
    }
    @Order(6)
    @Test
    void testThatMethodDeleteByIdThrowException() {
        Assertions.assertThatThrownBy(() -> noteFakeRepository.deleteById(null))
                .isInstanceOf(NoteNotFoundException.class);
    }
    @Order(7)
    @Test
    void testThatMethodUpdateWorkCorrectly() throws NoteNotFoundException {
        Note note = noteFakeRepository.add(Note.builder()
                .title("NoteOne")
                .content("NoteOne")
                .build());
        note.setTitle("New Title");
        note.setContent("New Content");
        noteFakeRepository.update(note);
        Assertions.assertThat(noteFakeRepository.getById(note.getId()))
                .isNotNull()
                .isEqualTo(note);
    }
    @Order(8)
    @Test
    void testThatMethodUpdateThrowException() {
        Assertions.assertThatThrownBy(() -> noteFakeRepository.update(Note.builder().build()))
                .isInstanceOf(NoteNotFoundException.class);
    }

}