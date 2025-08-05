package com.xyz.gps.poi.domain.dto;

import com.xyz.gps.poi.domain.entities.POI;
import com.xyz.gps.poi.domain.model.Coordinate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class POIDto {

    private String name;
    private Long x;
    private Long y;

    public POIDto(POI poi) {
        name = poi.getName();
        x = poi.getCoords().getX();
        y = poi.getCoords().getY();
    }

    public POI toEntity() {
        return new POI(null, name, new Coordinate(x, y));
    }

    public static POIDto from(POI poi) {
        return new POIDto(poi);
    }
    
}
