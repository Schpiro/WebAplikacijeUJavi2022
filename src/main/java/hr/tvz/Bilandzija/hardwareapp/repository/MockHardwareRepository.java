package hr.tvz.Bilandzija.hardwareapp.repository;

import hr.tvz.Bilandzija.hardwareapp.model.enums.TypeOfHardware;
import hr.tvz.Bilandzija.hardwareapp.model.pojo.Hardware;
import hr.tvz.Bilandzija.hardwareapp.repository.interfaces.HardwareRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MockHardwareRepository implements HardwareRepository {
    private final Set<Hardware> MOCKED_HARDWARE = new HashSet<>(Arrays.asList(
            new Hardware("Intel i7",1,100, TypeOfHardware.CPU,5),
            new Hardware("Intel i5",2,400, TypeOfHardware.CPU,4),
            new Hardware("Intel i3",3,600, TypeOfHardware.CPU,7),
            new Hardware("Ryzen 9",4,800, TypeOfHardware.CPU,15),
            new Hardware("RTX3080",5,1000, TypeOfHardware.GPU,2)
    ));


    @Override
    public Set<Hardware> findAll() {
        return MOCKED_HARDWARE;
    }

    @Override
    public Optional<Hardware> findByCode(Integer code) {
        return MOCKED_HARDWARE.stream().filter(x -> code.equals(x.getCode())).findAny();
    }

    @Override
    public void delete(Integer code) {
        MOCKED_HARDWARE.removeIf(x -> code.equals(x.getCode()));
    }

    @Override
    public Optional<Hardware> save(Hardware hardware) {
        if(MOCKED_HARDWARE.contains(hardware)) return Optional.empty();

        MOCKED_HARDWARE.add(hardware);
        return Optional.of(hardware);
    }

    @Override
    public Optional<Hardware> update(Integer code, Hardware hardware) {
        if(MOCKED_HARDWARE.removeIf( x -> x.getCode().equals(code) && hardware.getCode().equals(code)))
        {
            MOCKED_HARDWARE.add(hardware);
            return Optional.of(hardware);
        }
        else return Optional.empty();
    }
}
