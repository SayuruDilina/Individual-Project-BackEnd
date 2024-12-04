package edu.icet.repository;

import edu.icet.entity.DayOutPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DayOutRepository extends JpaRepository<DayOutPackageEntity,Integer> {
    @Modifying
    @Query(nativeQuery = true,value = "UPDATE day_out_packages a SET a.available_sheets = available_sheets- :getQty WHERE a.day_outid = :id")
    void updateQuantity(@Param("getQty") Integer getQty, @Param("id") Integer id);
}
