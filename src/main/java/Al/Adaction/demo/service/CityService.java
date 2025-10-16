package al.adaction.demo.service;

import al.adaction.demo.dto.City;
import al.adaction.demo.entity.CityEntity;
import al.adaction.demo.repository.CityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public CityEntity getCityById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ville introuvable avec l'ID: " + id));
    }

    @Transactional
    public CityEntity createCity(CityEntity city) {
        if (cityRepository.existsByName(city.getName())) {
            throw new RuntimeException("Une ville existe déjà avec ce nom");
        }
        return cityRepository.save(city);
    }

    public List<City> getAllCities() {
        return List.of();
    }
}