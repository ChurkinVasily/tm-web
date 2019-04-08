package ru.churkin.api;

import ru.churkin.entity.User;

public interface IUserService {

//    boolean createNewUser(User user);

    boolean createNewUser(String name, String pass);

    User findUserById(String id);

    User findUserByName(String userName) ;
//
//    boolean isExist(String userName);
//
//    boolean validateUser(User user);
//
    boolean validate(String name, String pass);

//    User getCurrentUser();
//
//    void setCurrentUser(User user);
//
//    void getUserByName(String userName);

}
