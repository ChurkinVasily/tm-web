package ru.churkin.api;

import ru.churkin.entity.Task;

import java.util.List;

public interface ITaskRepository {

    void createTask(String name, String userId);

    Task findTaskById(String id);

    List<Task> getTaskAll();

//    List<Task> getByProjectId(String projectId, String userId);
//
//    List<Task> getByUserId(String userId);

    void updateTask(Task task);

//    void deleteTaskById(String id);

    void deleteTask(Task task);
}
