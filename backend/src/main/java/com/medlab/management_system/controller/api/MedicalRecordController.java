package com.medlab.management_system.controller.api;

import com.medlab.management_system.model.dto.medicalRecord.MedicalRecordCreateDto;
import com.medlab.management_system.model.dto.medicalRecord.MedicalRecordResponseDto;
import com.medlab.management_system.model.dto.medicalRecord.MedicalRecordUpdateDto;
import com.medlab.management_system.service.MedicalRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/records")
@CrossOrigin(origins = "http://localhost:5173")
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @GetMapping
    public List<MedicalRecordResponseDto> getAll() {
        return medicalRecordService.getAllRecords();
    }

    @GetMapping("/{id}")
    public MedicalRecordResponseDto getById(@PathVariable Long id) {
        return medicalRecordService.getRecordDtoById(id);
    }

    @PostMapping("/add")
    public MedicalRecordResponseDto create(@RequestBody MedicalRecordCreateDto dto) {
        return medicalRecordService.createRecord(dto);
    }

    @PutMapping("/update/{id}")
    public void updateById(
            @PathVariable Long id,
            @RequestBody MedicalRecordUpdateDto dto
    ) {
        medicalRecordService.updateRecord(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        medicalRecordService.deleteRecord(id);
    }
}