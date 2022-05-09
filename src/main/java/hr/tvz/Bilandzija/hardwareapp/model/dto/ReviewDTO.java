package hr.tvz.Bilandzija.hardwareapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewDTO {
    private String title;
    private String text;
    private Integer grade;

    @Override
    public String toString(){
        return "ReviewDTO{" +
                "title=" + title + "\'"+
                ", text=" + text + '\'' +
                ", grade=" + grade + "}";
    }
}
