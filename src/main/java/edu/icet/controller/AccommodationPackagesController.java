package edu.icet.controller;

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
@RequestMapping("/accommodation")
public class AccommodationPackagesController {

    private final AccommodationService service;


    @PostMapping("/add-accommodation-package")
    public boolean postPackageDetails(
            @RequestParam("id")String id,
            @RequestParam("packageNum") String packageNum,
            @RequestParam("packageName") String packageName,
            @RequestParam("packageDetails") List<String> packageDetails,
            @RequestParam("image")MultipartFile file,
            @RequestParam("price")Integer price,
            @RequestParam("availableQty")Integer availableQty
            ) throws IOException {
        byte[] bytes= file.getBytes();

        AccommodationPackage accommodationPackage=new AccommodationPackage();
        accommodationPackage.setPackageNum(packageNum);
        accommodationPackage.setPackageName(packageName);
        accommodationPackage.setPackageDetails(packageDetails);
        accommodationPackage.setImage(bytes);
        accommodationPackage.setPrice(price);
        accommodationPackage.setAvailableQty(availableQty);
        accommodationPackage.setAccommodationId(Integer.parseInt(id));

        service.postPackage(accommodationPackage);

        return false;
    }

    @GetMapping("/get-all-accommodation-packages")
    public List<AccommodationPackage> getPackageDetails(){

        return service.getPackageDetails();
    }
    @PutMapping("/update-accommodation-package")
    public boolean updatePackageDetails(
            @RequestParam("id")String id,
            @RequestParam("packageNum") String packageNum,
            @RequestParam("packageName") String packageName,
            @RequestParam("packageDetails") List<String> packageDetails,
            @RequestParam("image")MultipartFile file,
            @RequestParam("price")Integer price,
            @RequestParam("availableQty")Integer availableQty
    ) throws IOException {
        byte[] bytes= file.getBytes();
        AccommodationPackage accommodationPackage=new AccommodationPackage();
        accommodationPackage.setPackageNum(packageNum);
        accommodationPackage.setPackageName(packageName);
        accommodationPackage.setPackageDetails(packageDetails);
        accommodationPackage.setImage(bytes);
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
