package co.escuelaing.edu.ieti.service.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    // Simula una base de datos de usuarios (en un entorno de producción, usa una base de datos real)
    private static final String DUMMY_USERNAME = "user";
    private static final String DUMMY_PASSWORD = "{noop}password"; // Usamos {noop} para indicar que la contraseña no está codificada
    private static final UserDetails DUMMY_USER = new User(DUMMY_USERNAME, DUMMY_PASSWORD, new ArrayList<>());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (DUMMY_USERNAME.equals(username)) {
            return DUMMY_USER;
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado con el nombre de usuario: " + username);
        }
    }
}