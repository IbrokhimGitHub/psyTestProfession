package uz.psy.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.psy.demo.entity.Role;
import uz.psy.demo.entity.enums.RoleName;
import uz.psy.demo.repository.QuestionRepository;
import uz.psy.demo.repository.RoleRepository;
import uz.psy.demo.service.QuestionService;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    QuestionService questionService;
    @Autowired
    RoleRepository roleRepository;

    @Value(value = "${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    @Override
    public void run(String... args) throws Exception {

        if (ddlAuto.equals("create")) {
            questionService.generateQuestions();
            Role role=new Role();
            role.setRoleName(RoleName.ADMIN);
            roleRepository.save(role);
            Role role1=new Role();
            role1.setRoleName(RoleName.TESTER);
            roleRepository.save(role1);

        }


    }
}
