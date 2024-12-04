package edu.icet.repository;


import edu.icet.entity.MenuOptionsPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MenuOptionsRepository extends JpaRepository<MenuOptionsPackageEntity,Integer> {

     @Modifying
    @Query(nativeQuery = true,value = "UPDATE menu_option_packages a SET a.available_sheets = available_sheets- :getQty WHERE a.menu_option_id = :id")
    void updateQuantity( @Param("getQty") Integer getQty,@Param("id") Integer id);
}
