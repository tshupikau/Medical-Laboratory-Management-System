package com.medlab.management_system.model.dto.doctor;

public record DoctorResponseDto(
        Long id,
        String fullName,
        String specialization,
        String phoneNumber
) {}