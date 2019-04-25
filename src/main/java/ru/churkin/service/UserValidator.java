package ru.churkin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.churkin.api.IUserService;
import ru.churkin.entity.User;

import java.util.logging.Logger;

@Component
public class UserValidator implements Validator {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    private IUserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        User user = (User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");

        if (userService.findUserByName(user.getName()) != null) {
            errors.rejectValue("name", "Duplicate.userForm.username");
        }
    }
}
