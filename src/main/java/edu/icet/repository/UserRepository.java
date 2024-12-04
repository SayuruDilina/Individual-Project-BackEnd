package edu.icet.repository;


import edu.icet.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    @Query(nativeQuery = true,value = "Select * from user where email=:email")
    Optional<UserEntity>checkEmail(@Param("email")String email);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "UPDATE user SET password = :password  where email=:email")
   void resetPassword(@Param("email")String email, @Param("password") String password);
}
