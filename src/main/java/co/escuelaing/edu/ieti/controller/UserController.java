package co.escuelaing.edu.ieti.controller;

import co.escuelaing.edu.ieti.exception.UserNotFoundException;
import co.escuelaing.edu.ieti.repository.LoginDTO;
import co.escuelaing.edu.ieti.repository.User;
import co.escuelaing.edu.ieti.repository.UserDTO;
import co.escuelaing.edu.ieti.security.JwtUtil;
import co.escuelaing.edu.ieti.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/user/")
public class UserController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private final UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        User userCreated = userService.save(new User(userDTO));
        URI createdUserUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();;
        return ResponseEntity.created(createdUserUri).body(userCreated);
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        User user = userService.login(loginDTO.getEmail(), loginDTO.getPassword());
        String token = "";
        if(user != null) {
            JwtUtil jtw = new JwtUtil();
            token = jtw.generateToken(user);
        }

        return ResponseEntity.ok().body(token);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.all();
        return ResponseEntity.ok(users);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> findById(@PathVariable("id") String id) {
        User userFound = userService.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return ResponseEntity.ok(userFound);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody UserDTO userDTO) {
        User userToUpdate = userService.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        User userUpdated = userService.update(new User(userDTO), id);
        userService.save(userToUpdate);
        return ResponseEntity.ok(userUpdated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        userService.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}