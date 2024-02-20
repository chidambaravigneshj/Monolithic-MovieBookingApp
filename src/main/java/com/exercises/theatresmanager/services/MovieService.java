package com.exercises.theatresmanager.services;

import com.exercises.theatresmanager.dao.Movie;
import com.exercises.theatresmanager.models.MovieDTO;
import java.util.List;

public interface MovieService {
  Movie addMovie(MovieDTO addMovieReq);

  List<Movie> getMovies();

  Movie getMovieById(Long id);

  List<Movie> getMoviesByCity(String city);

  Movie assignMovieToCity(Long movieId, String cityName);
}
