package com.github.perucca.mailer.service;

import com.github.perucca.ReportyIntegrationTest;
import com.github.perucca.domain.model.StudentEvaluation;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.perucca.CsvMapper.studentEvaluationMapper;
import static com.github.perucca.mailer.Template.TemplateView.StudentEvaluationTemplate;
import static org.assertj.core.api.Assertions.assertThat;

public class VelocityServiceIntegrationTest extends ReportyIntegrationTest{

    @Autowired
    VelocityService velocityService;

    StudentEvaluation studentEvaluation;

    @Before
    public void setupModel() {
        String studentEvaluationAsString = "Walter,walter@gmail.com,0,2,1,0,1,1,3,1,1,0,1,5,Cannot be defeated";
        studentEvaluation = studentEvaluationMapper(studentEvaluationAsString);
    }

    @Test
    public void should_get_mail_content_as_string() {
        String averageMark = studentEvaluation.computeTotal();
        String fileName = StudentEvaluationTemplate.getFileName();

        String mailContent = velocityService.getMailContent(fileName, studentEvaluation.toMapProperties());

        System.out.println(mailContent);

        assertThat(mailContent)
                .contains("Bonjour Walter")
                .contains("Vous avez eu : ".concat(averageMark))
                .contains(studentEvaluation.getComment());
    }
}