package com.github.perucca.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
@Getter
public class Student {

    private String email;
    private String name;
}
