package uz.psy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import uz.psy.demo.entity.User;
import uz.psy.demo.payload.ApiResponse;
import uz.psy.demo.payload.LoginDto;
import uz.psy.demo.payload.RegisterDto;
import uz.psy.demo.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> employeeList() {
        List<User> employeeList = userRepository.findAll();
        return employeeList;
    }
}
