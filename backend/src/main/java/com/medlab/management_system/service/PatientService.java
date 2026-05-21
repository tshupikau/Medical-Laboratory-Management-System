package com.medlab.management_system.service;

import com.medlab.management_system.model.dto.patient.PatientCreateDto;
import com.medlab.management_system.model.dto.patient.PatientResponseDto;
import com.medlab.management_system.model.dto.patient.PatientUpdateDto;
import com.medlab.management_system.model.entity.Patient;
import com.medlab.management_system.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.medlab.management_system.util.NullUtils.updateIfPresent;


@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDto> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(this::convertToResponseDto)
                .toList();
    }

    public PatientResponseDto getPatientById(@PathVariable Long id) {
        return convertToResponseDto(patientRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Patient not found")));
    }

    private Patient getPatientEntityById(Long id) {
        return patientRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Patient not found"));
    }

    public PatientResponseDto createPatient(@RequestBody PatientCreateDto patientCreateDto) {
        Patient patient = new Patient();

        patient.setFullName(patientCreateDto.fullName());
        patient.setAge(patientCreateDto.age());
        patient.setBirthDate(patientCreateDto.birthDate());
        patient.setPhoneNumber(patientCreateDto.phoneNumber());
        patient.setGender(patientCreateDto.gender());

        return convertToResponseDto(patientRepository.save(patient));
    }

    public void updatePatient(@PathVariable Long id, @RequestBody PatientUpdateDto newPatientData) {

        Patient patient = getPatientEntityById(id);

        updateIfPresent(patient::setFullName, newPatientData.fullName());
        updateIfPresent(patient::setAge, newPatientData.age());
        updateIfPresent(patient::setBirthDate, newPatientData.birthDate());
        updateIfPresent(patient::setPhoneNumber, newPatientData.phoneNumber());
        updateIfPresent(patient::setGender, newPatientData.gender());

        patientRepository.save(patient);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    private PatientResponseDto convertToResponseDto(Patient patient) {
        return new PatientResponseDto(
                patient.getId(),
                patient.getFullName(),
                patient.getAge(),
                patient.getBirthDate(),
                patient.getPhoneNumber(),
                patient.getGender()
        );
    }
}
