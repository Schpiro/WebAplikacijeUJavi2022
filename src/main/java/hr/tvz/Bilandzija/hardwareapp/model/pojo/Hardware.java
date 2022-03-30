package hr.tvz.Bilandzija.hardwareapp.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Bean;

@Data
@AllArgsConstructor
public class Hardware {
    /*Napisati klasu Hardware s fieldovima naziv, šifra, cijena, tip (CPU, GPU, MBO, RAM,
    STORAGE ili OTHER) i broj komada na stanju. Nazive fieldova pisati na engleskom.*/

    private String name;
    private Integer code;
    private Integer price;
    private TypeOfHardware type;
    private Integer availableStock;

    public enum TypeOfHardware{
        CPU,GPU,MBO,RAM,STORAGE,OTHER
    }
}
