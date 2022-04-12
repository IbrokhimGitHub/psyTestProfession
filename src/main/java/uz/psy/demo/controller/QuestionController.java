package uz.psy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.psy.demo.payload.ApiResponse;
import uz.psy.demo.service.AnswerService;
import uz.psy.demo.service.QuestionService;

@RestController
@RequestMapping("psy/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    AnswerService answerService;

    @GetMapping("generate")
    public HttpEntity<?> generateQuestions(){
        ApiResponse apiResponse = questionService.generateQuestions();
        return ResponseEntity.status(apiResponse.isSuccess()?200:401).body(apiResponse);
    }
    @PostMapping("test")
    public HttpEntity<?> test(@RequestParam String questionAnswer){
        ApiResponse apiResponse = questionService.getNextQuestion(questionAnswer);
        return ResponseEntity.status(apiResponse.isSuccess()?200:401).body(apiResponse);
    }
    @PostMapping("finish")
    public HttpEntity<?> test(){
        ApiResponse apiResponse = answerService.clickedFinishButton();
        return ResponseEntity.status(apiResponse.isSuccess()?200:401).body(apiResponse);
    }

}
