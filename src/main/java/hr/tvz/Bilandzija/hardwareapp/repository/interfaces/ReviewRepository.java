package hr.tvz.Bilandzija.hardwareapp.repository.interfaces;

import hr.tvz.Bilandzija.hardwareapp.model.pojo.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>{
   List<Review> findAllByHardwareCode(Integer hardwareCode);
}
