package com.nouha.medibook.availability;

import com.nouha.medibook.appointment.Appointment;
import com.nouha.medibook.doctor.DoctorProfile;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class AvailabilitySlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private DoctorProfile doctor;

    @Column(nullable = false)
    private LocalDateTime startAt;

    @Column(nullable = false)
    private LocalDateTime endAt;

    @OneToMany(mappedBy = "slot")
    @Builder.Default
    private List<Appointment> appointments = new ArrayList<>();
}
