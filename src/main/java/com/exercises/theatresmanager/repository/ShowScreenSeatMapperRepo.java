package com.exercises.theatresmanager.repository;

import com.exercises.theatresmanager.dao.Movie;
import com.exercises.theatresmanager.dao.ShowSeatScreenMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowScreenSeatMapperRepo extends JpaRepository<ShowSeatScreenMapper, Long> {

}
