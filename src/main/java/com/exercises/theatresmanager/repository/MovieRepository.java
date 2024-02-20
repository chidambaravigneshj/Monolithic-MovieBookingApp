package com.exercises.theatresmanager.repository;

import com.exercises.theatresmanager.dao.Movie;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

  @Query(value = "SELECT movie_id FROM movies_in_cities WHERE city_id=:cityId",
      nativeQuery = true)
  List<Long> getMovieIdsInCity(Integer cityId);

}
