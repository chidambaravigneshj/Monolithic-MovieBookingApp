package com.exercises.theatresmanager.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
public class AddMovieToTheatreShow {
  private Long theatreId;
  private Long screenId;
  private Long movieId;
  private String date;
  private String startTime;
}
