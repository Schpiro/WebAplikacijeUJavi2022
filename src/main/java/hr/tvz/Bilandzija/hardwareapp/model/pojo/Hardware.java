package hr.tvz.Bilandzija.hardwareapp.model.pojo;

import hr.tvz.Bilandzija.hardwareapp.model.enums.TypeOfHardware;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Hardware {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer code;
    private Integer price;
    private TypeOfHardware type;
    private Integer availableStock;

    @OneToMany(mappedBy = "hardware")
    private List<Review> reviews;
    public Hardware(String name, Integer code, Integer price, TypeOfHardware type, Integer availableStock) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.type = type;
        this.availableStock = availableStock;
    }

    public Hardware(Integer id, String name, Integer code, Integer price, TypeOfHardware type, Integer availableStock) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.price = price;
        this.type = type;
        this.availableStock = availableStock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hardware hardware = (Hardware) o;
        return code.equals(hardware.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
