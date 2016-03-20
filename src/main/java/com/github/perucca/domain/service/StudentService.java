package com.github.perucca.domain.service;

import com.github.perucca.CsvMapper;
import com.github.perucca.CsvReader;
import com.github.perucca.domain.mail.StudentEvaluationTemplate;
import com.github.perucca.domain.model.StudentEvaluation;
import com.github.perucca.mailer.service.MailerService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
@Setter
public class StudentService {

    @Autowired
    private MailerService mailerService;

    public void notifyStudents(String filePath) {
        getStudentEvaluations(filePath)
                .stream()
                .map(StudentEvaluationTemplate::new)
                .forEach(mailerService::sendMail);
    }

    protected List<StudentEvaluation> getStudentEvaluations(String filePath) {
        return  read(filePath)
                .getLines()
                .stream()
                .map(CsvMapper::studentEvaluationMapper)
                .collect(toList());
    }

    protected CsvReader read(String filePath) {
        try {
            return new CsvReader(new InputStreamReader(new ClassPathResource(filePath).getInputStream()));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
