package com.pharmacy.location.service;

import com.pharmacy.location.model.Location;
import com.pharmacy.location.model.Pharmacy;
import com.pharmacy.location.model.PharmacyAndDistance;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class Computations {

    Logger logger = LoggerFactory.getLogger(Computations.class);

    public double getDistanceBetweenTwoLocations(Location location1, Location location2){

        double lon1 = Math.toRadians(location1.getLongitude());
        double lon2 = Math.toRadians(location2.getLongitude());
        double lat1 = Math.toRadians(location1.getLatitude());
        double lat2 = Math.toRadians(location2.getLatitude());

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        logger.info("Distance between " + location1.toString() + "and " + location2.toString() + "is " + r);
        return(c * r);
    }

    public  List<PharmacyAndDistance> getPharmacyAndDistanceToLocation(Location location, List<Pharmacy> pharmacyList){

        List<PharmacyAndDistance> allDistances = new ArrayList<>();
        pharmacyList.forEach(pharmacy -> {
            allDistances.add(new PharmacyAndDistance(pharmacy,getDistanceBetweenTwoLocations(location, pharmacy.getLocation())));
        });
        logger.info("Distance between location and All pharmacis " + allDistances.toString());
        return allDistances;
    }

    public PharmacyAndDistance getClosestPharmacy(List<PharmacyAndDistance> allPharmaciesAndDistance){

        Collections.sort(allPharmaciesAndDistance, new SortbyDistance());
        logger.info("Sorted Distances" + allPharmaciesAndDistance.toString());
        return allPharmaciesAndDistance.get(0);
    }



    class SortbyDistance implements Comparator<PharmacyAndDistance>
    {
        // Used for sorting in ascending order of
        // roll number
        public int compare(PharmacyAndDistance a, PharmacyAndDistance b)
        {

            return Double.compare(a.getDistance(),b.getDistance());
        }
    }
}
