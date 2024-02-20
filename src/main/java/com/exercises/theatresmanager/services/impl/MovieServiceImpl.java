package com.exercises.theatresmanager.services.impl;

import com.exercises.theatresmanager.dao.City;
import com.exercises.theatresmanager.dao.Movie;
import com.exercises.theatresmanager.exceptions.utils.TheatreMngExceptionUtil;
import com.exercises.theatresmanager.models.MovieDTO;
import com.exercises.theatresmanager.repository.CityRepository;
import com.exercises.theatresmanager.repository.MovieRepository;
import com.exercises.theatresmanager.services.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {

  @Autowired
  private MovieRepository movieRepository;
  @Autowired
  private CityRepository cityRepository;
  @Autowired
  private ObjectMapper objectMapper;
  @Override
  public Movie addMovie(MovieDTO addMovieReq) {
    Movie movie = new Movie();
    movie.setTitle(addMovieReq.getTitle());
    movie.setGenre(addMovieReq.getGenre());
    movie.setDuration(addMovieReq.getDuration());
    movie.setCountry(addMovieReq.getCountry());
    movie.setReleaseDate(addMovieReq.getReleaseDate());
    movie.setDescription(addMovieReq.getDescription());
    movie.setLanguage(addMovieReq.getLanguage());
    return movieRepository.save(movie);
  }

  @Override
  public List<Movie> getMovies() {
    List<Movie> movies = movieRepository.findAll();
    return movies;
  }

  @Override
  public Movie getMovieById(Long id) {
    return movieRepository.findById(id).orElseThrow(
        () -> TheatreMngExceptionUtil.generateMovieNotFoundException(
           "Unable to find the movie",
           "Movie not present in system", HttpStatus.NOT_FOUND
        )
    );
  }

  @Override
  public List<Movie> getMoviesByCity(String city) {
    Optional<City> cityOptional = cityRepository.findByName(city);
    List<Long> movieIds = new ArrayList<>();
    if (cityOptional.isPresent()) {
      Integer city_id = cityOptional.get().getId();
      movieIds = movieRepository.getMovieIdsInCity(city_id);
    } else {
      throw new RuntimeException("City is not found...");
    }
    List<Movie> movies = movieRepository.findAllById(movieIds);
    return movies;
  }

  @Override
  public Movie assignMovieToCity(Long movieId, String cityName) {
    Optional<Movie> movieOptional = movieRepository.findById(movieId);
    if(movieOptional.isPresent()) {
      Movie movie = movieOptional.get();
      Optional<City> cityOptional = cityRepository.findByName(cityName);
      if(cityOptional.isPresent()) {
        City city = cityOptional.get();
        Set<City> availableCitiesSet = movie.getAvailableInCities();
        availableCitiesSet.add(city);
        try {
          log.info("availableCitiesSet: {}",objectMapper.writeValueAsString(availableCitiesSet));
          movie.setAvailableInCities(availableCitiesSet);
          log.info("movie: {}",objectMapper.writeValueAsString(movie));
          return movieRepository.save(movie);
        } catch (JsonProcessingException e) {
          throw new RuntimeException(e);
        }
      }
      else {
        throw new RuntimeException("City is not found with the given name: "+cityName);
      }
    } else {
      throw new RuntimeException("Movie is not found with the given ID: "+movieId.toString());
    }
  }
}
