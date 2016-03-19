package com.github.perucca;

import com.github.perucca.domain.StudentEvaluation;
import com.github.perucca.mailer.Template;

import java.util.Map;

public class StudentEvaluationTemplate implements Template {

    private StudentEvaluation studentEvaluation;

    public StudentEvaluationTemplate(StudentEvaluation studentEvaluation) {
        this.studentEvaluation = studentEvaluation;
    }

    @Override
    public Map<String, Object> getModel() {
        return studentEvaluation.toMapProperties();
    }

    @Override
    public String getReceiverMail() {
        return studentEvaluation.getStudent().getEmail();
    }
}
