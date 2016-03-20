package com.github.perucca.domain.model;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class StudentEvaluationTest {

    StudentEvaluation studentEvaluation;

    @Test
    public void should_compute_average_marks() {
        List<Answer> answers = asList(1, 5, 6).stream().map(Answer::new).collect(toList());
        studentEvaluation = StudentEvaluation.builder().answers(answers).build();

        String averageMark = studentEvaluation.computeTotal();

        assertThat(averageMark).isEqualTo("12,00");
    }
}