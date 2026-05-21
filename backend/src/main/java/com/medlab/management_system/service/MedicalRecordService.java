package com.medlab.management_system.service;

import com.medlab.management_system.model.dto.medicalRecord.MedicalRecordCreateDto;
import com.medlab.management_system.model.dto.medicalRecord.MedicalRecordResponseDto;
import com.medlab.management_system.model.dto.medicalRecord.MedicalRecordUpdateDto;
import com.medlab.management_system.model.entity.MedicalRecord;
import com.medlab.management_system.model.entity.Patient;
import com.medlab.management_system.repository.MedicalRecordRepository;
import com.medlab.management_system.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.medlab.management_system.util.NullUtils.updateIfPresent;

@Service
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final PatientRepository patientRepository;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository, PatientRepository patientRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.patientRepository = patientRepository;
    }

    public List<MedicalRecordResponseDto> getAllRecords() {
        return medicalRecordRepository.findAll().stream()
                .map(this::convertToResponseDto)
                .toList();
    }

    public MedicalRecordResponseDto getRecordDtoById(Long id) {
        MedicalRecord record = getRecordEntityById(id);
        return convertToResponseDto(record);
    }

    public MedicalRecordResponseDto createRecord(MedicalRecordCreateDto dto) {
        Patient patient = patientRepository.findById(dto.patientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        MedicalRecord record = new MedicalRecord();
        record.setPatient(patient);
        record.setTitle(dto.title());
        record.setDate(dto.date() != null ? dto.date() : LocalDateTime.now()); // Если дата пустая, ставим текущую
        record.setDiagnosis(dto.diagnosis());
        record.setTreatment(dto.treatment());

        MedicalRecord savedRecord = medicalRecordRepository.save(record);
        return convertToResponseDto(savedRecord);
    }

    public void updateRecord(Long id, MedicalRecordUpdateDto dto) {
        MedicalRecord record = getRecordEntityById(id);

        updateIfPresent(record::setTitle, dto.title());
        updateIfPresent(record::setDate, dto.date());
        updateIfPresent(record::setDiagnosis, dto.diagnosis());
        updateIfPresent(record::setTreatment, dto.treatment());

        medicalRecordRepository.save(record);
    }

    public void deleteRecord(Long id) {
        medicalRecordRepository.deleteById(id);
    }

    private MedicalRecord getRecordEntityById(Long id) {
        return medicalRecordRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Medical record not found"));
    }

    private MedicalRecordResponseDto convertToResponseDto(MedicalRecord record) {
        return new MedicalRecordResponseDto(
                record.getId(),
                record.getPatient() != null ? record.getPatient().getId() : null, // Забираем только ID
                record.getTitle(),
                record.getDate(),
                record.getDiagnosis(),
                record.getTreatment()
        );
    }
}