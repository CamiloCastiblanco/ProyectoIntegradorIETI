package co.escuelaing.edu.ieti.repository;


import java.util.UUID;

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
        setPassword(password);


    }

    public void update(UserDTO userDto) {
        name = userDto.getName();
        lastName = userDto.getLastName();
        email = userDto.getEmail();
        password = userDto.getPassword();
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
        this.password = password;
    }

}