package com.github.perucca.domain.service;

import com.github.perucca.CsvReader;
import com.github.perucca.domain.model.StudentEvaluation;
import com.github.perucca.mailer.service.MailerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {

    @Spy
    StudentService studentService;

    @Mock
    MailerService mailerService;

    @Mock
    CsvReader csvReader;

    List<String> studentEvaluationLines = new ArrayList<>();
    String firstEvaluation = "Junior,0,2,1,1,0,1,3,1,0,0,1,-1,9, Comment One";
    String secondEvaluation = "Walter,0,2,1,0,1,1,3,1,1,0,1,5,16, Cannot be defeated";

    @Before
    public void setupEvaluationLines() {
        asList(firstEvaluation, secondEvaluation).forEach(studentEvaluationLines::add);

        when(csvReader.getLines()).thenReturn(studentEvaluationLines);
        doReturn(csvReader).when(studentService).read(any());

        studentService.setMailerService(mailerService);
    }

    @Test
    public void should_get_student_evaluations() {
        List<StudentEvaluation> studentEvaluations = studentService.getStudentEvaluations("somepath");

        assertThat(studentEvaluations).hasSize(2);
    }

    @Test
    public void should_notify_students() {
        studentService.notifyStudents("test.csv");

        verify(mailerService, times(2)).sendMail(any());
    }
}