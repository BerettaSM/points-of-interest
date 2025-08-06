package com.xyz.gps.poi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xyz.gps.poi.domain.entities.POI;
import com.xyz.gps.poi.domain.model.Coordinate;

public interface POIRepository extends JpaRepository<POI, Coordinate> {

    Optional<POI> findBySlug(String slug);

}
