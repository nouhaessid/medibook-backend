package com.nouha.medibook.availability;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SlotRepository extends JpaRepository<AvailabilitySlot, Integer> {

    @Query("""
    select s from AvailabilitySlot s
    where s.doctor.id = :doctorId
      and s.startAt >= :from and s.startAt < :to
      and s.startAt > :now
      and not exists (
          select a from Appointment a
          where a.slot = s
            and a.status <> com.nouha.medibook.appointment.AppointmentStatus.CANCELLED)
    order by s.startAt
    """)
    List<AvailabilitySlot> findFreeSlots(@Param("doctorId") Integer doctorId,
                                         @Param("from") LocalDateTime from,
                                         @Param("to") LocalDateTime to,
                                         @Param("now") LocalDateTime now);
}
