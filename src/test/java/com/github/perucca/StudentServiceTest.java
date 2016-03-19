package com.github.perucca;

import com.github.perucca.domain.StudentEvaluation;
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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {

    @Spy
    StudentService studentService;

    @Mock
    CsvReader csvReader;

    List<String> studentEvaluationLines = new ArrayList<>();
    String firstEvaluation = "Junior,0,2,1,1,0,1,3,1,0,0,1,-1,9, Comment One";
    String secondEvaluation = "Walter,0,2,1,0,1,1,3,1,1,0,1,5,16, Cannot be defeated";

    @Before
    public void setupEvaluationLines() {
        asList(firstEvaluation, secondEvaluation).forEach(studentEvaluationLines::add);
    }

    @Test
    public void should_get_student_evaluations() {
        when(csvReader.getLines()).thenReturn(studentEvaluationLines);
        doReturn(csvReader).when(studentService).read(any());

        List<StudentEvaluation> studentEvaluations = studentService.getStudentEvaluations("somepath");

        assertThat(studentEvaluations).hasSize(2);
    }
}