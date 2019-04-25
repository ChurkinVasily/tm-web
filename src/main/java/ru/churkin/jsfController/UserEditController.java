package ru.churkin.jsfController;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
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
@SessionScoped
@ManagedBean(name = "userEditController")
public class UserEditController extends SpringBeanAutowiringSupport {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    IUserService userService;

    private String id;
    private String name;
    private String role;

    private User user = new User();
    private List<String> roleList = new ArrayList<>();

    public void init(){
        User user = userService.findUserById(id);
        if (user!=null) {
            this.user = user;
            this.name = user.getName();
            this.role = user.getRole().toString();
        }
        this.roleList.add(Role.ADMIN.name());
        this.roleList.add(Role.USER.name());
    }

    public String save() {
        User user2 = userService.findUserById(id);
        if (user2 != null) {
            user.setId(user2.getId());
            user.setName(this.name);
            user.setRole(Role.valueOf(role));
            userService.updateUser(user);
            logger.info("------------------------------success save User");
            return "success";
        }
        return "failure";
    }
}
