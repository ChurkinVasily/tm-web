package ru.churkin.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.churkin.api.IUserRepository;
import ru.churkin.entity.User;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class UserRepository implements IUserRepository {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    EntityManager entityManager;

    private List<User> userList = new ArrayList<>();

    @Override
    public void createUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User findUserByName(String name) {
        List<User> users = entityManager.createQuery("select e from User e where e.name = :userName", User.class)
                    .setParameter("userName", name)
                    .getResultList();
        if (users.isEmpty()) return null;
        return users.get(0);
    }

    @Override
    public User findUserById(String id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getUserList() {
        return userList;
    }
}
