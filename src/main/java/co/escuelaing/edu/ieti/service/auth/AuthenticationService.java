package co.escuelaing.edu.ieti.service.auth;
import lombok.RequiredArgsConstructor;
import co.escuelaing.edu.ieti.config.JwtService;
import co.escuelaing.edu.ieti.repository.AuthenticationRequest;
import co.escuelaing.edu.ieti.repository.AuthenticationResponse;
import co.escuelaing.edu.ieti.repository.RegisterRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import co.escuelaing.edu.ieti.repository.UserMongoRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserMongoRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = co.escuelaing.edu.ieti.repository.User.builder()
                .name(request.getName())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))                
                .build();

        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = repository.findUserByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}