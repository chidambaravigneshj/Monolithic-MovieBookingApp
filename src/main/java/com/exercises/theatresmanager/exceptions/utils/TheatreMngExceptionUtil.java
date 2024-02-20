package com.exercises.theatresmanager.exceptions.utils;

import com.exercises.theatresmanager.exceptions.TheatreManagementException;
import com.exercises.theatresmanager.model.ApiError;
import org.springframework.http.HttpStatus;

public class TheatreMngExceptionUtil {
  public static TheatreManagementException generateTheatreManagementException(
      String message, String error, HttpStatus status) {
    ApiError apiError = new ApiError();
    apiError.setCode(status.value());
    apiError.setMessage(message);
    apiError.setError(error);
    TheatreManagementException theatreManagementException = new TheatreManagementException
        (apiError, status);
    return theatreManagementException;
  }

  public static TheatreManagementException generateMovieNotFoundException(
      String message, String error, HttpStatus status) {
    ApiError apiError = new ApiError();
    apiError.setCode(status.value());
    apiError.setMessage(message);
    apiError.setError(error);
    TheatreManagementException theatreManagementException = new TheatreManagementException
        (apiError, status);
    return theatreManagementException;
  }

}
