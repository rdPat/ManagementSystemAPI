package com.ram.Springboot.tutorial.error;

import com.ram.Springboot.tutorial.entity.ErrorMessage;
import jdk.jfr.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorMessage> departmentNotFoundException(DepartmentNotFoundException message, WebRequest request)
    {
        ErrorMessage message1=new ErrorMessage(HttpStatus.NOT_FOUND,message.getMessage());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(message1);
    }
}
