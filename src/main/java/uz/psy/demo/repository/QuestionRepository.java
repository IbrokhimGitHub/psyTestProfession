package uz.psy.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.psy.demo.entity.Question;

import java.util.Optional;


public interface QuestionRepository extends JpaRepository <Question,Integer> {
    Optional<Question> findByQuestionNumber(Integer questionNumber);
}
