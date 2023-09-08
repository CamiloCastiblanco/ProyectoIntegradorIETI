package co.escuelaing.edu.ieti.service;

import co.escuelaing.edu.ieti.repository.User;
import co.escuelaing.edu.ieti.repository.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService
{
    User save(User user);

    Optional<User> findById(String id);

    List<User> all();

    void deleteById(String id);

    User update(User user, String id);

}