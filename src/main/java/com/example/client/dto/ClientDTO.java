package com.example.client.dto;

import com.example.client.entities.Client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.PastOrPresent;


import java.time.LocalDate;

public class ClientDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "CPF is required")
    @Pattern(
            regexp = "\\d{11}",
            message = "CPF must have 11 digits"
    )
    private String cpf;

    @PositiveOrZero(message = "Income must be zero or positive")
    private Double income;

    @PastOrPresent(message = "Birth date cannot be in the future")
    private LocalDate birthDate;

    @PositiveOrZero(message = "Children must be zero or positive")
    private Integer children;

    public ClientDTO() {
    }

    // Construtor a partir da Entity
    public ClientDTO(Client entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.cpf = entity.getCpf();
        this.income = entity.getIncome();
        this.birthDate = entity.getBirthDate();
        this.children = entity.getChildren();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }
}
