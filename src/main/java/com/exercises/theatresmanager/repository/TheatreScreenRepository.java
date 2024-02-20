package com.exercises.theatresmanager.repository;

import com.exercises.theatresmanager.dao.Theatre;
import com.exercises.theatresmanager.dao.TheatreScreen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreScreenRepository extends JpaRepository<TheatreScreen, Long> {

  @Query(value = "SELECT theatre_ref FROM theatre_screen ts WHERE ts.screen_id=:id", nativeQuery = true)
  Long getThreatreIdFromScreenId(Long id);


}
