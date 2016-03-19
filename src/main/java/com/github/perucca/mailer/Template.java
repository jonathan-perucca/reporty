package com.github.perucca.mailer;

import java.util.Map;

public interface Template {

    Map<String, Object> getModel();
    String getReceiverMail();
}
