package uz.psy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import uz.psy.demo.config.MostUsed;
import uz.psy.demo.entity.Answer;
import uz.psy.demo.entity.Subject;
import uz.psy.demo.entity.User;
import uz.psy.demo.entity.UserAnswers;
import uz.psy.demo.payload.ApiResponse;
import uz.psy.demo.repository.AnswerRepository;
import uz.psy.demo.repository.QuestionRepository;
import uz.psy.demo.repository.UserAnswersRepository;
import uz.psy.demo.repository.UserRepository;

import java.util.*;

@Service
public class AnswerService {

    @Autowired
    MostUsed mostUsed;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    UserAnswersRepository userAnswersRepository;


    public ApiResponse newMethod() {

        List<UserAnswers> byUserOrderByScoreDesc = userAnswersRepository.findFirst10ByUserOrderByScoreDesc(mostUsed.getCurrentUser());
        for (UserAnswers userAnswers : byUserOrderByScoreDesc) {
            System.out.println(userAnswers.getSubject().getName()+"   "+userAnswers.getScore());
        }

        return new ApiResponse("DONE",true);
    }


    public ApiResponse clickedFinishButton() {
        Map<Subject, Integer> subjectIntegerMap = new LinkedHashMap<>();
        User currentUser = mostUsed.getCurrentUser();

        List<Answer> answerList = answerRepository.findByUser(currentUser);
        for (Answer answer : answerList) {
            if (answer.getAnswer() == 1) {
                Subject subject = answer.getQuestion().getSubject();
                boolean isContainsKey = subjectIntegerMap.containsKey(subject);
                if (isContainsKey) {
                    Integer removedValue = subjectIntegerMap.remove(subject);
                    subjectIntegerMap.put(subject, removedValue + 1);
                } else {
                    subjectIntegerMap.put(subject, 1);
                }
            }
        }
        System.out.println(subjectIntegerMap.size());

        subjectIntegerMap.forEach((subject, integer) ->{
            UserAnswers userAnswers=new UserAnswers();
            userAnswers.setUser(currentUser);
            userAnswers.setSubject(subject);
            userAnswers.setScore(integer);
            userAnswersRepository.save(userAnswers);
        } );



        return new ApiResponse("Your test is finished", true);

    }
//    public ApiResponse getUserAnswers(){
//
//    }
}


