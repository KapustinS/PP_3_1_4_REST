package academy.kata.PP_3_1_2_SECURITY.Util;


import academy.kata.PP_3_1_2_SECURITY.model.User;
import academy.kata.PP_3_1_2_SECURITY.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if(checkExistUserByUsername(user.getUsername())){
            errors.rejectValue("username", "", "User Exist");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm", "Password not confirmed");
        }
    }

    public boolean checkExistUserByUsername(String username){
        return userService.showByUsername(username).isPresent();
    }
}
