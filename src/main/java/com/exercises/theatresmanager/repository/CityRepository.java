package com.exercises.theatresmanager.repository;

import com.exercises.theatresmanager.dao.City;
import com.exercises.theatresmanager.dao.Theatre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

  Optional<City> findByName(String name);

}
