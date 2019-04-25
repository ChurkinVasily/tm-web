package ru.churkin.jsfController;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.churkin.api.IProjectService;
import ru.churkin.api.ISecurityService;
import ru.churkin.api.ITaskService;
import ru.churkin.api.IUserService;
import ru.churkin.entity.Task;
import ru.churkin.entity.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;
import java.util.logging.Logger;

@Getter
@Setter
@ManagedBean(name = "taskListController")
@SessionScoped
public class TaskListController extends SpringBeanAutowiringSupport {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    ITaskService taskService;

    @Autowired
    IUserService userService;

    @Autowired
    IProjectService projectService;

    @Autowired
    ISecurityService securityService;

    private List<Task> tasks;

    private String taskName;
    private String taskId;
    private Task task;
    String username = securityService.findLoggedInUsername();
    User user = userService.findUserByName(username);

    private String projectId;

    public List<Task> taskList() {
        logger.info("------------------------taskList start " + username);
        logger.info("------------------------taskList start " + user);
        if (!(projectId == null) && !(projectId.isEmpty())) {
            final String tempProjectId = projectId;
            projectId = null;
            return taskService.getByProjectId(tempProjectId, user.getId());
        }
        return taskService.findTaskByUserId(user.getId());
    }

    public void createTask(){
        taskService.createTask(taskName, user);
    }

    public void removeTask(String id){
        taskService.deleteTask(id);
    }





}
