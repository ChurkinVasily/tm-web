package ru.churkin.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.churkin.api.IUserService;
import ru.churkin.entity.User;
import ru.churkin.enums.Role;
import ru.churkin.repository.UserRepositoryJPA;

import java.util.List;
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

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashPassword = passwordEncoder.encode(pass);

        User user = new User(name, hashPassword);
        boolean isConsist = false;
        for (User cUser : userRepository.findAll()) {
            if (!isConsist && name.equals(cUser.getName())) {
                isConsist = true;
            }
        }
        if (isConsist) {
            return false;
        }
        user.setRole(Role.USER);
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

    @Override
    public List<User> getUserAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean updateUser(@Nullable User user) {
        if (user == null) return false;
        userRepository.save(user);
        return true;
    }

//
//    @Override
//    public boolean removeUserById(@Nullable String id) {
//        if (id == null || id.isEmpty()) return false;
//        Optional<User> optionalUser = userRepository.findById(id);
//        User user = optionalUser.isPresent() ? optionalUser.get() : null;
//        if (user == null) return false;
//        userRepository.delete(user);
//        return true;
//    }
}
