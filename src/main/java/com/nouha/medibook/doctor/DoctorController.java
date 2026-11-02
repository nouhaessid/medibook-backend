package com.nouha.medibook.doctor;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping
    public ResponseEntity<Page<DoctorResponse>> getDoctors(
            @RequestParam(required = false) Integer specialtyId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return ResponseEntity.ok(doctorService.getDoctors(specialtyId, name, address, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponse> getDoctorById(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(doctorService.getDoctor(id));
    }
}
