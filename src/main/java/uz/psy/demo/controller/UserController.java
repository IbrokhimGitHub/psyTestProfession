package uz.psy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.psy.demo.entity.User;
import uz.psy.demo.payload.ApiResponse;
import uz.psy.demo.payload.LoginDto;
import uz.psy.demo.payload.RegisterDto;
import uz.psy.demo.service.AuthService;
import uz.psy.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("psy/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDto loginDto) {
        ApiResponse apiResponse=authService.login(loginDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:401).body(apiResponse);
    }
    @PostMapping("/register")
    public HttpEntity<?> registerUser(@RequestBody RegisterDto registerDto) {
        ApiResponse apiResponse=authService.registerUser(registerDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);

    }

    @GetMapping("getAll")
    public HttpEntity<?> getAll(){
        List<User> employees = userService.employeeList();
        return ResponseEntity.status(202).body(employees);
    }
}
