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

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@Getter
@Setter
@SessionScoped
@ManagedBean(name = "taskEditController")
public class TaskEditController extends SpringBeanAutowiringSupport {

    @Autowired
    ITaskService taskService;

    private String id;
    private String name;
    private String description;
    private String timeStart;
    private String timeFinish;
    private String text3;

    private Task task = new Task();

    public void init(){
        Task task = taskService.findTaskById(id);
        if (task!=null) {
            this.task = task;
            this.name = task.getName();
            this.description = task.getDescription();
            this.timeStart = task.getTimeStart();
            this.timeFinish = task.getTimeFinish();
        }
    }

    public String save() {
        Task task2 = taskService.findTaskById(id);
        if (task2 != null) {
            task.setId(task2.getId());
            task.setName(this.name);
            task.setDescription(this.description);
            task.setTimeStart(this.timeStart);
            task.setTimeFinish(this.timeFinish);
            taskService.updateTask(task);
            return "success";
        }
        return "failure";
    }


}
