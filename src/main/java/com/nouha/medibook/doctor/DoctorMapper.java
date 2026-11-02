package com.nouha.medibook.doctor;

import org.springframework.stereotype.Service;

@Service
public class DoctorMapper {
    public DoctorResponse toDoctorResponse(DoctorProfile doctorProfile) {
        return new DoctorResponse(
                doctorProfile.getId(),
                doctorProfile.getUser().getName(),
                doctorProfile.getSpecialty().getName(),
                doctorProfile.getBiography(),
                doctorProfile.getYearsOfExperience(),
                doctorProfile.getClinicAddress()
        );
    }
}
