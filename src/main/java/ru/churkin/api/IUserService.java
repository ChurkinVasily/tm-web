package ru.churkin.api;

import ru.churkin.entity.User;

import java.util.List;

public interface IUserService {

    boolean createNewUser(String name, String pass);

    User findUserById(String id);

    User findUserByName(String userName) ;

    boolean validate(String name, String pass);

//    List<User> getUsersAll();
//
//    boolean removeUserById(String id);

}
