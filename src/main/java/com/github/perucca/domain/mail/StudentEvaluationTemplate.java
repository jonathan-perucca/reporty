package com.github.perucca.domain.mail;

import com.github.perucca.domain.model.StudentEvaluation;
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

    @Override
    public TemplateView getTemplateView() {
        return TemplateView.StudentEvaluationTemplate;
    }
}
