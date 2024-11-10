package edu.icet.repository;

import edu.icet.entity.DayOutPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayOutRepository extends JpaRepository<DayOutPackageEntity,Integer> {
}
