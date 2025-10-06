package al.adaction.demo.service;

import al.adaction.demo.entity.VolonteerEntity;
import java.util.List;

public interface IVolonteerService {
    boolean deleteVolonteer(Long id);
    VolonteerEntity getVolonteerById(Long id);
    List<VolonteerEntity> getAllVolonteers();
    VolonteerEntity createVolonteer(VolonteerEntity volonteer);
    VolonteerEntity updateVolonteer(Long id, VolonteerEntity volonteer);
}
