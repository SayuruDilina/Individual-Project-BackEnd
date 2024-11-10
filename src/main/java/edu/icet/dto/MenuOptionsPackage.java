package edu.icet.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MenuOptionsPackage {

    private Integer menuOptionId;
    private String  packageNum;
    private  String packageName;
    private List<String> packageDetails;
    private  byte[] image1;
    private  byte[] image2;
    private  byte[] image3;
    private  byte[] image4;
    private  Integer price;
    private Integer availableSheets;
}
