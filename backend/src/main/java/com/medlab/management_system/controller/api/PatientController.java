package com.medlab.management_system.controller.api;

import com.medlab.management_system.model.dto.patient.PatientCreateDto;
import com.medlab.management_system.model.dto.patient.PatientResponseDto;
import com.medlab.management_system.model.dto.patient.PatientUpdateDto;
import org.springframework.web.bind.annotation.*;
import com.medlab.management_system.service.PatientService;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "http://localhost:5173")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<PatientResponseDto> getAll() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public PatientResponseDto getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    @PostMapping("/add")
    public PatientResponseDto create(@RequestBody PatientCreateDto patientCreateDto) {
        return patientService.createPatient(patientCreateDto);
    }

    @PutMapping("/update/{id}")
    public void updatePatientById(
            @PathVariable Long id,
            @RequestBody PatientUpdateDto patientUpdateDto
    ) {
        patientService.updatePatient(id, patientUpdateDto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        patientService.deletePatient(id);
    }

}
