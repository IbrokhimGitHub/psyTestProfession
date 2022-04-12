package uz.psy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.psy.demo.config.MostUsed;
import uz.psy.demo.entity.Answer;
import uz.psy.demo.entity.Subject;
import uz.psy.demo.entity.User;
import uz.psy.demo.payload.ApiResponse;
import uz.psy.demo.repository.AnswerRepository;
import uz.psy.demo.repository.QuestionRepository;
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

    public static void main(String[] args) {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("SSS", 10);
        map.put("WWW", 20);
        map.put("EEE", 30);
        map.put("XXX", 40);
        System.out.println(map.remove("SSS"));
    }

//    public ApiResponse newMethod() {
//        Map<Subject, Integer> subjectIntegerMap = new LinkedHashMap<>();
//        User currentUser = mostUsed.getCurrentUser();
////        if (currentUser.getQuestionCounter() != 174) {
////            return new ApiResponse("You have not finished test", false);
////        }
//        List<Answer> answerList = answerRepository.findByUser(currentUser);
//        for (Answer answer : answerList) {
//            if (answer.getAnswer() == 1) {
//                Subject subject = answer.getQuestion().getSubject();
//                boolean isContainsKey = subjectIntegerMap.containsKey(subject);
//                if (isContainsKey) {
//                    Integer removedValue = subjectIntegerMap.remove(subject);
//                    subjectIntegerMap.put(subject, removedValue + 1);
//                } else {
//                    subjectIntegerMap.put(subject, 1);
//                }
//
//
//            }
//
//        }
//        subjectIntegerMap.forEach((subject, integer) -> System.out.println(subject.getName() + ":   " + integer));
//        return new ApiResponse("done", true);
//
//    }


    public ApiResponse clickedFinishButton() {
        Map<Subject, Integer> subjectIntegerMap = new LinkedHashMap<>();
        User currentUser = mostUsed.getCurrentUser();
        if (currentUser.getQuestionCounter() != 174) {
            return new ApiResponse("You have not finished test", false);
        }
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

//        subjectIntegerMap.forEach((subject, integer) -> System.out.println(subject.getName() + ":   " + integer));

        return new ApiResponse("Your test is finished", true);

    }
    public ApiResponse getUserAnswers(){

    }
}
