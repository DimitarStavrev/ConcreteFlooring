package com.example.theproject.web;

import com.example.theproject.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ObjectNotFondAdvice {

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ModelAndView objectNotFound(ObjectNotFoundException objectNotFoundException) {

        ModelAndView modelAndView = new ModelAndView("object-not-found");
        modelAndView.addObject("objectId", objectNotFoundException.getObjectId());

        return modelAndView;
    }
}
