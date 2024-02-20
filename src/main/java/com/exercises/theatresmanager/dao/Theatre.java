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
import jakarta.persistence.OneToMany;
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

@Entity
@Data
@NoArgsConstructor
public class Theatre {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "theatre_id")
  private Long id;
  @Column(name = "theatre_name")
  private String name;
  private String city;
  @Column(name = "no_of_screens")
  private int totalScreens;

  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, targetEntity = TheatreScreen.class)
  @JoinColumn(name = "theatre_ref", referencedColumnName = "theatre_id")
  //  @OneToMany(mappedBy = "theatre", cascade = CascadeType.PERSIST, orphanRemoval = true)
  private Set<TheatreScreen> screens=new HashSet<>();

}
