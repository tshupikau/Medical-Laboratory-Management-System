package com.medlab.management_system.model.dto.medicalRecord;

import java.time.LocalDateTime;

public record MedicalRecordResponseDto(
        Long id,
        Long patientId,
        String title,
        LocalDateTime date,
        String diagnosis,
        String treatment
) {}