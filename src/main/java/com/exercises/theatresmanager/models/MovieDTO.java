package com.exercises.theatresmanager.models;

import com.exercises.theatresmanager.dao.MovieShow;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.Set;
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
public class MovieDTO {
  private String title;
  private String description;
  private String language;
  private String country;
  private String genre;
  private String releaseDate;
  private String duration;
}
