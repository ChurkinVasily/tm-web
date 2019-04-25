package ru.churkin.jsfController;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.churkin.api.IProjectService;
import ru.churkin.api.ITaskService;
import ru.churkin.entity.Project;
import ru.churkin.entity.Task;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Getter
@Setter
@SessionScoped
@ManagedBean(name = "taskEditController")
public class TaskEditController extends SpringBeanAutowiringSupport {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    ITaskService taskService;

    @Autowired
    IProjectService projectService;

    private String id;
    private String name;
    private String description;
    private String timeStart;
    private String timeFinish;
    private String projectId;

    private Task task = new Task();
    private List<Project> projectList = new ArrayList<>();

    public void init(){
        Task task = taskService.findTaskById(id);
        if (task!=null) {
            this.task = task;
            this.name = task.getName();
            this.description = task.getDescription();
            this.timeStart = task.getTimeStart();
            this.timeFinish = task.getTimeFinish();
            if (!(task.getProject()==null)) {
                this.projectId = task.getProject().getId();
            }
            else {
                this.projectId = "";
            }
        }
        this.projectList = projectService.getProjectAll();

    }

    public String save() {
        Task task2 = taskService.findTaskById(id);
        if (task2 != null) {
            task.setId(task2.getId());
            task.setName(this.name);
            task.setDescription(this.description);
            task.setTimeStart(this.timeStart);
            task.setTimeFinish(this.timeFinish);
            task.setProject(projectService.findProjectById(projectId));
            taskService.updateTask(task);
            logger.info("------------------------------success");
            return "success";
        }
        return "failure";
    }


}
