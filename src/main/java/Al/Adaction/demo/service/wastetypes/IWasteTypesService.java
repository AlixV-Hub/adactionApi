package al.adaction.demo.service.wastetypes;

import al.adaction.demo.entity.WasteTypesEntity;
import java.util.List;
import java.util.Optional;

public interface IWasteTypesService {

    List<WasteTypesEntity> getAllWasteTypes();

    Optional<WasteTypesEntity> getWasteTypeById(Long id);

    WasteTypesEntity createWasteType(WasteTypesEntity wasteType);

    WasteTypesEntity updateWasteType(Long id, WasteTypesEntity wasteType);

    void deleteWasteType(Long id);
}
