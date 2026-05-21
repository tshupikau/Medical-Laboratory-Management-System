package com.medlab.management_system.controller.api;

import com.medlab.management_system.model.dto.doctor.DoctorCreateDto;
import com.medlab.management_system.model.dto.doctor.DoctorResponseDto;
import com.medlab.management_system.model.dto.doctor.DoctorUpdateDto;
import com.medlab.management_system.model.entity.Doctor;
import com.medlab.management_system.model.entity.Patient;
import com.medlab.management_system.repository.DoctorRepository;
import com.medlab.management_system.service.DoctorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = "http://localhost:5173")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public List<DoctorResponseDto> getAll() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public DoctorResponseDto getById(@PathVariable Long id) {
        return doctorService.getDoctorDtoById(id);
    }

    @PostMapping("/add")
    public DoctorResponseDto create(@RequestBody DoctorCreateDto doctorCreateDto) {
        return doctorService.createDoctor(doctorCreateDto);
    }

    @PutMapping("/update/{id}")
    public void updateById(
            @PathVariable Long id,
            @RequestBody DoctorUpdateDto dto
    ) {
        doctorService.updateDoctor(id, dto);
    }
}
