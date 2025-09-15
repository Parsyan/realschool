package com.realschool.school.controller.exceptionHandlers;

import com.realschool.school.controller.exception.NoSuchEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CRUDExceptionsHandler {

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<ProblemDetail> nullPointerExceptionResponseEntity(NullPointerException nullPointerException){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, nullPointerException.toString());

        return new ResponseEntity<>(problemDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ProblemDetail> illegalArgumentExceptionResponseEntity(IllegalArgumentException illegalArgumentException){
        return new ResponseEntity<>(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, illegalArgumentException.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NoSuchEntityException.class)
    public ResponseEntity<ProblemDetail> noSuchEntityExceptionResponseEntity(NoSuchEntityException noSuchEntityException){
        return new ResponseEntity<>(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, noSuchEntityException.getMessage()), HttpStatus.BAD_REQUEST);
    }

// TODO Enable Logger for monitoring processes in all processes of course


}
