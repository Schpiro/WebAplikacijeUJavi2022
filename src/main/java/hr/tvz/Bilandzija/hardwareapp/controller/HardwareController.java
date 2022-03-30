package hr.tvz.Bilandzija.hardwareapp.controller;

import hr.tvz.Bilandzija.hardwareapp.model.dto.HardwareDTO;
import hr.tvz.Bilandzija.hardwareapp.service.interfaces.HardwareService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("hardware")
public class HardwareController {
    private final HardwareService hardwareService;

    public HardwareController(HardwareService hardwareService) {
        this.hardwareService = hardwareService;
    }

    @GetMapping
    public List<HardwareDTO> getAllHardware(){
        return hardwareService.findAll();
    }

    @GetMapping(params="code")
    public HardwareDTO getHardwareByCode(@RequestParam final Integer code){
        return hardwareService.findByCode(code);
    }
}
