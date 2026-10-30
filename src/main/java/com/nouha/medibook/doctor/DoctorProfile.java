package com.nouha.medibook.doctor;

import com.nouha.medibook.availability.AvailabilitySlot;
import com.nouha.medibook.specialty.Specialty;
import com.nouha.medibook.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class DoctorProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "specialty_id", nullable = false)
    private Specialty specialty;

    @Column(length = 2000)
    private String biography;

    @Column(nullable = false, unique = true)
    private String licenseNumber;

    private Integer yearsOfExperience;

    private String clinicAddress;

    @OneToMany(mappedBy = "doctor")
    @Builder.Default
    private List<AvailabilitySlot> slots = new ArrayList<>();
}