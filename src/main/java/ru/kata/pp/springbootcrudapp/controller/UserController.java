package ru.kata.pp.springbootcrudapp.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.pp.springbootcrudapp.dto.UserDto;
import ru.kata.pp.springbootcrudapp.model.User;
import ru.kata.pp.springbootcrudapp.service.UserService;

import java.util.List;
import java.util.stream.IntStream;

@Controller
@AllArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public String main() {
        return "redirect:users";
    }

    @GetMapping("/users")
    public String findAll(@RequestParam(required = false, defaultValue = "1") Integer page,
                          @RequestParam(required = false, defaultValue = "5") Integer size,
                          Model model) {
        log.info("handling users request: {} {}", page, size);

        Page<User> usersPage = userService.fetchUsers(page - 1, size);
        model.addAttribute("users_page", usersPage);

        int totalPages = usersPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
            model.addAttribute("page_numbers", pageNumbers);
        }
        return "b-users";
    }

    @GetMapping("/user-create")
    public String createUserForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "b-user-create";
    }

    @PostMapping("/user-create")
    public String createUser(@ModelAttribute("user") @Valid UserDto user,
                             BindingResult bindingResult) {
        log.info("handling create user request: {}", user);
        if (bindingResult.hasErrors()) {
            return "b-user-create";
        }
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/user-update")
    public String updateUserForm(@RequestParam(name = "id") Long id,
                                 Model model) {
        model.addAttribute("user", userService.findById(id));
        return "b-user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(@ModelAttribute("user") @Valid UserDto user,
                             BindingResult bindingResult) {
        log.info("handling update user request: {}", user);
        if (bindingResult.hasErrors()) {
            return "b-user-update";
        }
        userService.saveUser(user);
        return "redirect:/users";
    }

    @PostMapping("/user-delete")
    public String deleteUser(@RequestParam(name = "id") Long id) {
        log.info("handling delete user by id request: id = {}", id);
        userService.deleteById(id);
        return "redirect:/users";
    }
}
