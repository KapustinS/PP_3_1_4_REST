package academy.kata.PP_3_1_4_REST.controllers;

import academy.kata.PP_3_1_4_REST.model.User;
import academy.kata.PP_3_1_4_REST.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserServiceImpl userServiceImpl;

//    private final UserValidator userValidator;
    private static final String REDIRECT_ADMIN = "redirect:/admin";

    @Autowired
    public AdminController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping
    public String index(Model model) {

        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("users", userServiceImpl.listUsers());
        model.addAttribute("user", new User());
        model.addAttribute("authUser", authUser);
        model.addAttribute("allRoles", userServiceImpl.getAllAvailableRoles());
        return "admin/adminPage";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") User user) {

        userServiceImpl.add(user);
        return REDIRECT_ADMIN;
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("id") int id) {

        userServiceImpl.update(user, id);
        return REDIRECT_ADMIN;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {

        userServiceImpl.delete(id);
        return REDIRECT_ADMIN;
    }

}
