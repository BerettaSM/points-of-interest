package com.xyz.gps.poi.domain.entities;

import com.xyz.gps.poi.domain.model.Coordinate;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Table(name = "TBL_POINT_OF_INTEREST")
public class POI {

    private String name;
    private String slug;
    
    @EqualsAndHashCode.Include
    @EmbeddedId
    private Coordinate coords;
    
}
