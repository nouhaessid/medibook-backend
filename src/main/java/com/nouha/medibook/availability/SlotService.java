package com.nouha.medibook.availability;

import com.nouha.medibook.doctor.DoctorProfileRepository;
import com.nouha.medibook.exception.BadRequestException;
import com.nouha.medibook.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SlotService {

    private static final int MAX_DAYS_RANGE = 62;
    private final SlotRepository slotRepository;
    private final DoctorProfileRepository doctorProfileRepository;
    private final SlotMapper slotMapper;
    private final Clock clock;

    public List<SlotResponse> getFreeSlotsByDate(Integer doctorId, LocalDate date) {
        if (!doctorProfileRepository.existsById(doctorId)) {
            throw new ResourceNotFoundException("Doctor not found with ID: " + doctorId);
        }
        return slotRepository.findFreeSlots(
                        doctorId,
                        date.atStartOfDay(),
                        date.plusDays(1).atStartOfDay(),
                        LocalDateTime.now(clock))
                .stream()
                .map(slotMapper::toSlotResponse)
                .toList();
    }

    public List<LocalDate> getDaysWithFreeSlots(Integer doctorId, LocalDate from, LocalDate to) {
        if (!doctorProfileRepository.existsById(doctorId)) {
            throw new ResourceNotFoundException("Doctor not found with id " + doctorId);
        }
        if (to.isBefore(from)) {
            throw new BadRequestException("'to' must not be before 'from'.");
        }
        if (ChronoUnit.DAYS.between(from, to) > MAX_DAYS_RANGE) {
            throw new BadRequestException(
                    "The date range is too long (maximum " + MAX_DAYS_RANGE + " days).");
        }
        return slotRepository.findFreeSlots(
                        doctorId,
                        from.atStartOfDay(),
                        to.plusDays(1).atStartOfDay(),
                        LocalDateTime.now(clock))
                .stream()
                .map(slot -> slot.getStartAt().toLocalDate())
                .distinct()
                .toList();
    }
}
