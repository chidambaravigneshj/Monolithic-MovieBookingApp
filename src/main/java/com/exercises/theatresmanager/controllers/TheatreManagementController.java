package com.exercises.theatresmanager.controllers;


import com.exercises.theatresmanager.api.TheatresManagementApi;
import com.exercises.theatresmanager.dao.Theatre;
import com.exercises.theatresmanager.dao.TheatreScreen;
import com.exercises.theatresmanager.model.AddTheatreRequest;
import com.exercises.theatresmanager.model.AddTheatreResponse;
import com.exercises.theatresmanager.models.ScreenDTO;
import com.exercises.theatresmanager.models.TheatreDTO;
import com.exercises.theatresmanager.services.TheatreManagementService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/theatre-service")
public class TheatreManagementController {

  private final TheatreManagementService theatreManagementservice;

  public TheatreManagementController(TheatreManagementService theatreManagementservice) {
    this.theatreManagementservice = theatreManagementservice;
  }


  @PostMapping("/addtheatre")
  public ResponseEntity<Theatre> addTheatre(@RequestBody TheatreDTO addTheatreRequest) {
    Theatre addTheatreResponse = theatreManagementservice.addTheatre(addTheatreRequest);
    return new ResponseEntity<>(addTheatreResponse, HttpStatus.CREATED);
  }

  @PostMapping("/addScreen")
  public ResponseEntity<Theatre> addTheatre(@RequestBody ScreenDTO addScreenRequest) {
    Theatre addTheatreScreenResponse = theatreManagementservice.addNewScreen(addScreenRequest);
    return new ResponseEntity<>(addTheatreScreenResponse, HttpStatus.OK);
  }

  @PostMapping("/screen/{screenId}/addSeats")
  public ResponseEntity<TheatreScreen> addTheatre(@PathVariable Long screenId) {
    TheatreScreen addSeatsResponse = theatreManagementservice.addSeats(screenId);
    return new ResponseEntity<>(addSeatsResponse, HttpStatus.OK);
  }

  @GetMapping("/getAllTheatres")
  public ResponseEntity<List<Theatre>> getAllTheatres() {
    List<Theatre> theatres = theatreManagementservice.getAllTheatres();
    return new ResponseEntity<>(theatres, HttpStatus.OK);
  }

  @GetMapping("/getTheatres/{id}")
  public ResponseEntity<Theatre> getTheatreById(@PathVariable Long id) {
    Theatre theatre = theatreManagementservice.getTheatreById(id);
    return new ResponseEntity<>(theatre, HttpStatus.OK);
  }
}
