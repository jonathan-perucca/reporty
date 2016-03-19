package com.github.perucca.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ToString
@Builder
@Getter
public class StudentEvaluation {

    private Student student;
    private List<Answer> answers;
    private String comment;

    public Double computeMarks() {
        return answers.stream()
                .mapToInt(Answer::getMark)
                .average()
                .getAsDouble();
    }

    public Map<String, Object> toMapProperties() {
        final HashMap<String, Object> map = new HashMap<>();
        map.put("student", student);
        map.put("average_mark", computeMarks());
        map.put("comment", comment);

        return map;
    }
}
