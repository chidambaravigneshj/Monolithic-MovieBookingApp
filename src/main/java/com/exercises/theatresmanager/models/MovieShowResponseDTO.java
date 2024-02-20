package com.exercises.theatresmanager.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonSerialize
public class MovieShowResponseDTO {
  private Long showId;
  private String movieName;
  private Long theatreId;
  private String theatreName;
  private Long screenId;
  private String screenName;
  private String date;
  private String startTime;
  private Integer price;
  private Boolean isHouseFull;
}
