package al.adaction.demo.controlleur;

import al.adaction.demo.entity.VolonteerEntity;
import al.adaction.demo.repository.VolonteerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/volonteers/put")
public class VolonteerPutControlleur {
   // @Autowired // injection du repository au controller
    // final est une meilleure methode car le repo ne va pas changer mais force à créer un constructeur
    final VolonteerRepository volonteerRepository;

    public VolonteerPutControlleur(VolonteerRepository volonteerRepository) {
        this.volonteerRepository = volonteerRepository;
    }

    @PutMapping("/{id}")
    public ResponseEntity<VolonteerEntity> updateVolonteer(@PathVariable Long id, @RequestBody VolonteerEntity volonteerDetails){
        Optional<VolonteerEntity> volonteer = volonteerRepository.findById(id);
        if(volonteer.isPresent()){
            VolonteerEntity existingVolonteer = volonteer.get();

            existingVolonteer.setFirstname(volonteerDetails.getFirstname());
            existingVolonteer.setLastname(volonteerDetails.getLastname());
            existingVolonteer.setLocation(volonteerDetails.getLocation());
            existingVolonteer.setEmail(volonteerDetails.getEmail());
            existingVolonteer.setPassword(volonteerDetails.getPassword());

            VolonteerEntity updateVolonteer = volonteerRepository.save(existingVolonteer);
            return new ResponseEntity<>(updateVolonteer, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }



}
