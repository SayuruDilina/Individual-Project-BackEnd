package edu.icet.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.dto.AccommodationPackage;
import edu.icet.entity.AccommodationPackageEntity;
import edu.icet.repository.AccommodationRepository;
import edu.icet.service.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccommodationServiceImpl implements AccommodationService {

    private final  AccommodationRepository repository;
    private final ObjectMapper mapper;

    @Override
    public boolean postPackage(AccommodationPackage accommodationPackage) {
      repository.save( mapper.convertValue(accommodationPackage, AccommodationPackageEntity.class));
        return false;
    }

    @Override
    public  List<AccommodationPackage> getPackageDetails() {
List<AccommodationPackage>  packageList=new ArrayList<>();
List<AccommodationPackageEntity>entities=repository.findAll();
entities.forEach(accommodationPackageEntity -> {
    packageList.add(mapper.convertValue(accommodationPackageEntity,AccommodationPackage.class));
});
        return  packageList;
    }

    @Override
    public void updatePackage(AccommodationPackage accommodationPackage) {
        repository.save( mapper.convertValue(accommodationPackage, AccommodationPackageEntity.class));
    }

    @Override
    public AccommodationPackage searchById(Integer id) {

        return  mapper.convertValue(repository.findById(id),AccommodationPackage.class);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

}
