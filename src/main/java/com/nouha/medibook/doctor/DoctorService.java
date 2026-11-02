package com.nouha.medibook.doctor;

import com.nouha.medibook.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@RequiredArgsConstructor
@Service
public class DoctorService {

    private final DoctorProfileRepository doctorProfileRepository;
    private final DoctorMapper doctorMapper;

    public Page<DoctorResponse> getDoctors(Integer specialtyId, String name,
                                           String address, Pageable pageable) {
        String safeName = (name == null) ? "" : name;
        String safeAddress = (address == null) ? "" : address;
        return doctorProfileRepository
                .search(specialtyId, safeName, safeAddress, pageable)
                .map(doctorMapper::toDoctorResponse);
    }

    public DoctorResponse getDoctor(Integer id) {
        return doctorProfileRepository.findById(id)
                .map(doctorMapper::toDoctorResponse)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("No Doctor was found with ID: %d", id)));
    }
}
