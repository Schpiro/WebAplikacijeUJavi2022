package hr.tvz.Bilandzija.hardwareapp.repository.interfaces;

import hr.tvz.Bilandzija.hardwareapp.model.pojo.Hardware;

import java.util.List;
import java.util.Optional;

public interface HardwareRepository {
    List<Hardware> findAll();

    Optional<Hardware> findByCode(Integer code);
}
