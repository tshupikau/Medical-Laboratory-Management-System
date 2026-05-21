package com.medlab.management_system.model.dto.medicalRecord;

import java.time.LocalDateTime;

public record MedicalRecordCreateDto(
        Long patientId,
        String title,
        LocalDateTime date,
        String diagnosis,
        String treatment
) {}