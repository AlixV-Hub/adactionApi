package al.adaction.demo.service.wastetypes;

import al.adaction.demo.entity.WasteTypesEntity;
import al.adaction.demo.repository.WasteTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WasteTypesServiceImpl implements IWasteTypesService {

    private final WasteTypeRepository wasteTypeRepository;

    @Autowired
    public WasteTypesServiceImpl(WasteTypeRepository wasteTypeRepository) {
        this.wasteTypeRepository = wasteTypeRepository;
    }

    @Override
    public List<WasteTypesEntity> getAllWasteTypes() {
        return wasteTypeRepository.findAll();
    }

    @Override
    public Optional<WasteTypesEntity> getWasteTypeById(Long id) {
        return wasteTypeRepository.findById(id);
    }

    @Override
    public WasteTypesEntity createWasteType(WasteTypesEntity wasteType) {
        return wasteTypeRepository.save(wasteType);
    }

    @Override
    public WasteTypesEntity updateWasteType(Long id, WasteTypesEntity updatedWasteType) {
        return wasteTypeRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedWasteType.getName());
                    existing.setDescription(updatedWasteType.getDescription());
                    return wasteTypeRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Type de déchet non trouvé avec l'id : " + id));
    }

    @Override
    public void deleteWasteType(Long id) {
        wasteTypeRepository.deleteById(id);
    }
}
