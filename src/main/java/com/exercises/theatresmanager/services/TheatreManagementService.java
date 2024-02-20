package com.exercises.theatresmanager.services;

import com.exercises.theatresmanager.dao.Theatre;
import com.exercises.theatresmanager.dao.TheatreScreen;
import com.exercises.theatresmanager.model.AddTheatreRequest;
import com.exercises.theatresmanager.model.AddTheatreResponse;
import com.exercises.theatresmanager.model.ScreenInfo;
import com.exercises.theatresmanager.models.ScreenDTO;
import com.exercises.theatresmanager.models.TheatreDTO;
import java.util.List;
import org.springframework.stereotype.Service;

public interface TheatreManagementService {

  Theatre addTheatre(TheatreDTO addTheatreRequest);
  Theatre addNewScreen(ScreenDTO addScreenRequest);
  TheatreScreen addSeats(Long screenId);
  List<Theatre> getAllTheatres();
  Theatre getTheatreById(Long id);
}
