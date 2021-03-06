package hr.tvz.Bilandzija.hardwareapp.controller;

import hr.tvz.Bilandzija.hardwareapp.command.HardwareCommand;
import hr.tvz.Bilandzija.hardwareapp.model.dto.HardwareDTO;
import hr.tvz.Bilandzija.hardwareapp.service.interfaces.HardwareService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("hardware")
@CrossOrigin(origins = "http://localhost:4200")
public class HardwareController {
    private final HardwareService hardwareService;

    public HardwareController(HardwareService hardwareService) {
        this.hardwareService = hardwareService;
    }

    @GetMapping
    public List<HardwareDTO> getAllHardware(){
        return hardwareService.findAll();
    }

    @GetMapping("/{code}")
    public HardwareDTO getHardwareByCode(@PathVariable final Integer code){
        return hardwareService.findByCode(code);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<HardwareDTO> save(@Valid @RequestBody final HardwareCommand command){
        return hardwareService.save(command)
                .map(
                        hardwareDTO -> ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(hardwareDTO)
                )
                .orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .build()
                );
    }

    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{code}")
    public void delete(@PathVariable Integer code){
        hardwareService.deleteByCode(code);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{code}")
    public ResponseEntity<HardwareDTO> update(@PathVariable Integer code, @Valid @RequestBody final HardwareCommand updateHardwareCommand){
        return hardwareService.update(code, updateHardwareCommand)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }
}
