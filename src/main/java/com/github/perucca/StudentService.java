package com.github.perucca;

import com.github.perucca.domain.StudentEvaluation;
import com.github.perucca.mailer.service.MailerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class StudentService {

    private final MailerService mailerService;

    public void notifyStudents(List<StudentEvaluation> studentEvaluations) {
        studentEvaluations.stream()
                .map(StudentEvaluationTemplate::new)
                .forEach(mailerService::sendMail);
    }

    public List<StudentEvaluation> getStudentEvaluations(String filePath) {
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
