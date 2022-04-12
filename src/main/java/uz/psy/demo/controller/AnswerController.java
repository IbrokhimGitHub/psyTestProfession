package uz.psy.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.psy.demo.payload.ApiResponse;
import uz.psy.demo.service.AnswerService;


@RestController
@RequestMapping("psy/answer")
public class AnswerController {
    @Autowired
    AnswerService answerService;

    @GetMapping("finish")
    public HttpEntity<?> finish(){
        ApiResponse apiResponse = answerService.clickedFinishButton();
        return ResponseEntity.status(apiResponse.isSuccess()?200:401).body(apiResponse);
    }
//    @GetMapping("finish1")
//    public HttpEntity<?> finish1(){
//        ApiResponse apiResponse = answerService.newMethod();
//        return ResponseEntity.status(apiResponse.isSuccess()?200:401).body(apiResponse);
//    }
}
