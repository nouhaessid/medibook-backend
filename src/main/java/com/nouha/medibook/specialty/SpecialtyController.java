package com.nouha.medibook.specialty;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/specialties")
public class SpecialtyController {

    private final SpecialtyService specialtyService;

    @GetMapping
    public ResponseEntity<List<SpecialtyResponse>> getSpecialties() {
        return ResponseEntity.ok(specialtyService.getSpecialties());
    }

    @PostMapping
    public ResponseEntity<SpecialtyResponse> createSpecialty(
            @Valid @RequestBody SpecialtyRequest request
    ) {
        SpecialtyResponse createdSpecialty = specialtyService.createSpecialty(request.name());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSpecialty);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSpecialty(
            @PathVariable Integer id,
            @Valid @RequestBody SpecialtyRequest request
    ) {
        specialtyService.updateSpecialty(id, request.name());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecialty(
            @PathVariable Integer id
    ){
        specialtyService.deleteSpecialty(id);
        return ResponseEntity.noContent().build();
    }

}
