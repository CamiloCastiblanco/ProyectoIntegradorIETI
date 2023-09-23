package co.escuelaing.edu.ieti.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.security.crypto.bcrypt.BCrypt;
public class User {
    private String id;
    private String name;

    private String email;
    private String lastName;
    private String createdAt;
    private String password;


    public User(UserDTO dto) {
        this(dto.getName(), dto.getEmail(), dto.getLastName(), dto.getPassword());
    }

    public User() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = java.time.LocalDate.now().toString();
    }

    public User(String name, String email, String lastName, String password) {
        this();
        this.name = name;
        this.email = email;
        this.lastName = lastName;
        hashPassword(password);


    }

    public User(String id, String name, String email, String lastName,String password, String createdAt) {
        this(name, email, lastName, password);
        this.id = id;
        this.createdAt = createdAt;
    }

    public void update(UserDTO userDto) {
        name = userDto.getName();
        lastName = userDto.getLastName();
        email = userDto.getEmail();
        hashPassword(userDto.getPassword());
    }

    private void hashPassword(String password){
        if (password != null) {
            this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        }
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        hashPassword(password);
    }

}