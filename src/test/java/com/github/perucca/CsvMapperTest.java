package com.github.perucca;

import com.github.perucca.domain.model.StudentEvaluation;
import org.junit.Test;

import static com.github.perucca.CsvMapper.isIntergerParsable;
import static com.github.perucca.CsvMapper.studentEvaluationMapper;
import static org.assertj.core.api.Assertions.assertThat;

public class CsvMapperTest {

    String studentEvaluation = "Walter,walter@gmail.com,0,2,1,0,1,1,3,1,1,0,1,5,16, Cannot be defeated";

    @Test
    public void should_map_student_evaluation() {
        StudentEvaluation studentEvaluation = studentEvaluationMapper(this.studentEvaluation);

        assertThat(studentEvaluation.getStudent().getName()).isEqualTo("Walter");
        assertThat(studentEvaluation.getStudent().getEmail()).isEqualTo("walter@gmail.com");
        assertThat(studentEvaluation.getAnswers()).hasSize(13);
        assertThat(studentEvaluation.getAnswers().get(1).getMark()).isEqualTo(2);
        assertThat(studentEvaluation.getComment()).isEqualTo("Cannot be defeated");
    }

    @Test
    public void should_validate_is_integer_parsable() {
        assertThat(isIntergerParsable.test("12")).isEqualTo(true);
    }

    @Test
    public void should_not_validate_is_integer_parsable() {
        assertThat(isIntergerParsable.test("te")).isEqualTo(false);
    }
}