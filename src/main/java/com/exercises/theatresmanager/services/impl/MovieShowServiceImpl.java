package com.exercises.theatresmanager.services.impl;

import com.exercises.theatresmanager.dao.Movie;
import com.exercises.theatresmanager.dao.MovieShow;
import com.exercises.theatresmanager.dao.Theatre;
import com.exercises.theatresmanager.dao.TheatreScreen;
import com.exercises.theatresmanager.exceptions.utils.TheatreMngExceptionUtil;
import com.exercises.theatresmanager.models.AddMovieToTheatreShow;
import com.exercises.theatresmanager.models.MovieShowResponseDTO;
import com.exercises.theatresmanager.models.ShowsForMvWithDateAndCityReq;
import com.exercises.theatresmanager.models.UpdateMovieShowRequest;
import com.exercises.theatresmanager.models.UpdateMovieShowResponse;
import com.exercises.theatresmanager.repository.MovieRepository;
import com.exercises.theatresmanager.repository.MovieShowRepository;
import com.exercises.theatresmanager.repository.TheatreRepository;
import com.exercises.theatresmanager.repository.TheatreScreenRepository;
import com.exercises.theatresmanager.services.MovieShowService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MovieShowServiceImpl implements MovieShowService {

  @Autowired
  private MovieShowRepository movieShowRepository;
  @Autowired
  private MovieRepository movieRepository;
  @Autowired
  private TheatreRepository theatreRepository;
  @Autowired
  private TheatreScreenRepository theatreScreenRepository;

  @Autowired
  private ObjectMapper objectMapper;
  @Override
  public List<MovieShowResponseDTO> getShowsForMovieInCityWithDate(
      ShowsForMvWithDateAndCityReq showsForMvWithDateAndCityReq) {

    List<MovieShowResponseDTO> movieShowResponseDTOList = new ArrayList<>();

    Movie movie= movieRepository.findById(showsForMvWithDateAndCityReq.getMovieId())
        .get();

    try {
      log.info("SHOWS: {}", objectMapper.writeValueAsString(movie.getShows()));

    List<MovieShow> movieShowList = movie.getShows().stream().filter(movieShow -> movieShow.
            getDate().equals(showsForMvWithDateAndCityReq.getDate())).collect(Collectors.toList());
      log.info("SHOWS IN GIVEN DATE: {}", objectMapper.writeValueAsString(movieShowList));
    List<Theatre> theatres = theatreRepository.findAll();
    List<Theatre> theatresInGivenCity = theatres.stream().filter(theatre -> theatre.getCity().
            equals(showsForMvWithDateAndCityReq.getCity())).collect(Collectors.toList());
      log.info("THEATRES IN GIVEN CITY: {}", objectMapper.writeValueAsString(theatresInGivenCity));
      List<MovieShow> finalMovieShowList = theatresInGivenCity.stream().flatMap(
        theatre ->
        {
          return theatre.getScreens().stream().flatMap(screen ->
          {
            return screen.getMovieShows().stream()
              .filter(show -> {
                if(movieShowList.contains(show)) {
                  MovieShowResponseDTO movieShowResponseDTO = new MovieShowResponseDTO();
                  movieShowResponseDTO.setMovieName(movie.getTitle());
                  movieShowResponseDTO.setTheatreId(theatre.getId());
                  movieShowResponseDTO.setTheatreName(theatre.getName());
                  movieShowResponseDTO.setScreenId(screen.getId());
                  movieShowResponseDTO.setScreenName(screen.getName());
                  movieShowResponseDTO.setDate(show.getDate());
                  movieShowResponseDTO.setStartTime(show.getTime());
                  movieShowResponseDTO.setIsHouseFull(show.getIsFull());
                  movieShowResponseDTOList.add(movieShowResponseDTO);
                  return true;
                } else {
                  return false;
                }
              });
          });
        }

    ).collect(Collectors.toList());
      log.info("finalMovieShowList IN GIVEN CITY and Date: {}", objectMapper.writeValueAsString(finalMovieShowList));

    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return movieShowResponseDTOList;
  }
  public MovieShow addMovieShowsInTheatreScreen(AddMovieToTheatreShow addMovieToTheatreShow) {
    MovieShow movieShow = new MovieShow();
    movieShow.setDate(addMovieToTheatreShow.getDate());
    movieShow.setTime(addMovieToTheatreShow.getStartTime());
    movieShow.setIsFull(false);
    Movie movie = null;
    Optional<Movie> movieOptional = movieRepository.findById(addMovieToTheatreShow.getMovieId());
    if(movieOptional.isPresent()) {
      movie = movieOptional.get();
    } else {
      throw TheatreMngExceptionUtil.generateMovieNotFoundException(
          "Movie with ID: "+addMovieToTheatreShow.getMovieId().toString()+" is not found",
          "Failure to find movie",
          HttpStatus.NOT_FOUND);
    }
    Optional<Theatre> theatreOptional = theatreRepository.findById(addMovieToTheatreShow.getTheatreId());
    if(theatreOptional.isPresent()) {
      Theatre theatre  = theatreOptional.get();
      Optional<TheatreScreen> theatreScreenOptional = theatre.getScreens().stream().filter(theatreScreen -> theatreScreen.getId()
          .equals(addMovieToTheatreShow.getScreenId())).findFirst();
      if (theatreScreenOptional.isPresent()) {
        TheatreScreen theatreScreen = theatreScreenOptional.get();
        Set<MovieShow> movieShows = theatreScreen.getMovieShows();
        movieShows.add(movieShow);
        theatreScreen.setMovieShows(movieShows);
        theatreRepository.save(theatre);
      } else {
        throw TheatreMngExceptionUtil.generateTheatreManagementException(
            "Theatre Screen with ID: "+addMovieToTheatreShow.getScreenId().toString()+" is not found",
            "Failure to find theatre screen",
            HttpStatus.NOT_FOUND);
      }
    } else {
      throw TheatreMngExceptionUtil.generateTheatreManagementException(
        "Theatre with ID: "+addMovieToTheatreShow.getTheatreId().toString()+" is not found",
        "Failure to find theatre",
        HttpStatus.NOT_FOUND);
    }
    Set<MovieShow> movieShowsSet = movie.getShows();
    movieShowsSet.add(movieShow);
    movie.setShows(movieShowsSet);
    movieRepository.save(movie);
    return movieShow;
  }

  @Transactional
  public UpdateMovieShowResponse updateMovieShowInTheatreScreen(UpdateMovieShowRequest updateMovieShowRequest) {
    Optional<MovieShow> movieShowOptional = movieShowRepository.findById(updateMovieShowRequest.getShowId());
    Optional<TheatreScreen> theatreScreenOptional = theatreScreenRepository.findById(
        updateMovieShowRequest.getScreenId());
    Optional<Movie> movieOptional = movieRepository.findById(updateMovieShowRequest.getMovieId());
    if(movieShowOptional.isPresent()) {
      if(theatreScreenOptional.isPresent()) {
        if(movieOptional.isPresent()) {
          movieShowRepository.updateMovieShow(updateMovieShowRequest.getScreenId(),
              updateMovieShowRequest.getMovieId(), updateMovieShowRequest.getDate(),
              updateMovieShowRequest.getStartTime(),updateMovieShowRequest.getIsHouseFull(),
              updateMovieShowRequest.getShowId());
          UpdateMovieShowResponse response = UpdateMovieShowResponse.builder().
              showId(updateMovieShowRequest.getShowId()).success(true).build();
          return response;
        } else {
          throw TheatreMngExceptionUtil.generateTheatreManagementException(
              "Movie with ID: "+updateMovieShowRequest.getMovieId().toString()+" is not found",
              "Failed to find movie",
              HttpStatus.NOT_FOUND);
        }
      } else {
        throw TheatreMngExceptionUtil.generateTheatreManagementException(
            "Screen with ID: "+updateMovieShowRequest.getScreenId().toString()+" is not found",
            "Failed to find screen",
            HttpStatus.NOT_FOUND);
      }
    } else {
      throw TheatreMngExceptionUtil.generateTheatreManagementException(
          "Show with ID: "+updateMovieShowRequest.getShowId().toString()+" is not found",
          "Failed to find show details",
          HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public List<MovieShowResponseDTO> getAllShows() {
    List<MovieShowResponseDTO> movieShowResponseDTOList= new ArrayList<>();
    List<MovieShow> movieShowsList = movieShowRepository.findAll();
    movieShowsList.forEach(movieShow -> {
      MovieShowResponseDTO movieShowResponseDTO = new MovieShowResponseDTO();
      Long movieId = movieShowRepository.getMovieIdFromShowId(movieShow.getId());
      Long screenId = movieShowRepository.getScreenIdFromShowId(movieShow.getId());
      Long theatreId = theatreScreenRepository.getThreatreIdFromScreenId(screenId);

      movieShowResponseDTO.setTheatreId(theatreId);
      movieShowResponseDTO.setTheatreName(theatreRepository.findById(theatreId).get().getName());

      movieShowResponseDTO.setScreenId(screenId);
      movieShowResponseDTO.setScreenName(theatreScreenRepository.findById(screenId).get().getName());
      movieShowResponseDTO.setMovieName(movieRepository.findById(movieId).get().getTitle());

      movieShowResponseDTO.setShowId(movieShow.getId());
      movieShowResponseDTO.setDate(movieShow.getDate());
      movieShowResponseDTO.setStartTime(movieShow.getTime());
      movieShowResponseDTO.setIsHouseFull(movieShow.getIsFull());
      movieShowResponseDTOList.add(movieShowResponseDTO);
    });
    return movieShowResponseDTOList;
  }

  public void deleteShow(Long showId) {
    if(movieShowRepository.existsById(showId)) {
      movieShowRepository.deleteById(showId);
    } else {
      throw TheatreMngExceptionUtil.generateTheatreManagementException(
          "Show with ID: "+showId.toString()+" is not found",
          "Failed to delete show",
          HttpStatus.NOT_FOUND);
    }
  }

}
