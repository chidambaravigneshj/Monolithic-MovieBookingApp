package com.exercises.theatresmanager.repository;

import com.exercises.theatresmanager.dao.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long> {

}
