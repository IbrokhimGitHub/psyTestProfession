package uz.psy.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.psy.demo.entity.Subject;

import java.util.Optional;


public interface SubjectRepository extends JpaRepository <Subject,Integer> {
    Subject getSubjectBySubjectNumber(Integer subjectNumber);

}
