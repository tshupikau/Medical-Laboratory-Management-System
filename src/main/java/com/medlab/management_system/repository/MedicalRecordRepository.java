package com.medlab.management_system.repository;

import com.medlab.management_system.model.entity.MedicalRecord;
import com.medlab.management_system.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    List<MedicalRecord> findByPatient(Patient patient);
    MedicalRecord findByIdAndPatientId(Long id, Long patientId);
    void deleteByIdAndPatientId(Long id, Long patientId);
}
