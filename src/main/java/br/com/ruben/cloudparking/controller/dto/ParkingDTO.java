package br.com.ruben.cloudparking.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParkingDTO {

    private String id;
    private String license;
    private String state;
    private String model;
    private String color;
    private LocalDate entryDate;
    private LocalDate exitDate;
    private Double bill;

    public void setId(String id) {
    }
}
