package co.escuelaing.edu.ieti.controller;

import co.escuelaing.edu.ieti.exception.UserNotFoundException;
import co.escuelaing.edu.ieti.repository.User;
import co.escuelaing.edu.ieti.repository.UserDTO;
import co.escuelaing.edu.ieti.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("user")
	public User login(@RequestParam("user") String username, @RequestParam("password") String pwd) {	
		
		User user = new User();
		user.setName(username);
		user.setPassword(pwd);		
		return user;
		
	}    

    

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        User userCreated = userService.save(new User(userDTO));
        URI createdUserUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();;
        return ResponseEntity.created(createdUserUri).body(userCreated);
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