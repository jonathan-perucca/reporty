package com.github.perucca.domain.model;

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

    public String computeTotal() {
        double total = answers.stream()
                .mapToDouble(Answer::getMark)
                .sum();

        return String.format("%.2f", total);
    }

    public Map<String, Object> toMapProperties() {
        final HashMap<String, Object> map = new HashMap<>();
        map.put("student", student);
        map.put("average_mark", computeTotal());
        map.put("comment", comment);

        return map;
    }
}
