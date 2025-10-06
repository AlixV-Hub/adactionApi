package al.adaction.demo.service;

import al.adaction.demo.entity.VolonteerEntity;
import al.adaction.demo.repository.VolonteerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VolonteerServiceImpl implements IVolonteerService {

    private final VolonteerRepository volonteerRepository;

    public VolonteerServiceImpl(VolonteerRepository volonteerRepository) {
        this.volonteerRepository = volonteerRepository;
    }

    @Override
    public boolean deleteVolonteer(Long id) {
        Optional<VolonteerEntity> volonteer = volonteerRepository.findById(id);
        if(volonteer.isPresent()) {
            volonteerRepository.delete(volonteer.get());
            return true;
        }
        return false;
    }

    @Override
    public VolonteerEntity getVolonteerById(Long id) {
        return volonteerRepository.findById(id).orElse(null);
    }

    @Override
    public List<VolonteerEntity> getAllVolonteers() {
        return volonteerRepository.findAll();
    }

    @Override
    public VolonteerEntity createVolonteer(VolonteerEntity volonteer) {
        return volonteerRepository.save(volonteer);
    }

    @Override
    public VolonteerEntity updateVolonteer(Long id, VolonteerEntity volonteer) {
        return volonteerRepository.findById(id).map(existing -> {
            existing.setFirstname(volonteer.getFirstname());
            existing.setLastname(volonteer.getLastname());
            existing.setEmail(volonteer.getEmail());
            existing.setLocation(volonteer.getLocation());
            return volonteerRepository.save(existing);
        }).orElse(null);
    }
}
