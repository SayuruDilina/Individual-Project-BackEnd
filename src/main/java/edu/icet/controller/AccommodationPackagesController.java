package edu.icet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.dto.AccommodationPackage;
import edu.icet.service.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class AccommodationPackagesController {

    private final AccommodationService service;
private final ObjectMapper mapper;

    @PostMapping("/add-accommodation-package")
    public boolean postPackageDetails(
            @RequestParam("id")String id,
            @RequestParam("packageNum") String packageNum,
            @RequestParam("packageName") String packageName,
            @RequestParam("packageDetails") List<String> packageDetails,
            @RequestParam("image1")MultipartFile file1,
            @RequestParam("image2")MultipartFile file2,
            @RequestParam("image3")MultipartFile file3,
            @RequestParam("image4")MultipartFile file4,
            @RequestParam("price")Integer price,
            @RequestParam("availableQty")Integer availableQty
            ) throws IOException {
        byte[] bytes1= file1.getBytes();
        byte[] bytes2= file2.getBytes();
        byte[] bytes3= file3.getBytes();
        byte[] bytes4= file4.getBytes();
        AccommodationPackage accommodationPackage=new AccommodationPackage();
        accommodationPackage.setPackageNum(packageNum);
        accommodationPackage.setPackageName(packageName);
        accommodationPackage.setPackageDetails(packageDetails);
        accommodationPackage.setImage1(bytes1);
        accommodationPackage.setImage2(bytes2);
        accommodationPackage.setImage3(bytes3);
        accommodationPackage.setImage4(bytes4);
        accommodationPackage.setPrice(price);
        accommodationPackage.setAvailableQty(availableQty);
        accommodationPackage.setAccommodationId(Integer.parseInt(id));

        service.postPackage(accommodationPackage);

        return false;
    }

    @GetMapping("/get-accommodation-package")
    public List<AccommodationPackage> getPackageDetails(){

        return service.getPackageDetails();
    }
    @PutMapping("/update-accommodation-details")
    public boolean updatePackageDetails(
            @RequestParam("id")String id,
            @RequestParam("packageNum") String packageNum,
            @RequestParam("packageName") String packageName,
            @RequestParam("packageDetails") List<String> packageDetails,
            @RequestParam("image1")MultipartFile file1,
            @RequestParam("image2")MultipartFile file2,
            @RequestParam("image3")MultipartFile file3,
            @RequestParam("image4")MultipartFile file4,
            @RequestParam("price")Integer price,
            @RequestParam("availableQty")Integer availableQty
    ) throws IOException {
        byte[] bytes1= file1.getBytes();
        byte[] bytes2= file2.getBytes();
        byte[] bytes3= file3.getBytes();
        byte[] bytes4= file4.getBytes();
        AccommodationPackage accommodationPackage=new AccommodationPackage();
        accommodationPackage.setPackageNum(packageNum);
        accommodationPackage.setPackageName(packageName);
        accommodationPackage.setPackageDetails(packageDetails);
        accommodationPackage.setImage1(bytes1);
        accommodationPackage.setImage2(bytes2);
        accommodationPackage.setImage3(bytes3);
        accommodationPackage.setImage4(bytes4);
        accommodationPackage.setPrice(price);
        accommodationPackage.setAvailableQty(availableQty);
        accommodationPackage.setAccommodationId(Integer.parseInt(id));

        service.updatePackage(accommodationPackage);

        return false;
    }

    @GetMapping("/search-by-id/{id}")
    public AccommodationPackage searchById(@PathVariable Integer id){
        return service.searchById(id);
    }
    @DeleteMapping("/delete-by-id/{id}")
     public void deleteById(@PathVariable Integer id){
             service.deleteById(id);
     }
}
