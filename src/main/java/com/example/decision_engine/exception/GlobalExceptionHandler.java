package com.example.decision_engine.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice   //this class will handle exceptions globally for all controllers. Instead of writing try-catch in every controller, we handle errors here.
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)   //If any IllegalArgumentException happens anywhere in the application, call this method. it will catches exception object and redirects to this handler.
    @ResponseStatus(HttpStatus.BAD_REQUEST)  // When this exception happens, return HTTP status 400 instead of 500
   //we use Map here to return response like json format like key and values
    public Map<String , String> handleInvalidException(IllegalArgumentException ex){
        //creating custom json response
        Map<String ,String> map = new HashMap<>();
        map.put("Error","Invalid Rule Expression");
        map.put("message",ex.getMessage());

        return map;
    }

}
