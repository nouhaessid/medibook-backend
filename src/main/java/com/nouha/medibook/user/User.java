package com.nouha.medibook.user;

import com.nouha.medibook.appointment.Appointment;
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
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @OneToMany(mappedBy = "patient")
    @Builder.Default
    private List<Appointment> appointments = new ArrayList<>();
}
