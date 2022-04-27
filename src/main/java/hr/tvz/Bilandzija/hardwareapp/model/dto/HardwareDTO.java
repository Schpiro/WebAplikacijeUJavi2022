package hr.tvz.Bilandzija.hardwareapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HardwareDTO{
    /*Napisati klasu HardwareDTO s fieldovima naziv i cijena.*/
    private Integer code;
    private String name;
    private Integer price;

    @Override
    public String toString(){
        return "HardwareDTO{" +
                "code=" + code + "\'"+
                ", name=" + name + '\'' +
                ", price=" + price + "}";
    }
}
