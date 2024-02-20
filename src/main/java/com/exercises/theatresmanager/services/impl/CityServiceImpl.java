package com.exercises.theatresmanager.services.impl;


import com.exercises.theatresmanager.dao.City;
import com.exercises.theatresmanager.dao.Movie;
import com.exercises.theatresmanager.models.CityDTO;
import com.exercises.theatresmanager.repository.CityRepository;
import com.exercises.theatresmanager.services.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CityServiceImpl implements CityService {

  @Autowired
  private CityRepository cityRepository;

  @Override
  public City addCity(CityDTO addCityReq) {
    City city = new City();
    city.setName(addCityReq.getName());
    city.setState(addCityReq.getState());
    city.setCountry(addCityReq.getCountry());
    return cityRepository.save(city);
  }
}
