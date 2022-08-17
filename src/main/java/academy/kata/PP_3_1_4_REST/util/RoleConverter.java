package academy.kata.PP_3_1_4_REST.util;

import academy.kata.PP_3_1_4_REST.model.Role;
import academy.kata.PP_3_1_4_REST.service.UserService;
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
