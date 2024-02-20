package com.exercises.theatresmanager.dao;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Data
@NoArgsConstructor
public class TheatreScreen {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "screen_id")
  private Long id;
  @Column(name = "screen_name")
  private String name;
  @Column(name = "total_seats")
  private Integer totalSeatsCount;

  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, targetEntity = TheatreScreenSeat.class)
  @JoinColumn(name = "theatre_screen_ref", referencedColumnName = "screen_id")
  private Set<TheatreScreenSeat> seats=new HashSet<>();

  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, targetEntity = MovieShow.class)
  @JoinColumn(name = "screen_ref", referencedColumnName = "screen_id")
  private Set<MovieShow> movieShows=new HashSet<>();

}
