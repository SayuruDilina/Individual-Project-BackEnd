package edu.icet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MenuOptionPackages")
public class MenuOptionsPackageEntity {
    @Id

    private Integer menuOptionId;
    private String  packageNum;
    private  String packageName;
    @ElementCollection
    private List<String> packageDetails;
    @Column(name="image",columnDefinition ="MEDIUMBLOB")
    private  byte[] image;
    private  Integer price;
    private Integer availableSheets;
}
