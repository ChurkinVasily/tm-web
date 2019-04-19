package ru.churkin.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {

    private Boolean success = true;

    public String message = "";

    public Result(Boolean success) {
        this.success = success;
    }

    public Result(Exception e) {
        success = false;
        message = e.getMessage();
    }

    public Result() {
    }
}
