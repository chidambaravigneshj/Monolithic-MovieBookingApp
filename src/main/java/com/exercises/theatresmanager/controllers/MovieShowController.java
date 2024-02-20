package com.exercises.theatresmanager.controllers;


import com.exercises.theatresmanager.dao.Movie;
import com.exercises.theatresmanager.dao.MovieShow;
import com.exercises.theatresmanager.models.AddMovieToTheatreShow;
import com.exercises.theatresmanager.models.MovieShowResponseDTO;
import com.exercises.theatresmanager.models.ShowsForMvWithDateAndCityReq;
import com.exercises.theatresmanager.models.UpdateMovieShowRequest;
import com.exercises.theatresmanager.models.UpdateMovieShowResponse;
import com.exercises.theatresmanager.repository.MovieRepository;
import com.exercises.theatresmanager.repository.MovieShowRepository;
import com.exercises.theatresmanager.services.MovieShowService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/movie-show")
public class MovieShowController {

  @Autowired
  private MovieShowService movieShowService;

  @GetMapping(value = "/getShowsForMovieWithDate", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<MovieShowResponseDTO>> getShowsForMovieWithDate(
      @RequestBody ShowsForMvWithDateAndCityReq showsForMvWithDateAndCityReq) {
    return new ResponseEntity<>(movieShowService.getShowsForMovieInCityWithDate
        (showsForMvWithDateAndCityReq), HttpStatus.OK);
  }

  @PostMapping(value = "/addMovieShow", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<MovieShow> addMovieShow(
      @RequestBody AddMovieToTheatreShow addMovieToTheatreShow) {
    return new ResponseEntity<>(movieShowService.addMovieShowsInTheatreScreen(addMovieToTheatreShow)
        , HttpStatus.CREATED);
  }

  @PutMapping(value = "/updateMovieShow", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UpdateMovieShowResponse> updateMovieShow(
      @RequestBody UpdateMovieShowRequest updateMovieShowRequest) {
    return new ResponseEntity<>(movieShowService.updateMovieShowInTheatreScreen(updateMovieShowRequest)
        , HttpStatus.OK);
  }

  @GetMapping(value = "/getAllShows", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<MovieShowResponseDTO>> getAllShows() {
    return new ResponseEntity<>(movieShowService.getAllShows(), HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void getAllShows(@PathVariable Long id) {
    movieShowService.deleteShow(id);
  }

}
