package hr.tvz.Bilandzija.hardwareapp.repository.interfaces;

import hr.tvz.Bilandzija.hardwareapp.model.pojo.Hardware;

import java.util.Optional;
import java.util.Set;

public interface HardwareRepository {
    Set<Hardware> findAll();

    Optional<Hardware> findByCode(Integer code);

    void delete(Integer code);

    Optional<Hardware> save(Hardware hardware);

    Optional<Hardware> update(Integer code, Hardware hardware);
}
