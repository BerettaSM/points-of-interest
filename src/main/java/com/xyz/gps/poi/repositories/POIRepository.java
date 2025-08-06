package com.xyz.gps.poi.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xyz.gps.poi.domain.entities.POI;
import com.xyz.gps.poi.domain.model.Coordinate;
import com.xyz.gps.poi.domain.projections.POIWithDistanceProjection;

public interface POIRepository extends JpaRepository<POI, Coordinate> {

    Optional<POI> findBySlug(String slug);

    @Query(nativeQuery = true, value = """
        SELECT name, x, y, FLOOR(SQRT(POWER(:x - x, 2) + POWER(:y - y, 2))) AS distance
        FROM TBL_POINT_OF_INTEREST
        WHERE FLOOR(SQRT(POWER(:x - x, 2) + POWER(:y - y, 2))) <= :radius
        ORDER BY distance
    """, countQuery = """
        SELECT COUNT(*)
        FROM TBL_POINT_OF_INTEREST
        WHERE FLOOR(SQRT(POWER(:x - x, 2) + POWER(:y - y, 2))) <= :radius
    """)
    Page<POIWithDistanceProjection> findByProximity(Long x, Long y, Long radius, Pageable pageable);

}
