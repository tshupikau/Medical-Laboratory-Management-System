package com.medlab.management_system.service;

import com.medlab.management_system.model.dto.doctor.DoctorCreateDto;
import com.medlab.management_system.model.dto.doctor.DoctorResponseDto;
import com.medlab.management_system.model.dto.doctor.DoctorUpdateDto;
import com.medlab.management_system.model.entity.Doctor;
import com.medlab.management_system.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.medlab.management_system.util.NullUtils.updateIfPresent;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;


    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<DoctorResponseDto> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(this::convertToResponseDto)
                .toList();
    }

    public DoctorResponseDto getDoctorDtoById(Long id) {
        Doctor doctor = getDoctorEntityById(id);
        return convertToResponseDto(doctor);
    }

    public DoctorResponseDto createDoctor(DoctorCreateDto dto) {
        Doctor doctor = new Doctor();

        doctor.setFullName(dto.fullName());
        doctor.setSpecialization(dto.specialization());
        doctor.setPhoneNumber(dto.phoneNumber());

        Doctor savedDoctor = doctorRepository.save(doctor);
        return convertToResponseDto(savedDoctor);
    }

    public void updateDoctor(Long id, DoctorUpdateDto dto) {
        Doctor doctor = getDoctorEntityById(id);

        updateIfPresent(doctor::setFullName, dto.fullName());
        updateIfPresent(doctor::setSpecialization, dto.specialization());
        updateIfPresent(doctor::setPhoneNumber, dto.phoneNumber());

        doctorRepository.save(doctor);
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    private Doctor getDoctorEntityById(Long id) {
        return doctorRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Doctor not found"));
    }

    private DoctorResponseDto convertToResponseDto(Doctor doctor) {
        return new DoctorResponseDto(
                doctor.getId(),
                doctor.getFullName(),
                doctor.getSpecialization(),
                doctor.getPhoneNumber()
        );
    }
}
