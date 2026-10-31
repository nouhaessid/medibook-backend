package com.nouha.medibook.specialty;

import com.nouha.medibook.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SpecialtyService {

    private final SpecialtyRepository specialtyRepository;
    private final SpecialtyMapper specialtyMapper;

    public List<SpecialtyResponse> getSpecialties() {
        return specialtyRepository.findAll(Sort.by("name"))
                .stream()
                .map(specialtyMapper::toSpecialtyResponse)
                .toList();
    }

    public SpecialtyResponse createSpecialty(String specialtyName) {
        Specialty savedSpecialty = specialtyRepository.save(specialtyMapper.toSpecialty(specialtyName));
        return specialtyMapper.toSpecialtyResponse(savedSpecialty);
    }

    public void updateSpecialty(Integer id, String specialtyName) {
        var specialty = specialtyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("No specialty was found with ID: %d", id)));
        specialty.setName(specialtyName);
        specialtyRepository.save(specialty);
    }

    public void deleteSpecialty(Integer id) {
        var specialty = specialtyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("No specialty was found with ID: %d", id)));
        specialtyRepository.delete(specialty);
    }
}
