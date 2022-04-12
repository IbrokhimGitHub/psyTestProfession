package uz.psy.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.psy.demo.entity.User;

import javax.validation.constraints.Email;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository <User, UUID> {

    boolean existsByPhoneNumber(@Email String phoneNumber);



    Optional<User> findByPhoneNumber(@Email String phoneNumber);

}
