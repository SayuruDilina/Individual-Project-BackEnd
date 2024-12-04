package edu.icet.controller;


import edu.icet.dto.DayOutPackage;
import edu.icet.service.DayOutPackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/day-out")
@RequiredArgsConstructor
public class DayOutPackageController {

    private final DayOutPackageService service;

    @PostMapping("/add-day-out-package")
    public boolean postPackageDetails(
            @RequestParam("id")String id,
            @RequestParam("packageNum") String packageNum,
            @RequestParam("packageName") String packageName,
            @RequestParam("packageDetails") List<String> packageDetails,
            @RequestParam("image") MultipartFile file,
            @RequestParam("price")Integer price,
            @RequestParam("availableQty")Integer availableSheets
    ) throws IOException {
        byte[] bytes= file.getBytes();
        DayOutPackage dayOutPackage=new DayOutPackage();
        dayOutPackage.setDayOutID(Integer.parseInt(id));
        dayOutPackage.setPackageNum(packageNum);
        dayOutPackage.setPackageName(packageName);
        dayOutPackage.setPackageDetails(packageDetails);
        dayOutPackage.setImage(bytes);
        dayOutPackage.setPrice(price);
        dayOutPackage.setAvailableSheets(availableSheets);
        service.postPackage(dayOutPackage);
        return false;
    }
    @GetMapping("/get-all-day-out-packages")
    public List<DayOutPackage> getPackageDetails(){

        return service.getPackageDetails();
    }
    @PutMapping("/update-day-out-package")
    public boolean updatePackageDetails(
            @RequestParam("id")String id,
            @RequestParam("packageNum") String packageNum,
            @RequestParam("packageName") String packageName,
            @RequestParam("packageDetails") List<String> packageDetails,
            @RequestParam("image")MultipartFile file,
            @RequestParam("price")Integer price,
            @RequestParam("availableQty")Integer availableSheets
    ) throws IOException {
        byte[] bytes= file.getBytes();
        DayOutPackage dayOutPackage=new DayOutPackage();
        dayOutPackage.setDayOutID(Integer.parseInt(id));
        dayOutPackage.setPackageNum(packageNum);
        dayOutPackage.setPackageName(packageName);
        dayOutPackage.setPackageDetails(packageDetails);
        dayOutPackage.setImage(bytes);
        dayOutPackage.setPrice(price);
        dayOutPackage.setAvailableSheets(availableSheets);

        service.updatePackage(dayOutPackage);

        return false;
    }
    @GetMapping("/search-by-id/{id}")
    public DayOutPackage searchById(@PathVariable Integer id){
        return service.searchById(id);
    }
    @DeleteMapping("/delete-by-id/{id}")
    public void deleteById(@PathVariable Integer id){
        service.deleteById(id);
    }

}
