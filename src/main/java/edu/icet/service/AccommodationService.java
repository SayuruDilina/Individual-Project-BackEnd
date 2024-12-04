package edu.icet.service;

import edu.icet.dto.AccommodationPackage;

import java.util.List;

public interface AccommodationService {

    void postPackage(AccommodationPackage accommodationPackage);

    List<AccommodationPackage> getPackageDetails();

    void updatePackage(AccommodationPackage accommodationPackage);

    AccommodationPackage searchById(Integer id);

    void deleteById(Integer id);
}
