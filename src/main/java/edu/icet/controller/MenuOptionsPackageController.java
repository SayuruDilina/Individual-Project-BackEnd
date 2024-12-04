package edu.icet.controller;


import edu.icet.dto.MenuOptionsPackage;
import edu.icet.service.MenuOptionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/menu-option")
@RequiredArgsConstructor
public class MenuOptionsPackageController {

  private  final MenuOptionsService service;

    @PostMapping("/add-menu-option-package")
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
        MenuOptionsPackage menuOptionsPackage=new MenuOptionsPackage();
        menuOptionsPackage.setMenuOptionId(Integer.parseInt(id));
        menuOptionsPackage.setPackageNum(packageNum);
        menuOptionsPackage.setPackageName(packageName);
        menuOptionsPackage.setPackageDetails(packageDetails);
        menuOptionsPackage.setImage(bytes);
        menuOptionsPackage.setPrice(price);
        menuOptionsPackage.setAvailableSheets(availableSheets);
        service.postPackage(menuOptionsPackage);
        return false;
    }
    @GetMapping("/get-all-menu-option-packages")
    public List<MenuOptionsPackage> getPackageDetails(){

        return service.getPackageDetails();
    }
    @PutMapping("/update-menu-options-package")
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
        MenuOptionsPackage menuOptionsPackage=new MenuOptionsPackage();
        menuOptionsPackage.setMenuOptionId(Integer.parseInt(id));
        menuOptionsPackage.setPackageNum(packageNum);
        menuOptionsPackage.setPackageName(packageName);
        menuOptionsPackage.setPackageDetails(packageDetails);
        menuOptionsPackage.setImage(bytes);
         menuOptionsPackage.setPrice(price);
        menuOptionsPackage.setAvailableSheets(availableSheets);

        service.updatePackage(menuOptionsPackage);

        return false;
    }
    @GetMapping("/search-by-id/{id}")
    public MenuOptionsPackage searchById(@PathVariable Integer id){
        return service.searchById(id);
    }
    @DeleteMapping("/delete-by-id/{id}")
    public void deleteById(@PathVariable Integer id){
        service.deleteById(id);
    }
}
