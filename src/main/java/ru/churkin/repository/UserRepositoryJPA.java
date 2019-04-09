package ru.churkin.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.churkin.entity.User;

@Repository
public interface UserRepositoryJPA extends JpaRepository<User, String> {

    @NotNull
    @Query("select e from User e where e.name = :userName")
    User findUserByName(@Param("userName") String name);

}
