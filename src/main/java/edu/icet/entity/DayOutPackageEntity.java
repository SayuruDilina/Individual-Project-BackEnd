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
public class DayOutPackageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer dayOutID;
    private String  packageNum;
    private  String packageName;
    @ElementCollection
    private List<String> packageDetails;
    @Column(name="image1",columnDefinition ="MEDIUMBLOB")
    private  byte[] image1;
    @Column(name="image2",columnDefinition ="MEDIUMBLOB")
    private  byte[] image2;
    @Column(name="image3",columnDefinition ="MEDIUMBLOB")
    private  byte[] image3;
    @Column(name="image4",columnDefinition ="MEDIUMBLOB")
    private  byte[] image4;
    private  Integer price;
    private Integer availableSheets;
}
