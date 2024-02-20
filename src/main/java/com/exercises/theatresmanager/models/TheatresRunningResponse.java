package com.exercises.theatresmanager.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonSerialize
public class TheatresRunningResponse {
  private Long theatreId;
  private String theatreName;
  @JsonProperty("screens")
  private List<TheatreScreensRunningShowResponse> theatreScreensRunningShowResponses;

}
