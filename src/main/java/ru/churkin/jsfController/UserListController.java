package ru.churkin.jsfController;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.churkin.api.ISecurityService;
import ru.churkin.api.IUserService;
import ru.churkin.entity.User;
import ru.churkin.enums.Role;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Getter
@Setter
@ManagedBean(name = "userListController")
@SessionScoped
public class UserListController extends SpringBeanAutowiringSupport {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    IUserService userService;

    @Autowired
    ISecurityService securityService;

    private List<User> users;

    private String userName;
    private String userId;

    private String userRole;

    private User user;

    private List<String> roleList = new ArrayList<>();

//    String username = securityService.findLoggedInUsername();
//    User user = userService.findUserByName(username);

    public List<User> userList() {
        roleList.clear();
        roleList.add(Role.USER.name());
        roleList.add(Role.ADMIN.name());
        return userService.getUserAll();
    }
}
