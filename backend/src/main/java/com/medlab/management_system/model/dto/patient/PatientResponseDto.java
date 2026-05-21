package com.medlab.management_system.model.dto.patient;

import java.time.LocalDate;

public record PatientResponseDto(
        Long id,
        String fullName,
        Integer age,
        LocalDate birthDate,
        String phoneNumber,
        String gender
) {}