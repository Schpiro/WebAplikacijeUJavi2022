package hr.tvz.Bilandzija.hardwareapp.service.interfaces;

import hr.tvz.Bilandzija.hardwareapp.model.pojo.User;

public interface JwtService {

    boolean authenticate(String token);

    String createJwt(User user);

}
