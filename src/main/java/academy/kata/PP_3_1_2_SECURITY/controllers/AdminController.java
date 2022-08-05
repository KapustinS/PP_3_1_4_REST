package academy.kata.PP_3_1_2_SECURITY.controllers;

import academy.kata.PP_3_1_2_SECURITY.Util.UserValidator;
import academy.kata.PP_3_1_2_SECURITY.model.Role;
import academy.kata.PP_3_1_2_SECURITY.model.User;
import academy.kata.PP_3_1_2_SECURITY.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserServiceImpl userServiceImpl;

    private final UserValidator userValidator;
    private static final String REDIRECT_ADMIN = "redirect:/admin";

    @Autowired
    public AdminController(UserServiceImpl userServiceImpl, UserValidator userValidator) {
        this.userServiceImpl = userServiceImpl;
        this.userValidator = userValidator;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userServiceImpl.listUsers());
        model.addAttribute("newUser", new User());
        model.addAttribute("allRoles", userServiceImpl.getAllAvailableRoles());
        return "admin/adminPage";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("newUser") User user) {

        userServiceImpl.add(user);
        return REDIRECT_ADMIN;
    }

//============================
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userServiceImpl.showById(id));

        return "admin/show";
    }

//    @GetMapping("/new")
//    public String newUser(@ModelAttribute("user") User user, Model model) {
//        model.addAttribute("allRoles", userServiceImpl.getAllAvailableRoles());
//        return "admin/new";
//    }

//    @PostMapping("/new")
//    public String create(@ModelAttribute("user") @Valid User user
//            , BindingResult bindingResult
//    ) {
//
//        userValidator.validate(user, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            return "admin/new";
//        }
//        userServiceImpl.add(user);
//        return REDIRECT_ADMIN;
//    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userServiceImpl.showById(id));
        return "admin/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") int id) {

        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors())
            return "admin/edit";

        userServiceImpl.update(user, id);
        return REDIRECT_ADMIN;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userServiceImpl.delete(id);
        return REDIRECT_ADMIN;
    }
}
