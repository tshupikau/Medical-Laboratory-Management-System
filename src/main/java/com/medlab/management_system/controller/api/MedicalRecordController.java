package com.medlab.management_system.controller.api;

import com.medlab.management_system.model.entity.MedicalRecord;
import com.medlab.management_system.model.entity.Patient;
import com.medlab.management_system.repository.MedicalRecordRepository;
import com.medlab.management_system.repository.PatientRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class MedicalRecordController {
    private final MedicalRecordRepository medicalRecordRepository;
    private final PatientRepository patientRepository;

    public MedicalRecordController(MedicalRecordRepository medicalRecordRepository,
                                   PatientRepository patientRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.patientRepository = patientRepository;
    }

    @GetMapping("/{patientId}/records")
    public List<MedicalRecord> getAllPatientRecords(@PathVariable Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));;
        return medicalRecordRepository.findByPatient(patient);
    }

    @GetMapping("/{patientId}/records/{recordId}")
    public MedicalRecord getPatientRecordById(@PathVariable Long patientId, @PathVariable Long recordId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        return medicalRecordRepository.findByIdAndPatientId(recordId, patientId);
    }

    @PostMapping("/{patientId}/records/add")
    public MedicalRecord createPatientRecord(@RequestBody MedicalRecord record, @PathVariable Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        record.setPatient(patient);
        return medicalRecordRepository.save(record);
    }

    @DeleteMapping("/{patientId}/records/delete/{recordId}")
    public void deletePatientRecord(@PathVariable Long patientId, @PathVariable Long recordId) {
        medicalRecordRepository.deleteByIdAndPatientId(recordId, patientId);
    }
}
