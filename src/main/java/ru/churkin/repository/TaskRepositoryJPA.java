package ru.churkin.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.churkin.entity.Task;

import java.util.List;

@Repository
public interface TaskRepositoryJPA extends JpaRepository<Task, String> {

    @NotNull
    @Query("select t from Task t where t.user.id = :userId")
    List<Task> getByUserId(@Param("userId") String userId);

    @NotNull
    @Query("select t from Task t where t.user.id = :userId and t.project.id = :projectId")
    List<Task> getByProjectId(@Param("userId") String userId, @Param("projectId") String projectId);




}
