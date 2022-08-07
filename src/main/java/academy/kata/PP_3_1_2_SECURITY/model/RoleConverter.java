package academy.kata.PP_3_1_2_SECURITY.model;

import academy.kata.PP_3_1_2_SECURITY.service.UserService;
import academy.kata.PP_3_1_2_SECURITY.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter implements Converter<String, Role> {

    UserService userService;

    @Autowired
    public RoleConverter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Role convert(String id) {

        int idRole = Integer.parseInt(id);

        return userService.getAllAvailableRoles()
                .stream()
                .filter(role -> role.getId() == idRole)
                .findFirst().orElse(null);
    }
}
