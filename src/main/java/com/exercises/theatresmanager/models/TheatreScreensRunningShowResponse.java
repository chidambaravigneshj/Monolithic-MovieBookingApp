package com.exercises.theatresmanager.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonSerialize
public class TheatreScreensRunningShowResponse {
  private Long screenId;
  private String name;
  @JsonProperty("shows")
  private List<TheatreShowsRunningResponse> theatreShowsRunningResponses;
}
