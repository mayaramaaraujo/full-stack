package com.feefo.noteapi.services;

import com.feefo.noteapi.domain.Note;
import com.feefo.noteapi.domain.model.NoteRequestModel;
import com.feefo.noteapi.domain.model.NoteResponseModel;
import com.feefo.noteapi.domain.repository.NoteRepository;
import com.feefo.noteapi.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public NoteResponseModel store(NoteRequestModel requestModel) {
        Note newNote = new Note(requestModel.note());
        Note createdNote = noteRepository.save(newNote);

        return new NoteResponseModel(createdNote.getId(), createdNote.getNote());
    }

    public List<NoteResponseModel> list() {
        List<Note> notes = noteRepository.findAll();
        List<NoteResponseModel> noteResponseModels = new ArrayList<>();

        for (Note note : notes) {
            noteResponseModels.add(new NoteResponseModel(note.getId(), note.getNote()));
        }

        return noteResponseModels;
    }

    public NoteResponseModel get(Long id) {
        Optional<Note> note = noteRepository.findById(id);

        if (note.isEmpty()) {
            throw new ResourceNotFoundException("Resource not found: " + id);
        }

        return new NoteResponseModel(note.get().getId(), note.get().getNote());
    }

    public void delete(Long id) {
        Optional<Note> note = noteRepository.findById(id);

        if (note.isEmpty()) {
            throw new ResourceNotFoundException("Resource not found: " + id);
        }

        noteRepository.delete(note.get());
    }
}
