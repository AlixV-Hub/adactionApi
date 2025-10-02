package al.adaction.demo.controlleur;

import al.adaction.demo.entity.VolonteerEntity;
import al.adaction.demo.repository.VolonteerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/volonteers/post")
public class VolonteerPostControlleur {
   // @Autowired // injection du repository au controller
    // final est une meilleure methode car le repo ne va pas changer mais force à créer un constructeur
    final VolonteerRepository volonteerRepository;

    public VolonteerPostControlleur(VolonteerRepository volonteerRepository) {
        this.volonteerRepository = volonteerRepository;
    }


    @PostMapping
    public ResponseEntity<VolonteerEntity> createVolonteer(@RequestBody VolonteerEntity volonteer) {
        VolonteerEntity volonteerCreated = volonteerRepository.save(volonteer);
        // .save sert à la fois d'enregistrement et pour l'update
        return new ResponseEntity<>(volonteerCreated, HttpStatus.CREATED);
    }


}
