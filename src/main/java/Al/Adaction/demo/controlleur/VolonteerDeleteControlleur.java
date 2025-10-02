package al.adaction.demo.controlleur;

import al.adaction.demo.entity.VolonteerEntity;
import al.adaction.demo.repository.VolonteerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/volonteers/delete")
public class VolonteerDeleteControlleur {
   // @Autowired // injection du repository au controller
    // final est une meilleure methode car le repo ne va pas changer mais force à créer un constructeur
    final VolonteerRepository volonteerRepository;

    public VolonteerDeleteControlleur(VolonteerRepository volonteerRepository) {
        this.volonteerRepository = volonteerRepository;
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteVolonteer(@PathVariable Long id){
        Optional<VolonteerEntity> volonteer = volonteerRepository.findById(id);
        if(volonteer.isPresent()){
            volonteerRepository.delete(volonteer.get());
            return new ResponseEntity<>(HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }



}
