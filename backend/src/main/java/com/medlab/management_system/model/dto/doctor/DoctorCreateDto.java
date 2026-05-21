package com.medlab.management_system.model.dto.doctor;

public record DoctorCreateDto(
        String fullName,
        String specialization,
        String phoneNumber
) {}
