package com.exercises.theatresmanager.controllers;


import com.exercises.theatresmanager.dao.Movie;
import com.exercises.theatresmanager.models.MovieDTO;
import com.exercises.theatresmanager.services.MovieService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/movies")
public class MovieController {

  @Autowired
  private MovieService movieService;

  @PostMapping("/add")
  public ResponseEntity<Movie> addMovie(@RequestBody MovieDTO addMovieReq) {
    return new ResponseEntity<>(movieService.addMovie(addMovieReq), HttpStatus.CREATED);
  }

  @PutMapping("/{movie_id}/addTo/{city_name}")
  public ResponseEntity<Movie> assignMovieToCity(@PathVariable Long movie_id, @PathVariable String city_name) {
    return new ResponseEntity(movieService.assignMovieToCity(movie_id, city_name), HttpStatus.OK);
  }

  @GetMapping("/getbyid/{id}")
  public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
    return new ResponseEntity<>(movieService.getMovieById(id), HttpStatus.OK);
  }
  @GetMapping("/get")
  public ResponseEntity<List<Movie>> getAllMovie() {
    return new ResponseEntity<>(movieService.getMovies(), HttpStatus.OK);
  }

  @GetMapping("/getbycity/{city}")
  public ResponseEntity<List<Movie>> getMoviesByCity(@PathVariable String city) {
    List<Movie> moviesInCity = movieService.getMoviesByCity(city);
    return new ResponseEntity<>(moviesInCity, HttpStatus.OK);
  }
}
