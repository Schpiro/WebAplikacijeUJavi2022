package hr.tvz.Bilandzija.hardwareapp.service.interfaces;

import hr.tvz.Bilandzija.hardwareapp.controller.HardwareCommand;
import hr.tvz.Bilandzija.hardwareapp.model.dto.HardwareDTO;

import java.util.List;
import java.util.Optional;

public interface HardwareService {
    List<HardwareDTO> findAll();

    HardwareDTO findByCode(Integer code);

    Optional<HardwareDTO> save(HardwareCommand command);

    Optional<HardwareDTO> update(Integer code, HardwareCommand command);

    void deleteByCode(Integer code);
}
