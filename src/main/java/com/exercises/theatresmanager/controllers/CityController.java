package com.exercises.theatresmanager.controllers;

import com.exercises.theatresmanager.dao.City;
import com.exercises.theatresmanager.dao.Movie;
import com.exercises.theatresmanager.models.CityDTO;
import com.exercises.theatresmanager.models.MovieDTO;
import com.exercises.theatresmanager.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/cities")
public class CityController {

  @Autowired
  private CityService cityService;
  @PostMapping("/add")
  public ResponseEntity<City> addCity(@RequestBody CityDTO addCityReq) {
    return new ResponseEntity<>(cityService.addCity(addCityReq), HttpStatus.CREATED);
  }
}
