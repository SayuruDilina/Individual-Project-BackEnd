package edu.icet.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.dto.MenuOptionsPackage;
import edu.icet.entity.MenuOptionsPackageEntity;
import edu.icet.repository.MenuOptionsRepository;
import edu.icet.service.MenuOptionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuOptionsServiceImpl implements MenuOptionsService {

    private final  MenuOptionsRepository repository;
    private  final ObjectMapper mapper;
    @Override
    public boolean postPackage(MenuOptionsPackage menuOptionsPackage) {
        repository.save(mapper.convertValue(menuOptionsPackage, MenuOptionsPackageEntity.class));
        return false;
    }

    @Override
    public List<MenuOptionsPackage> getPackageDetails() {
        List<MenuOptionsPackage>packageList=new ArrayList<>();
        List<MenuOptionsPackageEntity>entities=repository.findAll();
        entities.forEach(menuOptionsPackageEntity -> {
            packageList.add(mapper.convertValue(menuOptionsPackageEntity,MenuOptionsPackage.class));
        });
        return packageList;
    }

    @Override
    public MenuOptionsPackage searchById(Integer id) {
        return mapper.convertValue(repository.findById(id), MenuOptionsPackage.class);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void updatePackage(MenuOptionsPackage menuOptionsPackage) {
        repository.save(mapper.convertValue(menuOptionsPackage, MenuOptionsPackageEntity.class));
    }
}
