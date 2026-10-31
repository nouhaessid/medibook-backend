package com.nouha.medibook.specialty;

import org.springframework.stereotype.Service;

@Service
public class SpecialtyMapper {

    public SpecialtyResponse toSpecialtyResponse(Specialty specialty) {
        return new SpecialtyResponse(specialty.getId(),  specialty.getName());
    }

    public Specialty toSpecialty(String specialtyName) {
        return Specialty.builder()
                .name(specialtyName)
                .build();
    }
}
