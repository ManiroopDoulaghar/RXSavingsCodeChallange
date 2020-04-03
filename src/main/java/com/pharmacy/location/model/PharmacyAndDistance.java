package com.pharmacy.location.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PharmacyAndDistance {
    private  Pharmacy pharmacy;
    private Double distance;
}
