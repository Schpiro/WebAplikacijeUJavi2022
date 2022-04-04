package hr.tvz.Bilandzija.hardwareapp.model.pojo;

import hr.tvz.Bilandzija.hardwareapp.model.enums.TypeOfHardware;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Hardware {
    private String name;
    private Integer code;
    private Integer price;
    private TypeOfHardware type;
    private Integer availableStock;
}
