package academy.kata.PP_3_1_4_REST.controllers;

import academy.kata.PP_3_1_4_REST.model.User;
import academy.kata.PP_3_1_4_REST.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String getUserInfo(Model model,
                       @AuthenticationPrincipal UserDetails userDetails){

        User user = userService.showByEmail(userDetails.getUsername()).orElse(null);
        model.addAttribute("authUser", user);

        return "user";
    }
}
