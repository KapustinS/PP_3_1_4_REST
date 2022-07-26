package academy.kata.PP_3_1_2_SECURITY.controllers;

import academy.kata.PP_3_1_2_SECURITY.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user")

    public String user( Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", user);

        return "user";
    }
}
