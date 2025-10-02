package al.adaction.demo.controlleur;

import al.adaction.demo.entity.VolonteerEntity;
import al.adaction.demo.repository.VolonteerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/volonteers")
public class VolonteerControlleur {
   // @Autowired // injection du repository au controller
    // final est une meilleure methode car le repo ne va pas changer mais force à créer un constructeur
    final VolonteerRepository volonteerRepository;

    public VolonteerControlleur(VolonteerRepository volonteerRepository) {
        this.volonteerRepository = volonteerRepository;
    }
    // recupérer tous les volonteers pour l'appel /api/volonteers
    @GetMapping
    // création d'une methode qui retourne une liste ajout d'un response entity pour
    // encapsuler les résultats et retourner un code http
    public ResponseEntity<List<VolonteerEntity>> getAllVolonteers(){
        return new ResponseEntity<>(volonteerRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VolonteerEntity> getVolonteerById(@PathVariable Long id){
        Optional<VolonteerEntity> volonteer = volonteerRepository.findById(id);

        if(volonteer.isPresent()){
            return new ResponseEntity<>(volonteer.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<VolonteerEntity> createVolonteer(@RequestBody VolonteerEntity volonteer) {
        VolonteerEntity volonteerCreated = volonteerRepository.save(volonteer);
        // .save sert à la fois d'enregistrement et pour l'update
        return new ResponseEntity<>(volonteerCreated, HttpStatus.CREATED);
    }


}
