package com.nouha.medibook.specialty;

import jakarta.validation.constraints.NotBlank;

public record SpecialtyRequest(
        @NotBlank String name
) {
}
