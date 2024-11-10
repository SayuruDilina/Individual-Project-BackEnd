package edu.icet.controller;

import edu.icet.dto.AccommodationPackage;
import edu.icet.dto.DayOutPackage;
import edu.icet.dto.MenuOptionsPackage;
import edu.icet.service.DayOutPackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class DayOutPackageController {

    private final DayOutPackageService service;

    @PostMapping("/add-day-out-package")
    public boolean postPackageDetails(
            @RequestParam("packageNum") String packageNum,
            @RequestParam("packageName") String packageName,
            @RequestParam("packageDetails") List<String> packageDetails,
            @RequestParam("image1") MultipartFile file1,
            @RequestParam("image2")MultipartFile file2,
            @RequestParam("image3")MultipartFile file3,
            @RequestParam("image4")MultipartFile file4,
            @RequestParam("price")Integer price,
            @RequestParam("availableQty")Integer availableSheets
    ) throws IOException {
        byte[] bytes1= file1.getBytes();
        byte[] bytes2= file2.getBytes();
        byte[] bytes3= file3.getBytes();
        byte[] bytes4= file4.getBytes();
        DayOutPackage dayOutPackage=new DayOutPackage();
        dayOutPackage.setPackageNum(packageNum);
        dayOutPackage.setPackageName(packageName);
        dayOutPackage.setPackageDetails(packageDetails);
        dayOutPackage.setImage1(bytes1);
        dayOutPackage.setImage2(bytes2);
        dayOutPackage.setImage3(bytes3);
        dayOutPackage.setImage4(bytes4);
        dayOutPackage.setPrice(price);
        dayOutPackage.setAvailableSheets(availableSheets);
        service.postPackage(dayOutPackage);
        return false;
    }
    @GetMapping("/get-day-out-package")
    public List<DayOutPackage> getPackageDetails(){

        return service.getPackageDetails();
    }
    @PutMapping("/update-dayout-details")
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
            @RequestParam("availableQty")Integer availableSheets
    ) throws IOException {
        byte[] bytes1= file1.getBytes();
        byte[] bytes2= file2.getBytes();
        byte[] bytes3= file3.getBytes();
        byte[] bytes4= file4.getBytes();
        DayOutPackage dayOutPackage=new DayOutPackage();
        dayOutPackage.setDayOutID(Integer.parseInt(id));
        dayOutPackage.setPackageNum(packageNum);
        dayOutPackage.setPackageName(packageName);
        dayOutPackage.setPackageDetails(packageDetails);
        dayOutPackage.setImage1(bytes1);
        dayOutPackage.setImage2(bytes2);
        dayOutPackage.setImage3(bytes3);
        dayOutPackage.setImage4(bytes4);
        dayOutPackage.setPrice(price);
        dayOutPackage.setAvailableSheets(availableSheets);

        service.updatePackage(dayOutPackage);

        return false;
    }
    @GetMapping("/search-by-id-dayout/{id}")
    public DayOutPackage searchById(@PathVariable Integer id){
        return service.searchById(id);
    }
    @DeleteMapping("/delete-by-id-dayout/{id}")
    public void deleteById(@PathVariable Integer id){
        service.deleteById(id);
    }

}
