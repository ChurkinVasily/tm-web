package ru.churkin.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class Project {

    private String id;
    private String name;
    private String description;
    private String timeStart;
    private String timeFinish;

    public Project(String name) {
//        this.id = UUID.randomUUID().toString();
        this.name = name;
    }
}
