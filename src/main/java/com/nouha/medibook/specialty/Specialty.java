package com.nouha.medibook.specialty;

import com.nouha.medibook.doctor.DoctorProfile;
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
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "specialty")
    @Builder.Default
    private List<DoctorProfile> doctors = new ArrayList<>();
}