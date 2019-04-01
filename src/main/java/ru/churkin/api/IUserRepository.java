package ru.churkin.api;

import org.jetbrains.annotations.Nullable;
import ru.churkin.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserRepository {

    void createUser(User user);

    User findUserByName(String name);

    User findUserById(String id);

    void deleteUser(String id);

    List<User> getUserList();

    boolean validate(String userName, String userPass);

}
