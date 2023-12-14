package co.escuelaing.edu.ieti.service.user.impl;

import co.escuelaing.edu.ieti.repository.User;
import co.escuelaing.edu.ieti.repository.UserMongoRepository;
import co.escuelaing.edu.ieti.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceMongoDb implements UserService {

    private final UserMongoRepository userMongoRepository;

    @Autowired
    public UserServiceMongoDb(UserMongoRepository userMongoRepository) {
        this.userMongoRepository = userMongoRepository;
    }

    @Override
    public User save(User user) {
        return userMongoRepository.save(user);
    }

    @Override
    public Optional<User> findById(String id) {
        Optional<User> user = userMongoRepository.findById(id);
        if (user.isPresent()) return Optional.of(user.get());
        return null;

    }

    @Override
    public List<User> all() {
        return userMongoRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        userMongoRepository.deleteById(id);
    }

    @Override
    public User update(User user, String userId) {
        if(userMongoRepository.existsById(userId)){
            User actualUser = userMongoRepository.findById(userId).get();
            actualUser.setCreatedAt(user.getCreatedAt());
            actualUser.setEmail(user.getEmail());
            actualUser.setLastName(user.getLastName());
            actualUser.setName(user.getName());
            userMongoRepository.save(actualUser);
            return actualUser;
        }
        return null;
    }
}