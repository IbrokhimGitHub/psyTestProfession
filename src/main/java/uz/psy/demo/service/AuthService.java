package uz.psy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.psy.demo.entity.Role;
import uz.psy.demo.entity.User;
import uz.psy.demo.entity.enums.RoleName;
import uz.psy.demo.payload.ApiResponse;
import uz.psy.demo.payload.LoginDto;
import uz.psy.demo.payload.RegisterDto;
import uz.psy.demo.repository.RoleRepository;
import uz.psy.demo.repository.UserRepository;
import uz.psy.demo.security.JwtProvider;


import java.util.*;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;



    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {

        Optional<User> optionalUser = userRepository.findByPhoneNumber(phoneNumber);
        return optionalUser.orElseThrow(() -> new UsernameNotFoundException(phoneNumber+" can't find user"));

    }

    public ApiResponse login(LoginDto loginDto) {
        try {


            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getPhoneNumber(),
                    loginDto.getPassword()
            ));
            User principal = (User) authenticate.getPrincipal();
//            Employee principal = mostUsed.getCurrentEmployee();

            String token = jwtProvider.generateToken(loginDto.getPhoneNumber(), principal.getRoles());
            return new ApiResponse("Token", true, token);
        }catch (BadCredentialsException badCredentialsException){
            return new ApiResponse("USERNAME OR PASSWORD  IS MISTAKE",false);
        }
    }

    public ApiResponse registerUser(RegisterDto registerDto) {

        boolean existsByPhoneNumber = userRepository.existsByPhoneNumber(registerDto.getPhoneNumber());
//        if (existsByPhoneNumber){
//            return new ApiResponse("Such phoneNumber exist",false);
//        }


        User user=new User();
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setPhoneNumber(registerDto.getPhoneNumber());
        user.setAge(registerDto.getAge());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        String role=registerDto.getRole();
        System.out.println(role);
        Set<Role> roleSet=new HashSet<>();


        if (role.equals("ADMIN")){
            roleSet.add(roleRepository.findByRoleName(RoleName.ADMIN));
            user.setRoles(roleSet);
        }else {
            roleSet.add(roleRepository.findByRoleName(RoleName.TESTER));
            user.setRoles(roleSet);

        }
        User save = userRepository.save(user);
        System.out.println(save.getRoles());

        return new ApiResponse("Success, new user saved",true);

    }

    public ApiResponse resetPassword(){
        return null;
    }
}
