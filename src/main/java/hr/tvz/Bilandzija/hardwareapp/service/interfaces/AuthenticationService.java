package hr.tvz.Bilandzija.hardwareapp.service.interfaces;

import hr.tvz.Bilandzija.hardwareapp.command.LoginCommand;
import hr.tvz.Bilandzija.hardwareapp.model.dto.LoginDTO;

import java.util.Optional;

public interface AuthenticationService {

    Optional<LoginDTO> login(LoginCommand command);

}
