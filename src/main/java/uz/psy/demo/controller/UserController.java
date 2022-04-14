package uz.psy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.psy.demo.entity.User;
import uz.psy.demo.payload.ApiResponse;
import uz.psy.demo.payload.LoginDto;
import uz.psy.demo.payload.RegisterDto;
import uz.psy.demo.service.AuthService;
import uz.psy.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("psy/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDto loginDto) {
        ApiResponse apiResponse = authService.login(loginDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 401).body(apiResponse);
    }
    @GetMapping("/login")
    public String login() {
        System.out.println("AppCont->login");

        return "login";
    }

        @PostMapping("/register")
    public HttpEntity<?> registerUser(@RequestBody RegisterDto registerDto) {
        ApiResponse apiResponse = authService.registerUser(registerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);


    }



    @GetMapping("/register")
    public String startingRegister(Model model) {
        System.out.println("AppCont->register");
//        model.addAttribute("registration form");
        return "register";

    }

    @GetMapping("getAll")
    public HttpEntity<?> getAll() {
        List<User> employees = userService.employeeList();
        return ResponseEntity.status(202).body(employees);
    }
}
