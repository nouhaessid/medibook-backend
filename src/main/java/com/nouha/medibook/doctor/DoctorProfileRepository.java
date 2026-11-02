package com.nouha.medibook.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface DoctorProfileRepository extends JpaRepository<DoctorProfile, Integer> {
    @Query("""
        select d from DoctorProfile d
        join d.user u
        where (:specialtyId is null or d.specialty.id = :specialtyId)
          and lower(u.name) like lower(concat('%', :name, '%'))
          and lower(coalesce(d.clinicAddress, '')) like lower(concat('%', :address, '%'))
        """)
    Page<DoctorProfile> search(@Param("specialtyId") Integer specialtyId,
                               @Param("name") String name,
                               @Param("address") String address,
                               Pageable pageable);
}
