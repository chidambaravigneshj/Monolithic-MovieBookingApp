package com.exercises.theatresmanager.repository;

import com.exercises.theatresmanager.dao.TheatreScreen;
import com.exercises.theatresmanager.dao.TheatreScreenSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreScreenSeatRepository extends JpaRepository<TheatreScreenSeat, Long> {

}
