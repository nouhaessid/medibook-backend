package com.nouha.medibook.availability;

import com.nouha.medibook.doctor.DoctorProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class SlotController {

    private final SlotService slotService;

    @GetMapping("/doctors/{doctorId}/slots/days")
    public ResponseEntity<List<LocalDate>> getDaysWithFreeSlots(
            @PathVariable Integer doctorId,
            @RequestParam LocalDate from,
            @RequestParam LocalDate to
    ) {
        return ResponseEntity.ok(slotService.getDaysWithFreeSlots(doctorId, from, to));
    }

    @GetMapping("/doctors/{doctorId}/slots")
    public ResponseEntity<List<SlotResponse>> getFreeSlotsByDate(
            @PathVariable Integer doctorId,
            @RequestParam LocalDate date
    ){
        return ResponseEntity.ok(slotService.getFreeSlotsByDate(doctorId, date));
    }


}
