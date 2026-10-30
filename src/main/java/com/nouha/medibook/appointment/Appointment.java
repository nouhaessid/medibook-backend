package com.nouha.medibook.appointment;

import com.nouha.medibook.availability.AvailabilitySlot;
import com.nouha.medibook.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private User patient;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "slot_id", nullable = false)
    private AvailabilitySlot slot;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private String cancellationReason;




}
