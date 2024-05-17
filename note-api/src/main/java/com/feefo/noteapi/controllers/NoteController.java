package com.feefo.noteapi.controllers;

import com.feefo.noteapi.domain.model.NoteRequestModel;
import com.feefo.noteapi.domain.model.NoteResponseModel;
import com.feefo.noteapi.services.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/notes")
@CrossOrigin
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<NoteResponseModel> store(@RequestBody @Valid NoteRequestModel requestModel) {
        NoteResponseModel createdNote = noteService.store(requestModel);

        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(createdNote.id()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<NoteResponseModel>> list() {
        return ResponseEntity.ok(noteService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponseModel> get(@PathVariable Long id) {
        return ResponseEntity.ok(noteService.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        noteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
