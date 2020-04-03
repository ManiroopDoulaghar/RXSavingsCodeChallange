package com.pharmacy.location.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pharmacy {
    private String name;
    private String address;
    private String city;
    private String state;
    private int zip;
    private Location location;


}
