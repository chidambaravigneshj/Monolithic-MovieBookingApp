package com.exercises.theatresmanager.repository;

import com.exercises.theatresmanager.dao.Movie;
import com.exercises.theatresmanager.dao.MovieShow;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieShowRepository extends JpaRepository<MovieShow, Long> {
  Optional<List<MovieShow>> findByDate(String date);
  @Query(value = "SELECT movie_ref FROM movie_show ms WHERE ms.show_id=:id", nativeQuery = true)
  Long getMovieIdFromShowId(Long id);
  @Query(value = "SELECT screen_ref FROM movie_show ms WHERE ms.show_id=:id", nativeQuery = true)
  Long getScreenIdFromShowId(Long id);
  @Modifying
  @Query(value = "update movie_show ms set ms.screen_ref=:screenId, ms.movie_ref=:movieId,"
      + "ms.date = :date, ms.time = :time, ms.is_full=:isFull where ms.show_id = :id"
      ,nativeQuery = true)
  void updateMovieShow(Long screenId, Long movieId, String date, String time, Boolean isFull, Long id);
}
