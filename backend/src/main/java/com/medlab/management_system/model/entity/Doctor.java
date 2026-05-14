package com.medlab.management_system.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Doctor
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    String fullName;

    @Column(name = "specialization")
    String specialization;

    public Doctor() {}
}
