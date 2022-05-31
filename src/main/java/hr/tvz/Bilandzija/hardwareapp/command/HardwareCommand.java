package hr.tvz.Bilandzija.hardwareapp.command;
import hr.tvz.Bilandzija.hardwareapp.model.enums.TypeOfHardware;
import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
public class HardwareCommand {
    @NotBlank(message = "Name must not be empty")
    private String name;

    @NotNull(message = "Code must not be empty")
    private Integer code;

    @NotNull(message = "Price must not be empty")
    private Integer price;

    @NotNull(message = "Type must not be empty")
    private TypeOfHardware type;

    @NotNull(message = "Stock must not be empty")
    private Integer stock;
}
