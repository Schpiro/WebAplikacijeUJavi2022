package hr.tvz.Bilandzija.hardwareapp.service;

import hr.tvz.Bilandzija.hardwareapp.command.HardwareCommand;
import hr.tvz.Bilandzija.hardwareapp.model.pojo.Hardware;
import hr.tvz.Bilandzija.hardwareapp.model.dto.HardwareDTO;
import hr.tvz.Bilandzija.hardwareapp.repository.interfaces.HardwareRepository;
import hr.tvz.Bilandzija.hardwareapp.service.interfaces.HardwareService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    @Override
    public Optional<HardwareDTO> save(final HardwareCommand hardwareCommand) {
        return hardwareRepository.save(mapCommandToHardware(hardwareCommand)).map(this::mapHardwareToDTO);
    }

    @Override
    public Optional<HardwareDTO> update(final Integer code, final HardwareCommand hardwareCommand) {
        return hardwareRepository.update(code, mapCommandToHardware(hardwareCommand)).map(this::mapHardwareToDTO);
    }

    @Override
    public void deleteByCode(Integer code) {
        hardwareRepository.delete(code);
    }

    private HardwareDTO mapHardwareToDTO(final Hardware hardware){
        return new HardwareDTO(hardware.getCode(), hardware.getName(),hardware.getPrice());
    }

    private Hardware mapCommandToHardware(final HardwareCommand hardwareCommand) {
        return new Hardware(hardwareCommand.getName(),hardwareCommand.getCode(),hardwareCommand.getPrice(),hardwareCommand.getType(),hardwareCommand.getStock());
    }
}
