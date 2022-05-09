package hr.tvz.Bilandzija.hardwareapp.service.interfaces;

import hr.tvz.Bilandzija.hardwareapp.model.dto.ReviewDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {
    List<ReviewDTO> findAll();

    List<ReviewDTO> findByHardwareCode(Integer code);
}
