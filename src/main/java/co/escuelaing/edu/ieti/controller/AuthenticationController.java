package co.escuelaing.edu.ieti.controller;
import lombok.RequiredArgsConstructor;
import co.escuelaing.edu.ieti.service.auth.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.escuelaing.edu.ieti.repository.AuthenticationRequest;
import co.escuelaing.edu.ieti.repository.AuthenticationResponse;
import co.escuelaing.edu.ieti.repository.RegisterRequest;

@RestController
@RequestMapping("v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return new ResponseEntity<>(service.register(request), HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return new ResponseEntity<>(service.authenticate(request), HttpStatus.OK);
    }
}