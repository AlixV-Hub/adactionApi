package Al.Adaction.demo.controlleur;

import Al.Adaction.demo.entity.VolonteerEntity;
import Al.Adaction.demo.repository.VolonteerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    // création d'une methode qui retourne une liste ajout d'ubn response entity pour
    // encapsuler les résultats et retourner un code http
    public ResponseEntity<List<VolonteerEntity>> getAllVolonteers(){
        return new ResponseEntity<>(volonteerRepository.findAll(), HttpStatus.OK);
    }
}
