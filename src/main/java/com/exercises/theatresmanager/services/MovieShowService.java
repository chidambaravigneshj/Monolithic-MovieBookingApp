package com.exercises.theatresmanager.services;

import com.exercises.theatresmanager.dao.MovieShow;
import com.exercises.theatresmanager.models.AddMovieToTheatreShow;
import com.exercises.theatresmanager.models.MovieShowResponseDTO;
import com.exercises.theatresmanager.models.ShowsForMvWithDateAndCityReq;
import com.exercises.theatresmanager.models.UpdateMovieShowRequest;
import com.exercises.theatresmanager.models.UpdateMovieShowResponse;
import java.util.List;

public interface MovieShowService {

  List<MovieShowResponseDTO> getShowsForMovieInCityWithDate(
      ShowsForMvWithDateAndCityReq showsForMvWithDateAndCityReq);

  MovieShow addMovieShowsInTheatreScreen(AddMovieToTheatreShow addMovieToTheatreShow);

  UpdateMovieShowResponse updateMovieShowInTheatreScreen(UpdateMovieShowRequest updateMovieShowRequest);

  List<MovieShowResponseDTO> getAllShows();

  void deleteShow(Long showId);
}
