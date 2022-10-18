package uz.psy.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.psy.demo.entity.User;
import uz.psy.demo.entity.UserAnswers;

import java.util.List;
import java.util.Optional;


public interface UserAnswersRepository extends JpaRepository<UserAnswers, Integer> {

    Optional<UserAnswers> findByUser(User user);

    List<UserAnswers> findAByUserOrderByScoreDesc(User user);

    List<UserAnswers> findFirst10ByUserOrderByScoreDesc(User user);




}
