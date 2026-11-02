package com.nouha.medibook.availability;

import java.time.LocalDateTime;

public record SlotResponse(
        Integer id,
        LocalDateTime startAt,
        LocalDateTime endAt
) {}