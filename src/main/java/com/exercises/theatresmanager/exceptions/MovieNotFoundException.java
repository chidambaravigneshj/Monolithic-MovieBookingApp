package com.exercises.theatresmanager.exceptions;

import com.exercises.theatresmanager.model.ApiError;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MovieNotFoundException extends RuntimeException {

  final HttpStatus httpStatus;
  final ApiError error;

  public MovieNotFoundException(ApiError apiError, HttpStatus httpStatus) {
    super(String.format("%s", apiError.getError()));
    this.httpStatus = httpStatus;
    this.error = apiError;
  }

}
