package hr.tvz.Bilandzija.hardwareapp.service;

import hr.tvz.Bilandzija.hardwareapp.command.LoginCommand;
import hr.tvz.Bilandzija.hardwareapp.model.dto.LoginDTO;
import hr.tvz.Bilandzija.hardwareapp.model.pojo.User;
import hr.tvz.Bilandzija.hardwareapp.repository.interfaces.UserRepository;
import hr.tvz.Bilandzija.hardwareapp.service.interfaces.AuthenticationService;
import hr.tvz.Bilandzija.hardwareapp.service.interfaces.JwtService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthenticationServiceImpl(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<LoginDTO> login(LoginCommand command) {
        Optional<User> user = userRepository.findByUsername(command.getUsername());

        if (user.isEmpty() || !isMatchingPassword(command.getPassword(), user.get().getPassword())) {
            return Optional.empty();
        }

        return Optional.of(
                new LoginDTO(jwtService.createJwt(user.get()))
        );
    }

    private boolean isMatchingPassword(String rawPassword, String encryptedPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(rawPassword,encryptedPassword);
    }
}
