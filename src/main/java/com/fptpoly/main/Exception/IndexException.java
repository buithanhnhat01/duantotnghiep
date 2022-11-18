package com.fptpoly.main.Exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = Controller.class)
public class IndexException {

    @ExceptionHandler
    public String demo(Exception x){
        System.out.println(x);
        return "x";
    }
}
