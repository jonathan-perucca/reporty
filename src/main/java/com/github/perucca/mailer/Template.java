package com.github.perucca.mailer;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

public interface Template {

    Map<String, Object> getModel();

    String getReceiverMail();

    TemplateView getTemplateView();

    @AllArgsConstructor(access = PRIVATE)
    @Getter
    enum TemplateView {
        StudentEvaluationTemplate("student-evaluation.vm");

        private String fileName;
    }
}
