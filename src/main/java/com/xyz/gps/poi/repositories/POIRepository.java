package com.xyz.gps.poi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xyz.gps.poi.domain.entities.POI;

public interface POIRepository extends JpaRepository<POI, Long> {
}
