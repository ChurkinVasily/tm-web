package ru.churkin.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.churkin.api.IUserService;
import ru.churkin.entity.User;
import ru.churkin.repository.UserRepositoryJPA;

import java.util.Optional;

@Service
@Transactional
@NoArgsConstructor
public class UserService implements IUserService {

    @Autowired
    UserRepositoryJPA userRepository;

    @Override
    public boolean createNewUser(String name, String pass) {
        if (name == null || name.isEmpty() || pass == null || pass.isEmpty()) return false;
        User user = new User(name, pass);
        boolean isConsist = false;
        for (User cUser : userRepository.findAll()) {
            if (!isConsist && name.equals(cUser.getName())) {
                isConsist = true;
            }
        }
        if (isConsist) {
            return false;
        }
        userRepository.save(user);
        return true;
    }

    @Override
    public User findUserByName(String userName) {
        if (userName == null || userName.isEmpty()) return null;
        User user = userRepository.findUserByName(userName);
        return user;
    }

    @Override
    public User findUserById(String id) {
        if (id == null || id.isEmpty()) return null;
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.isPresent() ? optionalUser.get() : null;
        return user;
    }

    @Override
    public boolean validate(String name, String pass) {
        if (name == null || name.isEmpty() || pass == null || pass.isEmpty()) return false;
        User userInBase = userRepository.findUserByName(name);
        if (userInBase == null) return false;
        if (userInBase.getPassword().equals(pass)) return true;
        return false;
    }
}
