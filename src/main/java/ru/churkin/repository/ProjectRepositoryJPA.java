package ru.churkin.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.churkin.entity.Project;

@Repository
public interface ProjectRepositoryJPA extends JpaRepository<Project, String> {

    @NotNull
    @Query(value = "select e from Project e where e.name = :projectName")
    Project findProjectByName(@NotNull @Param("projectName") String name);

}
