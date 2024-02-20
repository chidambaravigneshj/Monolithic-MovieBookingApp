package com.exercises.theatresmanager.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Movie {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "movie_id")
  private Long id;
  private String title;
  private String description;
  private String language;
  private String country;
  private String genre;
  @Column(name = "release_date")
  private String releaseDate;
  private String duration;

  @JsonIgnore
  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, targetEntity = MovieShow.class)
  @JoinColumn(name = "movie_ref", referencedColumnName = "movie_id")
  private Set<MovieShow> shows=new HashSet<>();

  @JsonIgnore
  @ManyToMany
  @JoinTable(
      name = "movies_in_cities",
      joinColumns = @JoinColumn(name = "movie_id"),
      inverseJoinColumns = @JoinColumn(name = "city_id"))
  private Set<City> availableInCities=new HashSet<>();
}
