package ru.churkin.repository;

import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;
import ru.churkin.api.IUserRepository;
import ru.churkin.entity.User;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository implements IUserRepository {

    private List<User> userList = new ArrayList<>();

    @PostConstruct
    public void init() {
        User user1 = new User("user1", "pass1");
        user1.setId("u1");
        User user2 = new User("user2", "pass2");
        user2.setId("u2");
        User user3 = new User("user3", "pass3");
        user3.setId("u3");

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
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

    @Override
    public boolean validate(final @Nullable String userName, final @Nullable String userPass) {
        if (userName == null || userName.isEmpty() || userPass == null || userPass.isEmpty()) return false;
        User userInBase = this.findUserByName(userName);
        if (userInBase == null) return false;
        if (userInBase.getPassword().equals(userPass)) return true;
        return false;
    }
}
