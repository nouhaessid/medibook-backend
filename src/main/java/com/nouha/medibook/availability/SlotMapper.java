package com.nouha.medibook.availability;

import org.springframework.stereotype.Service;
@Service
public class SlotMapper {

    public SlotResponse toSlotResponse(AvailabilitySlot slot) {
        return new SlotResponse(slot.getId(), slot.getStartAt(), slot.getEndAt());
    }
}