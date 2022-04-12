package uz.psy.demo.config;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.psy.demo.entity.Role;
import uz.psy.demo.entity.User;


import java.util.Set;


@Component
public class MostUsed {


    public User getCurrentUser(){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return currentUser;
    }

    public Set<Role> getCurrentUserRole(){
        Set<Role> roles = getCurrentUser().getRoles();
        return roles;
    }
}
