package com.github.perucca.mailer.service;

import lombok.RequiredArgsConstructor;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import static org.springframework.ui.velocity.VelocityEngineUtils.mergeTemplateIntoString;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class VelocityService {

    private final VelocityEngine velocityEngine;
    private final String ENCODING = "UTF-8";

    public String getMailContent(Map<String, Object> model) {
        String resourcePath = "";

        return mergeTemplateIntoString(velocityEngine, resourcePath, ENCODING, model);
    }
}
