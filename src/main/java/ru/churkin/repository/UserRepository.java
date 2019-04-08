package ru.churkin.repository;

import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.churkin.api.IUserRepository;
import ru.churkin.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class UserRepository implements IUserRepository {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    EntityManager entityManager;

    private List<User> userList = new ArrayList<>();

//    @PostConstruct
//    public void init() {
//        User user1 = new User("user1", "pass1");
//        user1.setId("u1");
//        User user2 = new User("user2", "pass2");
//        user2.setId("u2");
//        User user3 = new User("user3", "pass3");
//        user3.setId("u3");
//
//        userList.add(user1);
//        userList.add(user2);
//        userList.add(user3);
//    }

//    @Override
//    public void createUser(User user) {
//        userList.add(user);
//    }


    @Override
    public void createUser(User user) {
        entityManager.persist(user);
    }

//    @Override
//    public User findUserByName(final @Nullable String name) {
//        User user = null;
//        for (User iuser : userList) {
//            if (name.equals(iuser.getName())) {
//                user = iuser;
//                break;
//            }
//        }
//        return user;
//    }
    @Override
    public User findUserByName(String name) {
        List<User> users = entityManager.createQuery("select e from User e where e.name = :userName", User.class)
                    .setParameter("userName", name)
                    .getResultList();
        if (users.isEmpty()) return null;
        return users.get(0);
    }


//    @Override
//    public User findUserById(final @Nullable String id) {
//        if (id == null || id.isEmpty()) return null;
//        User user = null;
//        for (User iuser : userList) {
//            if (id.equals(iuser.getId())) {
//                user = iuser;
//                break;
//            }
//        }
//        return user;
//    }


    @Override
    public User findUserById(String id) {
        return entityManager.find(User.class, id);
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
       logger.info("----------------validate start");
        if (userName == null || userName.isEmpty() || userPass == null || userPass.isEmpty()) return false;
        logger.info("----------------validate : user in base : " + userName);
        User userInBase = this.findUserByName(userName);
        logger.info("----------------validate : user in base : " + userInBase);
        if (userInBase == null) return false;
        if (userInBase.getPassword().equals(userPass)) return true;
        return false;
    }
}
