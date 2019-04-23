package ru.churkin.api;

public interface ISecurityService {

    public String findLoggedInUsername();

    public void autoLogin(String username, String password);
}
