package edu.icet.repository;

import edu.icet.dto.MenuOptionsPackage;
import edu.icet.entity.MenuOptionsPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuOptionsRepository extends JpaRepository<MenuOptionsPackageEntity,Integer> {
}
