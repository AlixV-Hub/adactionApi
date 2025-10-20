package al.adaction.demo.service;

import al.adaction.demo.dto.CityRequest;
import al.adaction.demo.entity.CityEntity;
import al.adaction.demo.repository.CityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<CityRequest> getAllCities() {
        List<CityEntity> cities = cityRepository.findAll();

        //  CityEntity en CityRequest (DTO)
        return cities.stream()
                .map(city -> new CityRequest(city.getId(), city.getName()))
                .collect(Collectors.toList());
    }
}