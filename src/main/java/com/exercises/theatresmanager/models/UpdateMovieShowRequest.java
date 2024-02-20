package com.exercises.theatresmanager.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UpdateMovieShowRequest {
  private Long showId;
  private Long screenId;
  private Long movieId;
  private String date;
  private String startTime;
  private Boolean isHouseFull;
}
