package ru.churkin.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.churkin.api.IUserService;
import ru.churkin.entity.User;
import ru.churkin.enums.Role;

import java.util.HashSet;
import java.util.Set;

@Service
//@Transactional
@NoArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.findUserByName(userName);
        if (user == null) throw new UsernameNotFoundException("user not found");

        // указываем роли юзеру
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(Role.USER.name()));

        // создаем объект UserDetails который позволить проверить введенные логин и пароль и аутентифицировать
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getName(),
                user.getPassword(), roles);

        return userDetails;
    }
}
