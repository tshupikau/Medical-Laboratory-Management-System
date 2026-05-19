package com.medlab.management_system.controller.api;

import com.medlab.management_system.model.entity.Patient;
import com.medlab.management_system.repository.PatientRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "http://localhost:5173")
public class PatientController {
    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    @PostMapping("/add")
    public Patient create(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }

    @PutMapping("/update/{id}")
    public void updatePatientById(@PathVariable Long id, @RequestBody Patient newPatientData) {
        Patient patient = patientRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Patient not found with id: " + id)
        );
        //TODO
        patient.setFullName(newPatientData.getFullName() != null ?
                newPatientData.getFullName() : patient.getFullName());

        patient.setBirthDate(newPatientData.getBirthDate() != null ?
                newPatientData.getBirthDate() : patient.getBirthDate());
        patient.setAge();
        patientRepository.save(patient);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        patientRepository.deleteById(id);
    }
}
