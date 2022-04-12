package uz.psy.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.psy.demo.entity.Answer;
import uz.psy.demo.entity.Question;
import uz.psy.demo.entity.User;
import java.util.List;
import java.util.Optional;


public interface AnswerRepository extends JpaRepository <Answer,Integer> {
    List<Answer> findByUserOrderByQuestion(User user);
    List<Answer> findByUser(User user);
    Optional<Answer> findByQuestion(Question question);
}
