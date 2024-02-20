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
public class ShowsForMvWithDateAndCityReq {
  private Long movieId;
  private String date;
  private String city;
}
