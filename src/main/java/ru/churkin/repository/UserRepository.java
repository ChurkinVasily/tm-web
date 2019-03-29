package ru.churkin.repository;

import org.jetbrains.annotations.Nullable;
import ru.churkin.api.IUserRepository;
import ru.churkin.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {

    private static List<User> userList = new ArrayList<>();

    static {
        User user1 = new User("user1", "pass1");
        User user2 = new User("user2", "pass2");

        userList.add(user1);
        userList.add(user2);
    }

    @Override
    public void createUser(User user) {
        userList.add(user);
    }

    @Override
    public User findUserByName(final @Nullable String name) {
        if (name == null || name.isEmpty()) return null;
        User user = null;
        for (User iuser : userList) {
            if (name.equals(iuser.getName())) {
                user = iuser;
                break;
            }
        }
        return user;
    }

    @Override
    public User findUserById(final @Nullable String id) {
        if (id == null || id.isEmpty()) return null;
        User user = null;
        for (User iuser : userList) {
            if (id.equals(iuser.getId())) {
                user = iuser;
                break;
            }
        }
        return user;
    }

    @Override
    public void deleteUser(String id) {
        if (id.isEmpty()) return;
        for (User user : userList) {
            if (id.equals(user.getId())) {
                userList.remove(user);
                return;
            }
        }
    }

    @Override
    public List<User> getUserList() {
        return userList;
    }
}
