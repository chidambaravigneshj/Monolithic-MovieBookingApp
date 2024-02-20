package com.exercises.theatresmanager.convertors;

import com.exercises.theatresmanager.dao.TheatreScreen;
import com.exercises.theatresmanager.dao.TheatreScreenSeat;
import com.exercises.theatresmanager.model.ScreenInfo;
import com.exercises.theatresmanager.repository.TheatreScreenSeatRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class ScreenInfoToTheatreScreen
    implements Converter<ScreenInfo, TheatreScreen> {

  @Autowired
  private TheatreScreenSeatRepository theatreScreenSeatRepository;

  @Override
  public TheatreScreen convert(ScreenInfo screenInfo) {
    Set<TheatreScreenSeat> theatreScreenSeatList = new HashSet<>(screenInfo.getSeatsCapacity());
    TheatreScreen theatreScreen = new TheatreScreen();
    theatreScreen.setName(screenInfo.getName());
    theatreScreen.setTotalSeatsCount(screenInfo.getSeatsCapacity());
    int limit = screenInfo.getSeatsCapacity();
    for(int i = 0; i < limit; i++) {
      TheatreScreenSeat theatreScreenSeat = new TheatreScreenSeat();
      theatreScreenSeat.setSeatNumber(i+1);
      theatreScreenSeatList.add(theatreScreenSeat);
    }
    //theatreScreen.setSeats(theatreScreenSeatList);
    return theatreScreen;
  }
}
