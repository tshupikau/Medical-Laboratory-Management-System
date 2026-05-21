package com.medlab.management_system.model.dto.doctor;

public record DoctorUpdateDto(
        String fullName,
        String specialization,
        String phoneNumber
) {}