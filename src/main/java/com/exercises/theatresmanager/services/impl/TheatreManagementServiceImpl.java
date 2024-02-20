package com.exercises.theatresmanager.services.impl;

import com.exercises.theatresmanager.dao.Theatre;
import com.exercises.theatresmanager.dao.TheatreScreen;
import com.exercises.theatresmanager.dao.TheatreScreenSeat;
import com.exercises.theatresmanager.exceptions.utils.TheatreMngExceptionUtil;
import com.exercises.theatresmanager.model.ScreenInfo;
import com.exercises.theatresmanager.models.ScreenDTO;
import com.exercises.theatresmanager.models.TheatreDTO;
import com.exercises.theatresmanager.repository.MovieRepository;
import com.exercises.theatresmanager.repository.TheatreRepository;
import com.exercises.theatresmanager.repository.TheatreScreenRepository;
import com.exercises.theatresmanager.repository.TheatreScreenSeatRepository;
import com.exercises.theatresmanager.services.TheatreManagementService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class TheatreManagementServiceImpl implements TheatreManagementService {

  @Autowired
  private TheatreRepository theatreRepository;

  @Autowired
  private TheatreScreenRepository theatreScreenRepository;

  @Autowired
  private MovieRepository movieRepository;

  @Autowired
  private TheatreScreenSeatRepository theatreScreenSeatRepository;

  @Autowired
  private ConversionService conversionService;

  @Autowired
  private ObjectMapper objectMapper;

  @Override
  @Transactional
  public Theatre addTheatre(TheatreDTO addTheatreRequest) {
      Theatre theatre = new Theatre();
      theatre.setName(addTheatreRequest.getName());
      theatre.setCity(addTheatreRequest.getCity());
      theatre.setTotalScreens(addTheatreRequest.getScreensCount());
      return theatreRepository.save(theatre);
  }

  public Theatre addNewScreen(ScreenDTO addScreenRequest) {
    TheatreScreen theatreScreen = new TheatreScreen();
    theatreScreen.setName(addScreenRequest.getName());
    theatreScreen.setTotalSeatsCount(addScreenRequest.getSeatsCapacity());
    Optional<Theatre> theatreOptional = theatreRepository.findById(addScreenRequest.getTheatreId());
    if(theatreOptional.isPresent()) {
      Theatre theatre = theatreOptional.get();
      Set<TheatreScreen> theatreScreens = theatre.getScreens();
      theatreScreens.add(theatreScreen);
      theatre.setScreens(theatreScreens);
      return theatreRepository.save(theatre);
    }
    else {
      throw new RuntimeException("Theatre not found, pls check");
    }
  }

  public TheatreScreen addSeats(Long screenId) {
    Optional<TheatreScreen> theatreScreenOptional = theatreScreenRepository.findById(screenId);
    if(theatreScreenOptional.isPresent()) {
      TheatreScreen theatreScreen = theatreScreenOptional.get();
      Set<TheatreScreenSeat> theatreScreenSeatSet = theatreScreen.getSeats();
      for(int i = 1; i <= theatreScreen.getTotalSeatsCount(); i++) {
        TheatreScreenSeat theatreScreenSeat = new TheatreScreenSeat();
        theatreScreenSeat.setSeatNumber(i);
        theatreScreenSeatSet.add(theatreScreenSeat);
      }
      theatreScreen.setSeats(theatreScreenSeatSet);
      return theatreScreenRepository.save(theatreScreen);
    } else {
      throw new RuntimeException("Screen not found, pls check");
    }
  }

  @Override
  public List<Theatre> getAllTheatres() {
    return theatreRepository.findAll();
  }

  @Override
  public Theatre getTheatreById(Long id) {
    return theatreRepository.findById(id).orElseThrow(() ->
        TheatreMngExceptionUtil.generateTheatreManagementException(
            "Theatre with ID: "+id.toString()+" is not found",
            "Failure to find theatre",
            HttpStatus.NOT_FOUND));
  }

  public Theatre getThreatreForScreenById(Long screenId) {
    Long theatreId = theatreScreenRepository.getThreatreIdFromScreenId(screenId);
    Theatre theatre = theatreRepository.findById(theatreId).get();
    try {
      log.info("Theatre from screen: {}", objectMapper.writeValueAsString(theatre));
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return  theatre;
  }
}
