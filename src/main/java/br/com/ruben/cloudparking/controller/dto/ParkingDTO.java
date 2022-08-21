package br.com.ruben.cloudparking.controller.dto;

import java.time.LocalDate;

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
