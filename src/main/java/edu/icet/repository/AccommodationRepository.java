package edu.icet.repository;

import edu.icet.dto.AccommodationPackage;
import edu.icet.entity.AccommodationPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccommodationRepository extends JpaRepository<AccommodationPackageEntity,Integer> {

    @Modifying
    @Query(nativeQuery = true,value = "UPDATE accommodations a SET a.available_qty = available_qty- :getQty WHERE a.accommodation_id = :id")
    int updateQuantity(@Param("id") Integer id, @Param("getQty") Integer getQty);
}
