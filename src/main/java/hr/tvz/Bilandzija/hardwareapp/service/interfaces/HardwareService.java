package hr.tvz.Bilandzija.hardwareapp.service.interfaces;

import hr.tvz.Bilandzija.hardwareapp.model.dto.HardwareDTO;

import java.util.List;

public interface HardwareService {
    List<HardwareDTO> findAll();

    HardwareDTO findByCode(Integer code);
}
