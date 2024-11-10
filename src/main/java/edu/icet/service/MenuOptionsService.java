package edu.icet.service;

import edu.icet.dto.AccommodationPackage;
import edu.icet.dto.MenuOptionsPackage;

import java.util.List;

public interface MenuOptionsService {
    boolean postPackage(MenuOptionsPackage menuOptionsPackage);

    List<MenuOptionsPackage> getPackageDetails();

    MenuOptionsPackage searchById(Integer id);

    void deleteById(Integer id);

    void updatePackage(MenuOptionsPackage menuOptionsPackage);
}
