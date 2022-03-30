package hr.tvz.Bilandzija.hardwareapp.service;

import hr.tvz.Bilandzija.hardwareapp.model.pojo.Hardware;
import hr.tvz.Bilandzija.hardwareapp.model.dto.HardwareDTO;
import hr.tvz.Bilandzija.hardwareapp.repository.interfaces.HardwareRepository;
import hr.tvz.Bilandzija.hardwareapp.service.interfaces.HardwareService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HardwareServiceImpl implements HardwareService {
    private final HardwareRepository hardwareRepository;

    public HardwareServiceImpl(HardwareRepository hardwareRepository) {
        this.hardwareRepository = hardwareRepository;
    }

    @Override
    public List<HardwareDTO> findAll() {
        return hardwareRepository.findAll().stream().map(this::mapHardwareToDTO).collect(Collectors.toList());
    }

    @Override
    public HardwareDTO findByCode(final Integer code) {
        return hardwareRepository.findByCode(code).map(this::mapHardwareToDTO).orElse(null);
    }

    private HardwareDTO mapHardwareToDTO(final Hardware hardware){
        return new HardwareDTO(hardware.getName(),hardware.getPrice());
    }
}
