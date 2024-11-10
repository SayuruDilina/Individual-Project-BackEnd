package edu.icet.repository;

import edu.icet.dto.User;
import edu.icet.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
//    @Query(nativeQuery = true,value = "Select * from user where email=:email and password=:password")
//    Optional<UserEntity> checkEmailPassword(@Param("email")String email, @Param("password")String password);

    @Query(nativeQuery = true,value = "Select * from user where email=:email")
    Optional<UserEntity>checkEmail(@Param("email")String email);
}
