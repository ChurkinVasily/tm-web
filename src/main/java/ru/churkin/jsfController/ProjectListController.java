package ru.churkin.jsfController;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.churkin.api.IProjectService;
import ru.churkin.api.IUserService;
import ru.churkin.entity.Project;
import ru.churkin.entity.User;
import ru.churkin.enums.Role;
import ru.churkin.repository.UserRepositoryJPA;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@Getter
@Setter
@ManagedBean(name = "projectListController")
@SessionScoped
public class ProjectListController  extends SpringBeanAutowiringSupport { // extend нужен чтобы инжектить бин spring в JSF

    @Autowired
    IProjectService projectService;

//    private List<Project> projects = projectService.getProjectAll();

    private String projectName;
    private String projectId;
    private Project project;

//       еще один способ, дающий возможность инжектить бин spring в JSF
//    private List<Project> projectsList = new ArrayList<>();
//    @PostConstruct
//    public void init(){
//
//        FacesContextUtils
//                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
//                .getAutowireCapableBeanFactory().autowireBean(this);
//        projectsList = projectService.getProjectAll();
//    }

    public List<Project> projectsList(){
        return projectService.getProjectAll();
    }


    public void removeProject(String id) {
        projectService.deleteProject(id);
    }
    public void createProject() {
        projectService.createProject(projectName);
    }

    public String print(){
        return "hello from controller ";
    }

}
