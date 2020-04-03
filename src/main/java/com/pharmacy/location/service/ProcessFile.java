package com.pharmacy.location.service;

import com.pharmacy.location.model.Location;
import com.pharmacy.location.model.Pharmacy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProcessFile {

    Logger logger = LoggerFactory.getLogger(ProcessFile.class);
    public  List<Pharmacy> readFile() throws IOException {
        List<Pharmacy> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/pharmacies.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Pharmacy pharmacy = new Pharmacy(values[0],values[1],values[2],values[3],Integer.parseInt(values[4]),new Location(Double.parseDouble(values[5]),Double.parseDouble(values[6])));
                records.add(pharmacy);
            }
        }
        logger.info("All records +" + records.toString());
        return records;
    }
}
