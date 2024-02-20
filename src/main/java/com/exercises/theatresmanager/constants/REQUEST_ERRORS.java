package com.exercises.theatresmanager.constants;

public enum REQUEST_ERRORS {
  REQUEST_BODY_NULL_MSG("Request body is null"),
  REQUEST_BODY_NULL_ERROR("Request body payload is missing, please provide");
  private String value;

  public String getValue() {
    return value;
  }
  REQUEST_ERRORS(String val) {
    this.value = val;
  }
}
