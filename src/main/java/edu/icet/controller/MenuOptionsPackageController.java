package edu.icet.controller;

import edu.icet.dto.AccommodationPackage;
import edu.icet.dto.DayOutPackage;
import edu.icet.dto.MenuOptionsPackage;
import edu.icet.service.MenuOptionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class MenuOptionsPackageController {

  private  final MenuOptionsService service;

    @PostMapping("/add-menu-option-package")
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
        MenuOptionsPackage menuOptionsPackage=new MenuOptionsPackage();
        menuOptionsPackage.setPackageNum(packageNum);
        menuOptionsPackage.setPackageName(packageName);
        menuOptionsPackage.setPackageDetails(packageDetails);
        menuOptionsPackage.setImage1(bytes1);
        menuOptionsPackage.setImage2(bytes2);
        menuOptionsPackage.setImage3(bytes3);
        menuOptionsPackage.setImage4(bytes4);
        menuOptionsPackage.setPrice(price);
        menuOptionsPackage.setAvailableSheets(availableSheets);
        service.postPackage(menuOptionsPackage);
        return false;
    }
    @GetMapping("/get-menu-option-package")
    public List<MenuOptionsPackage> getPackageDetails(){

        return service.getPackageDetails();
    }
    @PutMapping("/update-menuoptions-details")
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
        MenuOptionsPackage menuOptionsPackage=new MenuOptionsPackage();
        menuOptionsPackage.setMenuOptionId(Integer.parseInt(id));
        menuOptionsPackage.setPackageNum(packageNum);
        menuOptionsPackage.setPackageName(packageName);
        menuOptionsPackage.setPackageDetails(packageDetails);
        menuOptionsPackage.setImage1(bytes1);
        menuOptionsPackage.setImage2(bytes2);
        menuOptionsPackage.setImage3(bytes3);
        menuOptionsPackage.setImage4(bytes4);
        menuOptionsPackage.setPrice(price);
        menuOptionsPackage.setAvailableSheets(availableSheets);

        service.updatePackage(menuOptionsPackage);

        return false;
    }
    @GetMapping("/search-by-id-menuoptions/{id}")
    public MenuOptionsPackage searchById(@PathVariable Integer id){
        return service.searchById(id);
    }
    @DeleteMapping("/delete-by-id-menuoptions/{id}")
    public void deleteById(@PathVariable Integer id){
        service.deleteById(id);
    }
}
