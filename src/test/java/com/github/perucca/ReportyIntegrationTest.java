package com.github.perucca;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.mock;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ReportyApplication.class)
@Import(ReportyIntegrationTest.Config.class)
public abstract class ReportyIntegrationTest {

    @Configuration
    static class Config {
        @Bean
        public JavaMailSender javaMailSender() {
            return mock(JavaMailSender.class);
        }
    }
}
