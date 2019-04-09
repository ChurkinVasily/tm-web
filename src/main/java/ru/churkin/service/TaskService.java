package ru.churkin.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.churkin.api.ITaskService;
import ru.churkin.entity.Task;
import ru.churkin.entity.User;
import ru.churkin.repository.TaskRepositoryJPA;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@NoArgsConstructor
public class TaskService implements ITaskService {

    @Autowired
    TaskRepositoryJPA taskRepository;

    @Override
    public List<Task> getTasksAll() {
        List<Task> taskList = taskRepository.findAll();
        if (taskList.isEmpty()) return null;
        return taskList;
    }

    @Override
    public List<Task> findTaskByUserId(String userId) {
        return taskRepository.getByUserId(userId);
    }

    @Override
    public boolean createTask(@Nullable String name, @Nullable User user) {
        if (name == null || user == null) return false;
        boolean isExist = false;
        for (Task ctask : taskRepository.findAll()) {
            if (name.equals(ctask.getName()) && !(name.isEmpty())) {
                isExist = true;
                return false;
            }
        }
        Task task = new Task(name);
        task.setUser(user);
        taskRepository.save(task);
        return true;
    }

    @Override
    public Task findTaskById(@Nullable String id) {
        if (id == null || id.isEmpty()) return null;
        Optional<Task> taskList = taskRepository.findById(id);
        Task task = (Task) taskList.get();
        return task;
    }

    @Override
    public List<Task> getByProjectId(@Nullable String projectId, @Nullable String userId) {
        if (projectId.isEmpty() || projectId == null || userId.isEmpty() || userId == null) return null;
        return taskRepository.getByProjectId(userId, projectId);
    }

    @Override
    public boolean deleteTask(@Nullable String id) {
        if (id == null || id.isEmpty()) return false;
        Optional<Task> optionalTask = taskRepository.findById(id);
        Task task = optionalTask.isPresent() ? optionalTask.get() : null;
        if (task == null) return false;
        taskRepository.delete(task);
        return true;
    }

    @Override
    public boolean updateTask(Task task) {
        taskRepository.save(task);
        return true;
    }
}
