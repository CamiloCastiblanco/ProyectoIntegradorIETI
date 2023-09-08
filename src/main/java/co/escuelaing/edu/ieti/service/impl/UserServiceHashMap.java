package co.escuelaing.edu.ieti.service.impl;
import co.escuelaing.edu.ieti.repository.User;
import co.escuelaing.edu.ieti.repository.UserDTO;
import co.escuelaing.edu.ieti.service.UserService;
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
        usersMap.put(user.getId(), user);
        return user;
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
    public User update(UserDTO userDto, String id) {
        if (usersMap.containsKey(id)) {
            User user = usersMap.get(id);
            user.update(userDto);
            return user;
        } else {
            return null;
        }
    }




}
