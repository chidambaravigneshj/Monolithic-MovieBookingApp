package com.exercises.theatresmanager.exceptions;

import com.exercises.theatresmanager.model.ApiError;
import java.io.IOException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TheatreManagementException extends RuntimeException {

  final HttpStatus httpStatus;
  final ApiError error;

  public TheatreManagementException(ApiError apiError, HttpStatus httpStatus) {
    super(String.format("%s", apiError.getError()));
    this.httpStatus = httpStatus;
    this.error = apiError;

  }

}
