package ru.churkin.api;

import ru.churkin.entity.Task;
import ru.churkin.entity.User;

import java.util.List;

public interface ITaskService {

    boolean createTask(String name, User user);

    Task findTaskById(String id);

    List<Task> findTaskByUserId(String id);

    boolean updateTask(Task task);

    boolean deleteTask(String id);

    List<Task> getTasksAll();

    List<Task> getByProjectId(String projectId, String userId);

}
