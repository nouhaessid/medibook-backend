package com.nouha.medibook.doctor;

public record DoctorResponse(
        Integer id,
        String name,
        String specialty,
        String biography,
        Integer yearsOfExperience,
        String clinicAddress
) {
}
