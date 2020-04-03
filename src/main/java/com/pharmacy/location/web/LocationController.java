package com.pharmacy.location.web;


import com.pharmacy.location.model.Location;
import com.pharmacy.location.model.Pharmacy;
import com.pharmacy.location.model.PharmacyAndDistance;
import com.pharmacy.location.service.Computations;
import com.pharmacy.location.service.ProcessFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.model.IComment;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class LocationController {

    Computations computations;
    ProcessFile processFile;
    Logger logger = LoggerFactory.getLogger(LocationController.class);

    public LocationController(Computations computations, ProcessFile processFile) {
        this.computations = computations;
        this.processFile = processFile;
    }

    @PostMapping("/getClosestPharmacy")
    public String getClosestPharmacy(@ModelAttribute Location location , Map<String, Object> modal) throws IOException {

        PharmacyAndDistance pharmacy =  computations.getClosestPharmacy(computations.getPharmacyAndDistanceToLocation(location, processFile.readFile()));

        modal.put("Source", location);
        modal.put("closest", pharmacy.getPharmacy());
        modal.put("distance", pharmacy.getDistance());
        return "showNearestPharmacy";
//        if (pharmacy != null ){
//            return ResponseEntity.status(HttpStatus.OK).body(pharmacy);
//        }else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(pharmacy);
//        }
    }

    @GetMapping("/getAllPharmacies")
    public String geAllPharmacy(@ModelAttribute Location location, Map<String, Object> modal ) throws IOException {

        List<Pharmacy> allPharmacies =  processFile.readFile();

        modal.put("allPharmacies", allPharmacies);

        return "allPharmacies";

    }
}
