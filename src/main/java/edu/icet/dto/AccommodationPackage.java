package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AccommodationPackage {

    private Integer accommodationId;
    private String  packageNum;
    private  String packageName;
    private List<String> packageDetails;
    private  byte[] image;
    private  Integer price;
    private Integer availableQty;


}
