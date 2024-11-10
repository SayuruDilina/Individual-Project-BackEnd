package edu.icet.service;

import edu.icet.dto.DayOutPackage;

import java.util.List;

public interface DayOutPackageService {
    void postPackage(DayOutPackage dayOutPackage);

    List<DayOutPackage> getPackageDetails();

    DayOutPackage searchById(Integer id);

    void deleteById(Integer id);

    void updatePackage(DayOutPackage dayOutPackage);
}
