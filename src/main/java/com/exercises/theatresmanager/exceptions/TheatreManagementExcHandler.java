package com.exercises.theatresmanager.exceptions;

import com.exercises.theatresmanager.model.ApiError;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TheatreManagementExcHandler {
  @ExceptionHandler(TheatreManagementException.class)
  public ResponseEntity<ApiError> theatreManagementExceptionHandler(TheatreManagementException ex) {
    return new ResponseEntity<>(ex.getError(), ex.getHttpStatus());
  }
  @ExceptionHandler(MovieNotFoundException.class)
  public ResponseEntity<ApiError> movieNotFoundExceptionHandler(MovieNotFoundException ex) {
    return new ResponseEntity<>(ex.getError(), ex.getHttpStatus());
  }
}
