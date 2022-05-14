package hr.tvz.Bilandzija.hardwareapp.repository.interfaces;

import hr.tvz.Bilandzija.hardwareapp.model.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
