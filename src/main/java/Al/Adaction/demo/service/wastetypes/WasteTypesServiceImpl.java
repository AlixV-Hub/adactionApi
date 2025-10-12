package al.adaction.demo.service.wastetypes;

import al.adaction.demo.entity.WasteTypesEntity;
import al.adaction.demo.repository.WasteTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service  // <- Très important, c’est ce qui dit à Spring que c’est un bean
public class WasteTypesServiceImpl implements IWasteTypesService {

    private final WasteTypesRepository wasteTypesRepository;

    @Autowired
    public WasteTypesServiceImpl(WasteTypesRepository wasteTypesRepository) {
        this.wasteTypesRepository = wasteTypesRepository;
    }

    @Override
    public List<WasteTypesEntity> getAllWasteTypes() {
        return wasteTypesRepository.findAll();
    }

    @Override
    public Optional<WasteTypesEntity> getWasteTypeById(Long id) {
        return wasteTypesRepository.findById(id);
    }

    @Override
    public WasteTypesEntity createWasteType(WasteTypesEntity wasteType) {
        return wasteTypesRepository.save(wasteType);
    }

    @Override
    public WasteTypesEntity updateWasteType(Long id, WasteTypesEntity updatedWasteType) {
        return wasteTypesRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedWasteType.getName());
                    existing.setDescription(updatedWasteType.getDescription());
                    return wasteTypesRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Type de déchet non trouvé avec l'id : " + id));
    }

    @Override
    public void deleteWasteType(Long id) {
        wasteTypesRepository.deleteById(id);
    }
}
