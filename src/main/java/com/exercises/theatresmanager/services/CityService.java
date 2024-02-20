package com.exercises.theatresmanager.services;

import com.exercises.theatresmanager.dao.City;
import com.exercises.theatresmanager.dao.Movie;
import com.exercises.theatresmanager.models.CityDTO;

public interface CityService {

  City addCity(CityDTO addCityReq);
}
