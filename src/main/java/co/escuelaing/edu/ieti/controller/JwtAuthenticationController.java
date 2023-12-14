package co.escuelaing.edu.ieti.controller;
import co.escuelaing.edu.ieti.repository.JwtRequest;
import co.escuelaing.edu.ieti.repository.JwtResponse;
import co.escuelaing.edu.ieti.service.security.JwtUserDetailsService;
import co.escuelaing.edu.ieti.service.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        try {
            // Autentica al usuario utilizando el AuthenticationManager
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

            // Carga los detalles del usuario
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

            // Genera el token JWT
            final String token = jwtTokenUtil.generateToken(userDetails);

            // Devuelve el token en la respuesta
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (BadCredentialsException e) {
            // Maneja la excepción si las credenciales son incorrectas
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            // Utiliza el AuthenticationManager para autenticar al usuario
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );
        } catch (BadCredentialsException e) {
            // Lanza una excepción si las credenciales son incorrectas
            throw new BadCredentialsException("Credenciales incorrectas", e);
        }
    }
}