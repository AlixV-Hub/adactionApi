package al.adaction.demo.controller;

import al.adaction.demo.dto.City;
import al.adaction.demo.entity.CityEntity;
import al.adaction.demo.service.CityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }
}
