package hr.tvz.Bilandzija.hardwareapp.repository;

import hr.tvz.Bilandzija.hardwareapp.model.pojo.Hardware;
import hr.tvz.Bilandzija.hardwareapp.repository.interfaces.HardwareRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class MockHardwareRepository implements HardwareRepository {
    private final List<Hardware> MOCKED_HARDWARE = Arrays.asList(
            new Hardware("Intel i7",1,100, Hardware.TypeOfHardware.CPU,5),
            new Hardware("RTX3080",2,200, Hardware.TypeOfHardware.GPU,2)
    );


    @Override
    public List<Hardware> findAll() {
        return MOCKED_HARDWARE;
    }

    @Override
    public Optional<Hardware> findByCode(Integer code) {
        return MOCKED_HARDWARE.stream().filter(x -> code.equals(x.getCode())).findAny();
    }
}
