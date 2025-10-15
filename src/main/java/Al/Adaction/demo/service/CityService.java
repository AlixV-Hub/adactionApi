// src/main/java/al/adaction/demo/service/CityService.java
package al.adaction.demo.service;

import al.adaction.demo.dto.City;
import al.adaction.demo.entity.CityEntity;
import al.adaction.demo.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getAllCities() {
        return cityRepository.findAll()
                .stream()
                .map(city -> new City(city.getId(), city.getName()))
                .collect(Collectors.toList());
    }
}
