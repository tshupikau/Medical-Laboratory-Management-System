package com.medlab.management_system.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    String fullName;

    @Column(name = "birth_date")
    LocalDate birthDate;

    public Patient() {}

}
