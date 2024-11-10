package edu.icet.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.dto.DayOutPackage;
import edu.icet.entity.DayOutPackageEntity;
import edu.icet.repository.DayOutRepository;
import edu.icet.service.DayOutPackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DayOutPackageServiceImpl implements DayOutPackageService {

    private final DayOutRepository repository;
    private final ObjectMapper mapper;

    @Override
    public void postPackage(DayOutPackage dayOutPackage) {
        repository.save(mapper.convertValue(dayOutPackage, DayOutPackageEntity.class));
    }

    @Override
    public List<DayOutPackage> getPackageDetails() {
        List<DayOutPackage> packageList=new ArrayList<>();
        List<DayOutPackageEntity> entities = repository.findAll();
        entities.forEach(dayOutPackageEntity -> {
            packageList.add(mapper.convertValue(dayOutPackageEntity, DayOutPackage.class));
        });
        return packageList ;
    }

    @Override
    public DayOutPackage searchById(Integer id) {
        return mapper.convertValue( repository.findById(id), DayOutPackage.class);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void updatePackage(DayOutPackage dayOutPackage) {
        repository.save(mapper.convertValue(dayOutPackage, DayOutPackageEntity.class));
    }
}
