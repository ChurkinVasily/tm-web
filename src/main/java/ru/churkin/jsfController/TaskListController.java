package ru.churkin.jsfController;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.churkin.api.IProjectService;
import ru.churkin.api.ITaskService;
import ru.churkin.api.IUserService;
import ru.churkin.entity.Project;
import ru.churkin.entity.Task;
import ru.churkin.entity.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@Getter
@Setter
@ManagedBean(name = "taskListController")
@SessionScoped
public class TaskListController extends SpringBeanAutowiringSupport {

    @Autowired
    ITaskService taskService;

    @Autowired
    IUserService userService;

    @Autowired
    IProjectService projectService;

    private List<Task> tasks;

    private String taskName;
    private String taskId;
    private Task task;
    private User user = userService.findUserById("103714de-96a2-4138-ad6a-94169e099336");
    private String projectId;

    public List<Task> taskList() {
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
