package com.feefo.noteapi.domain.model;

import jakarta.validation.constraints.NotBlank;

public record NoteRequestModel(@NotBlank String note) {
}
