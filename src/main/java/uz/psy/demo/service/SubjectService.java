package uz.psy.demo.service;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.psy.demo.entity.Question;
import uz.psy.demo.entity.Subject;
import uz.psy.demo.payload.ApiResponse;
import uz.psy.demo.repository.SubjectRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    public Subject getSubjectByNumber( int subjectNumber){
        Subject subjectBySubjectNumber = subjectRepository.getSubjectBySubjectNumber(subjectNumber);
        return subjectBySubjectNumber;

    }
    public ApiResponse generateSubjects(){
        int counter=1;
        String fileName = "E:\\Spring Projects\\psy-test-profession\\subjects.docx";



        try (XWPFDocument doc = new XWPFDocument(
                Files.newInputStream(Paths.get(fileName)))) {

            XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(doc);
            String docText = xwpfWordExtractor.getText();
            String[] split = docText.split("\n");
            for (String s : split) {
                Subject subject=new Subject();
                subject.setSubjectNumber(counter);
                subject.setName(s);
                subjectRepository.save(subject);
                counter++;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        if (counter==30){
            return new ApiResponse("All questions are saved",true);
        }else {
            return new ApiResponse("Some questions are not saved",false);
        }
    }

}
