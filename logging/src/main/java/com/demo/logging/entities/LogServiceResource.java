package com.demo.logging.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class LogServiceResource {

    private String type;
    private Map<String,String> labels;
}
