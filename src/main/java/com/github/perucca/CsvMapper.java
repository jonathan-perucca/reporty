package com.github.perucca;

import com.github.perucca.domain.Answer;
import com.github.perucca.domain.Student;
import com.github.perucca.domain.StudentEvaluation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Arrays.stream;

public interface CsvMapper {

    Predicate<String> isIntergerParsable = string -> {
        try {Integer.parseInt(string);} catch (NumberFormatException ex) {
            return false;
        }
        return true;
    };

    static StudentEvaluation studentEvaluationMapper(String line) {
        final String[] lineParts = line.split(",");
        final String commentPart = lineParts[lineParts.length - 1];

        final StudentEvaluation.StudentEvaluationBuilder builder = StudentEvaluation
                .builder()
                .student(Student.builder().name(lineParts[0]).email(lineParts[1]).build())
                .comment(commentPart.trim());

        List<Answer> answers = new ArrayList<>();
        stream(lineParts)
                .skip(2)
                .filter(isIntergerParsable)
                .mapToInt(Integer::valueOf)
                .mapToObj(Answer::new)
                .forEach(answers::add);

        return builder.answers(answers).build();
    }
}