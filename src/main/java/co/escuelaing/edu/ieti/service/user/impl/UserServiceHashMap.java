package co.escuelaing.edu.ieti.service.user.impl;
import co.escuelaing.edu.ieti.repository.User;
import co.escuelaing.edu.ieti.repository.UserDTO;
import co.escuelaing.edu.ieti.service.user.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceHashMap implements UserService {

    private final HashMap<String, User> usersMap = new HashMap<>();

    @Override
    public User save(User user) {
        if(findById(user.getId()).isEmpty()){
            user.setId(String.valueOf(usersMap.size()));
        }
        usersMap.put(user.getId(), user);
        return usersMap.get(user.getId());
    }

    @Override
    public User login(String email, String password) {

        return null; // Devuelve null si la autenticación falla
    }

    private boolean passwordMatches(String rawPassword, String encodedPassword) {
        return rawPassword.equals(encodedPassword);
    }

    @Override
    public Optional<User> findById(String id) {
        if (usersMap.containsKey(id)) {
            return Optional.ofNullable(usersMap.get(id));
        }
        return null;
    }

    @Override
    public List<User> all() {
        return new ArrayList<>(usersMap.values());
    }

    @Override
    public void deleteById(String id) {
        usersMap.remove(id);
    }



    @Override
    public User update(User user, String userId) {
        User userToUpdate = usersMap.get(userId);
        userToUpdate.update(new UserDTO(user.getName(), user.getLastName(), user.getEmail()));
        return userToUpdate;
    }




}
