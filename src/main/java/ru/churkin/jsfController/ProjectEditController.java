package ru.churkin.jsfController;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.churkin.api.IProjectService;
import ru.churkin.entity.Project;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@Getter
@Setter
@ManagedBean(name="projectEdit")
@SessionScoped
public class ProjectEditController extends SpringBeanAutowiringSupport {

    @Autowired
    IProjectService projectService;

    private String id;
    private String name;
    private String description;
    private String timeStart;
    private String timeFinish;

    private Project project = new Project();

    public void init(){
        Project project = projectService.findProjectById(id);
        if (project!=null) {
            this.project = project;
            this.name = project.getName();
            this.description = project.getDescription();
            this.timeStart = project.getTimeStart();
            this.timeFinish = project.getTimeFinish();
        }
    }

    public String save() {
        Project project2 = projectService.findProjectById(id);
        if (project2 != null) {
            project.setId(project2.getId());
            project.setName(this.name);
            project.setDescription(this.description);
            project.setTimeStart(this.timeStart);
            project.setTimeFinish(this.timeFinish);
            projectService.updateProject(project);
            return "success";
        }
        return "failure";
    }

}
