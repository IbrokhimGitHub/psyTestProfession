package uz.psy.demo.service;


import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.psy.demo.config.MostUsed;
import uz.psy.demo.entity.Answer;
import uz.psy.demo.entity.Question;
import uz.psy.demo.entity.Subject;
import uz.psy.demo.entity.User;
import uz.psy.demo.payload.ApiResponse;
import uz.psy.demo.repository.AnswerRepository;
import uz.psy.demo.repository.QuestionRepository;
import uz.psy.demo.repository.UserRepository;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    MostUsed mostUsed;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    SubjectService subjectService;

    public ApiResponse getNextQuestion(String questionAnswer) {
        questionAnswer = questionAnswer + "";

        User currentUser = mostUsed.getCurrentUser();
        Integer questionCounter = currentUser.getQuestionCounter();
        if (questionCounter == 174) {
            return new ApiResponse("You have finished the test please push finish button", true);
        }
        if (questionCounter != 0) {
            Optional<Question> optionalCurrentQuestion = questionRepository.findByQuestionNumber(questionCounter);
            Question currentQuestion = optionalCurrentQuestion.get();
            Optional<Answer> byQuestion = answerRepository.findByQuestion(currentQuestion);
            Answer answer = null;
            if (!byQuestion.isPresent()) {
                answer = new Answer();
                answer.setQuestion(currentQuestion);
                answer.setUser(currentUser);
            } else {
                answer= byQuestion.get();
                if (questionAnswer.equals("YES"))
                    answer.setAnswer(1);
            }
            answerRepository.save(answer);

        }


        currentUser.setQuestionCounter(questionCounter + 1);
        Optional<Question> optionalQuestionForResponse = questionRepository.findByQuestionNumber(currentUser.getQuestionCounter());
        Question questionForResponse = optionalQuestionForResponse.get();

        userRepository.save(currentUser);


        return new ApiResponse(questionForResponse.getDescription(), true);
    }


    public ApiResponse generateQuestions() {
        String fileName = "E:\\Spring Projects\\psy-test-profession\\questions.docx";
        int counter = 1;
        int subjectCounter = 1;
        ApiResponse apiResponse = subjectService.generateSubjects();
        if (!apiResponse.isSuccess()) {
            return new ApiResponse("there are problems with subject creating", false);
        }

        try (XWPFDocument doc = new XWPFDocument(
                Files.newInputStream(Paths.get(fileName)))) {

            XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(doc);
            String docText = xwpfWordExtractor.getText();
            String[] split = docText.split("\n");
            for (String s : split) {
                Question question = new Question();
                question.setQuestionNumber(counter);
                question.setDescription(s);
                if (subjectCounter > 29)
                    subjectCounter = 1;
                Subject subjectByNumber = subjectService.getSubjectByNumber(subjectCounter);
                question.setSubject(subjectByNumber);
                questionRepository.save(question);
                counter++;
                subjectCounter++;


            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        if (counter == 175) {
            return new ApiResponse("All questions are saved", true);
        } else {
            return new ApiResponse("Some questions are not saved", false);
        }
    }


}
