package com.exercises.theatresmanager.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonSerialize
public class TheatreShowsRunningResponse {
  private Long showId;
  private Date startTime;
  private Date endTime;
  private Boolean houseFull;
}
