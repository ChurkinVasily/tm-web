package ru.churkin.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.churkin.api.ITaskRepository;
import ru.churkin.api.ITaskService;
import ru.churkin.entity.Task;
import ru.churkin.entity.User;

import java.util.List;

@Service
@Transactional
@NoArgsConstructor
public class TaskService implements ITaskService {

    @Autowired
    ITaskRepository taskRepository;

    @Override
    public List<Task> getTasksAll() {
        List<Task> taskList = taskRepository.getTaskAll();
        if (taskList.isEmpty()) return null;
        return taskList;
    }

    @Override
    public List<Task> findTaskByUserId(String id) {
        return taskRepository.getByUserId(id);
    }

    @Override
    public boolean createTask(@Nullable String name, @Nullable User user) {
        if (name == null || user == null) return false;
        boolean isExist = false;
        for (Task ctask : taskRepository.getTaskAll()) {
            if (name.equals(ctask.getName()) && !(name.isEmpty())) {
                isExist = true;
                return false;
            }
        }
        taskRepository.createTask(name, user);
        return true;
    }

    @Override
    public Task findTaskById(@Nullable String id) {
        if (id == null || id.isEmpty()) return null;
        return taskRepository.findTaskById(id);
    }

    @Override
    public List<Task> getByProjectId(@Nullable String projectId, @Nullable String userId) {
        if (projectId.isEmpty() || projectId == null || userId.isEmpty() || userId == null) return null;
        return taskRepository.getByProjectId(projectId, userId);
    }

    @Override
    public boolean deleteTask(@Nullable String id) {
        if (id == null || id.isEmpty()) return false;
        Task task = taskRepository.findTaskById(id);
        taskRepository.deleteTask(task);
        return true;
    }

    @Override
    public boolean updateTask(Task task) {
        taskRepository.updateTask(task);
        return true;
    }
}
