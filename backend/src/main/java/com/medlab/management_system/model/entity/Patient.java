package com.medlab.management_system.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("fullName")
    @Column(name = "full_name")
    private String fullName;

    @JsonProperty("age")
    @Column(name = "age")
    private Integer age;

    @JsonProperty("birthDate")
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @JsonProperty("phoneNumber")
    @Column(name = "phone_number")
    private String phoneNumber;

    @JsonProperty("gender")
    @Column(name = "gender")
    private String gender;

    public Patient() {}


}
